package org.jetbrains.android.anko.config

interface ConfigurationKey<out T : Any> {
    val name: String
    val defaultValue: T?
}

fun <T : Any> ConfigurationKey(name: String, defaultValue: T? = null): ConfigurationKey<T> = ConfigurationKeyImpl(name, defaultValue)

private open class ConfigurationKeyImpl<out T : Any>(
        override val name: String,
        override val defaultValue: T? = null) : ConfigurationKey<T>

class CliConfiguationKey<out T : Any>(
        name: String,
        val cliName: String = name,
        defaultValue: T? = null,
        val mapper: (String) -> T?
) : ConfigurationKey<T> by ConfigurationKeyImpl(name, defaultValue)

val LOG_LEVEL = CliConfiguationKey(
        "logLevel",
        defaultValue = LogManager.LogLevel.WARNING,
        mapper = { LogManager.LogLevel.valueOf(it) })

val CLI_CONFIGURATION_KEYS: List<CliConfiguationKey<Any>> = listOf(LOG_LEVEL)

interface Options {
    operator fun <T : Any> get(key: ConfigurationKey<T>): T = opt(key)!!
    fun <T : Any> opt(key: ConfigurationKey<T>): T?

    operator fun contains(key: ConfigurationKey<*>) = opt(key) != null

    companion object {
        fun create(): Options = OptionsImpl()
    }
}

interface MutableOptions : Options {
    operator fun <T : Any> set(key: ConfigurationKey<T>, value: T)

    companion object {
        fun create(): MutableOptions = OptionsImpl()
    }
}

fun <T : Any> MutableOptions.setCliOption(key: CliConfiguationKey<T>, stringValue: String) {
    val value = key.mapper(stringValue)
    if (value != null) {
        this[key] = value
    }
}

private class OptionsImpl : MutableOptions {
    private val options = mutableMapOf<ConfigurationKey<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> opt(key: ConfigurationKey<T>) = options[key] as T? ?: key.defaultValue

    override fun <T : Any> set(key: ConfigurationKey<T>, value: T) {
        options[key] = value
    }
}
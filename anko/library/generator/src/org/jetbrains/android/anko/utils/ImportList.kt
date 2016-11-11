package org.jetbrains.android.anko.utils

class ImportList {
    private val myImports = mutableListOf<String>()
    private val myClassNames = mutableListOf<String>()

    val imports: List<String>
        get() = myImports

    fun isEmpty() = myImports.isEmpty()

    fun hasImport(fqName: String) = fqName in myImports

    operator fun get(fqName: String): String {
        val (packageName, className) = fqName.toPackageClassName()
        val nameToImport = getNameToImport(packageName, className)

        if (packageName.isEmpty() || hasImport(nameToImport)) return className

        // Already has another import with the same simple name, should use fqname
        if (className in myClassNames) return fqName

        add(nameToImport, className)
        return className
    }

    operator fun get(type: KType): KType {
        val newName = get(type.fqName)
        val newArgs = type.arguments.map { get(it) }
        return KType(newName, type.isNullable, type.variance, newArgs)
    }

    private fun add(nameToImport: String, className: String) {
        myImports += nameToImport
        myClassNames += className
    }

    private fun getNameToImport(packageName: String, className: String): String {
        return packageName + '.' + className.substringBefore('.')
    }

    private fun String.toPackageClassName(): Pair<String, String> {
        if ('.' !in this) return Pair("", this)

        val path = split('.')
        val packageName = path.takeWhile { it.all(Char::isPackageSymbol) }
        val simpleName = path.drop(packageName.size)

        return Pair(packageName.joinToString("."), simpleName.joinToString("."))
    }
}
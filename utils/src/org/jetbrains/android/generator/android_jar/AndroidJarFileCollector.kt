/*
 * Copyright 2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.android.generator.android_jar

import java.io.*
import org.jetbrains.android.anko.utils.*
import java.util.zip.*
import java.util.regex.*

public fun main(args: Array<String>): Unit = AndroidJarCollector().collect()

class AndroidJarCollector {
    private companion object {
        private val REQUIRED_PLATFORM_VERSIONS = arrayOf("15", "15s", "19", "19s", "21", "21s")
        private val VERSIONS = File("workdir/original").listFiles(AndroidVersionDirectoryFilter())

        private val SUPPORT_PACKAGES_VERSION = "22.2.0"
        private val SUPPORT_LIBRARIES = arrayOf("support-v4", "appcompat-v7")

        private val ANDROID_SDK = File("lib/android-sdk")
        private val PLATFORMS_DIR = File(ANDROID_SDK, "platforms")
        private val SUPPORT_PACKAGES_DIR = File(ANDROID_SDK, "extras/android/m2repository/com/android/support")

        private val INDENT = "    "
    }

    fun collect() {
        if (checkIfAlreadyCollected()) {
            println("All required files exist. Aborting")
            return
        }

        createDirs()
        val supportFiles = findSupportFiles()

        for (version in REQUIRED_PLATFORM_VERSIONS) {
            println("Processing platform ${version}:")

            val versionDir = File("workdir/original", version)
            val versionNumber = version.replace("s", "").toInt()
            val support = version.endsWith("s")
            processVersion(versionDir, versionNumber, support, supportFiles)
        }
        println("Complete.")
    }

    private fun processVersion(versionDir: File, versionNumber: Int, support: Boolean, supportFiles: List<File>) {
        fun log(s: String) = println("$INDENT$s")

        val platformDir = getPlatformDirectory(versionNumber)

        log("android")

        val androidJar = getAndroidJar(platformDir)
        androidJar.copyTo(File(versionDir, androidJar.name))

        if (support) {
            supportFiles.forEach { file ->
                log(file.nameWithoutExtension)
                
                when (file.extension.toLowerCase()) {
                    "aar" -> extractAarJars(file, versionDir)
                    "jar" -> file.copyTo(File(versionDir, file.name))
                    else -> throw CollectorException("Unknown file type")
                }
            }
        }
    }
    
    private fun extractAarJars(aar: File, destinationDir: File) {
        val aarName = aar.name.substringBeforeLast('.')
        
        ZipFile(aar).use { zip ->
            val entries = zip.entries()
            var entry = entries.nextElement()
            while (entry != null) {
                if (entry.getName().toLowerCase().endsWith(".jar")) {
                    val rawName = entry.getName().substringAfterLast('/')
                    val name = if (rawName == "classes.jar") "$aarName.jar" else "$aarName-$rawName"

                    FileOutputStream(File(destinationDir, name)).use { fos ->
                        zip.getInputStream(entry).copyTo(fos)
                    }
                }
                entry = if (entries.hasMoreElements()) entries.nextElement() else null
            }
        }
    }

    private fun getPlatformDirectory(versionNumber: Int): File {
        val platformDirectory = File(PLATFORMS_DIR, "android-$versionNumber")
        if (!platformDirectory.exists() || !platformDirectory.isDirectory()) {
            throw CollectorException("Android SDK platform $versionNumber not found")
        }
        return platformDirectory
    }

    private fun getAndroidJar(platformDir: File): File {
        val androidJar = File(platformDir, "android.jar")
        if (!androidJar.exists()) {
            throw CollectorException("$androidJar does not exist")
        }
        return androidJar
    }

    private fun createDirs() {
        REQUIRED_PLATFORM_VERSIONS.forEach { File("workdir/original/$it/").mkdirs() }
        File("workdir/temp").mkdirs()
    }

    private fun findSupportFiles(): List<File> {
        return SUPPORT_LIBRARIES.map { lib ->
            val dir = File(SUPPORT_PACKAGES_DIR, "$lib/$SUPPORT_PACKAGES_VERSION")
            val aar = File(dir, "$lib-$SUPPORT_PACKAGES_VERSION.aar")
            val jar = File(dir, "$lib-$SUPPORT_PACKAGES_VERSION.jar")
            if (aar.exists()) aar else if (jar.exists()) jar else throw CollectorException("$jar not found")
        }
    }

    private fun checkIfAlreadyCollected(): Boolean {
        if (VERSIONS == null || VERSIONS.isEmpty()) return false

        for (version in REQUIRED_PLATFORM_VERSIONS) {
            if (!VERSIONS.any { it.name == version }) return false
        }

        for (version in VERSIONS) {
            val androidJarFile = File(version, "android.jar")
            val support = version.name.endsWith("s")

            fun hasSupport(): Boolean {
                SUPPORT_LIBRARIES.forEach { filename ->
                    if (version.listFiles { it.name.startsWith(filename) }?.isEmpty() ?: true) return false
                }
                return true
            }

            if (!androidJarFile.exists()) return false
            if (support && !hasSupport()) return false
        }

        return true
    }

    private fun <R> ZipFile.use(block: (ZipFile) -> R): R {
        var closed = false
        try {
            return block(this)
        } catch (e: Exception) {
            closed = true
            try {
                this.close()
            }
            catch (closeException: Exception) {}
            throw e
        } finally {
            if (!closed) {
                this.close()
            }
        }
    }
    
}

private class CollectorException(message: String) : RuntimeException(message)

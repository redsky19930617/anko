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

package org.jetbrains.android.anko.functional

import org.jetbrains.android.anko.*
import org.jetbrains.android.anko.config.AnkoConfiguration
import org.jetbrains.android.anko.config.AnkoFile
import org.jetbrains.android.anko.utils.JarFileFilter
import java.io.File
import java.io.FileFilter
import java.io.FileWriter
import java.io.IOException
import java.util.ArrayList
import java.util.Arrays
import org.junit.Assert.*
import org.junit.Test

public abstract class AbstractFunctionalTest {
    val config = TestAnkoConfiguration()
    var classTree: ClassTree? = null

    protected fun loadOrCreate(file: File, data: String): String {
        try {
            return file.readText()
        } catch (e: Exception) {
            val parentDir = file.getParentFile()
            if (!parentDir.exists()) parentDir.mkdirs()

            file.createNewFile()
            val fileWriter = FileWriter(file)
            fileWriter.write(data.replace("\n", System.getProperty("line.separator")))
            fileWriter.close()
            fail("Empty expected data, creating from actual")
            return data
        }
    }

    protected fun runFunctionalTest(version: String,
                                    intVersion: Int,
                                    inputJarFiles: List<String>,
                                    testDataFile: String,
                                    subsystem: AnkoFile,
                                    config: TestAnkoConfiguration) {
        if (classTree == null) {
            classTree = ClassProcessor(inputJarFiles).genClassTree()
        }

        val generator = DSLGenerator(intVersion, version, inputJarFiles, config, classTree)
        generator.run()

        fun String.trimBlank() = trim('\n', '\t', ' ', '\r')

        val actual = config.getOutputFile(subsystem).readText().replace("\r", "").trimBlank()
        val expectedPath = ("dsl/testData/functional/$version/$testDataFile")
        val expected = loadOrCreate(File(expectedPath), actual).replace("\r", "").trimBlank()

        assertTrue("Expected text is empty.", expected.length() > 0)
        assertTrue("Actual text is empty.", actual.length() > 0)
        assertEquals(expected, actual)
    }

    protected fun runFunctionalTest(
            testDataFile: String,
            subsystem: AnkoFile,
            version: String,
            settings: AnkoConfiguration.() -> Unit
    ) {
        config.generateImports = false
        config.generatePackage = false
        config.generateMavenArtifact = false

        config.files.clear()
        config.tunes.clear()

        config.settings()

        val versionDir = File("workdir/original", version)
        val intVersion = version.replace("[^0-9]".toRegex(), "").toInt()

        val jarFiles = versionDir.listFiles(JarFileFilter()).map { it.getAbsolutePath() }

        runFunctionalTest(version, intVersion, jarFiles, testDataFile, subsystem, config)
    }

}

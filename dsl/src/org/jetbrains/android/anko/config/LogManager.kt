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

package org.jetbrains.android.anko.config

class LogManager(config: AnkoConfiguration) {
    private enum class LogLevel {
        DEBUG, INFO, WARNING, ERROR
    }

    private val level = LogLevel.valueOf(config.generatorOptions
            .filterIsInstance<GeneratorOption.LogLevel>().firstOrNull()?.arg?.toUpperCase() ?: LogLevel.INFO.name)

    public fun d(s: String) {
        if (level <= LogLevel.DEBUG) System.out.println("D: $s")
    }

    public fun i(s: String) {
        if (level <= LogLevel.INFO) System.out.println("I: $s")
    }

    public fun w(s: String) {
        if (level <= LogLevel.WARNING) System.out.println("W: $s")
    }

    public fun e(s: String) {
        if (level <= LogLevel.ERROR) {
            System.err.println("E: $s")
            throw Error(s)
        }
    }

}
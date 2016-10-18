/*
 * Copyright 2016 JetBrains s.r.o.
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

package org.jetbrains.android.generator.test

import org.jetbrains.android.anko.config.AnkoFile
import org.jetbrains.android.anko.config.Tune

fun Context.generate() {
    functionalDslTests {
        functionalDslTest("ComplexListenerClassTest", AnkoFile.LISTENERS) {
            file(AnkoFile.LISTENERS)
            tune(Tune.COMPLEX_LISTENER_CLASSES)
        }

        functionalDslTest("ComplexListenerSetterTest", AnkoFile.LISTENERS) {
            file(AnkoFile.LISTENERS)
            tune(Tune.COMPLEX_LISTENER_SETTERS)
        }

        functionalDslTest("LayoutsTest", AnkoFile.LAYOUTS) {
            file(AnkoFile.LAYOUTS)
        }

        functionalDslTest("ViewTest", AnkoFile.VIEWS) {
            file(AnkoFile.VIEWS)
            tune(Tune.TOP_LEVEL_DSL_ITEMS)
            tune(Tune.HELPER_CONSTRUCTORS)
        }

        functionalDslTest("PropertyTest", AnkoFile.PROPERTIES) {
            file(AnkoFile.PROPERTIES)
        }

        functionalDslTest("ServicesTest", AnkoFile.SERVICES) {
            file(AnkoFile.SERVICES)
        }

        functionalDslTest("SimpleListenerTest", AnkoFile.LISTENERS) {
            file(AnkoFile.LISTENERS)
            tune(Tune.SIMPLE_LISTENERS)
        }
        
        functionalDslTest("SqlParserHelpersTest", AnkoFile.SQL_PARSER_HELPERS) {
            file(AnkoFile.SQL_PARSER_HELPERS)
        }
    }
}
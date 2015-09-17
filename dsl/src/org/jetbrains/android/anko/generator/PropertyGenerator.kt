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

package org.jetbrains.android.anko.generator

import org.jetbrains.android.anko.*
import org.jetbrains.android.anko.utils.MethodNodeWithClass
import org.jetbrains.android.anko.utils.fqName
import org.jetbrains.android.anko.utils.toProperty
import org.objectweb.asm.tree.MethodNode

class PropertyGenerator : Generator<PropertyElement> {

    override fun generate(state: GenerationState) = with (state) {
        val propertyGetters = availableMethods
                .filter {
                    it.clazz.isView &&
                            it.method.isGetter() && !it.method.isOverridden && !it.method.isListenerGetter &&
                            !config.excludedProperties.contains(it.clazz.fqName + "#" + it.method.name) &&
                            !config.excludedProperties.contains(it.clazz.fqName + "#*")
                }
                .sortedBy { it.identifier }

        val propertySetters = availableMethods
                .filter { it.clazz.isView && it.method.isNonListenerSetter() && !it.method.isOverridden }
                .groupBy { it.identifier }

        genProperties(propertyGetters, propertySetters)
    }

    private fun GenerationState.genProperties(
            getters: Collection<MethodNodeWithClass>,
            setters: Map<String, List<MethodNodeWithClass>>): List<PropertyElement> {
        val existingProperties = hashSetOf<String>()

        getters.map { getter ->
            val property = getter.toProperty()
            val settersList = setters.get(property.setterIdentifier) ?: listOf()

            val (best, others) = settersList.partition {
                it.method.args.size() == 1 && it.method.args[0] == getter.method.returnType
            }

            existingProperties.add(property.setterIdentifier)
            PropertyElement(property.name, getter, best + others)
        }
        val propertyWithoutGetters = setters.values().map { setters ->
            val property = setters.first().toProperty()

            val id = property.setterIdentifier
            if (property.propertyFqName in config.propertiesWithoutGetters && id !in existingProperties) {
                PropertyElement(property.name, null, setters)
            } else null
        }.filterNotNull()
        return propertyWithoutGetters
    }

    private val MethodNode.isListenerGetter: Boolean
        get() = name.startsWith("get") && name.endsWith("Listener")
}
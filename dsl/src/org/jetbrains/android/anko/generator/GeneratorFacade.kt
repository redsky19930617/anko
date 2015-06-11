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
import org.jetbrains.android.anko.annotations.ExternalAnnotation
import org.jetbrains.android.anko.config.AnkoConfiguration
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodNode
import org.objectweb.asm.tree.InnerClassNode
import java.util.TreeMap
import java.util.Arrays
import org.jetbrains.android.anko.config.AnkoFile.*
import org.jetbrains.android.anko.config.Configurable
import org.jetbrains.android.anko.config.generate
import org.jetbrains.android.anko.config.generateList
import org.jetbrains.android.anko.generator.*
import org.jetbrains.android.anko.utils.ClassTreeUtils
import org.jetbrains.android.anko.utils.toProperty
import org.objectweb.asm.tree.FieldNode
import org.jetbrains.android.anko.annotations.ExternalAnnotation.GenerateLayout

interface Generator<R> {
    fun generate(state: GenerationState): Iterable<R>
}

class GenerationState(
        public override val classTree: ClassTree,
        config: AnkoConfiguration
): ClassTreeUtils, Configurable(config) {

    private val cachedResults: MutableMap<Class<*>, Any> = hashMapOf()

    public val availableClasses: List<ClassNode> = findAvailableClasses()

    public val availableMethods: List<MethodNodeWithClass> = findAvailableMethods(availableClasses)

    @suppress("UNCHECKED_CAST")
    fun <T> get(generatorClass: Class<out Generator<T>>): Iterable<T> = cachedResults.getOrPut(generatorClass) {
        initializeGenerator(generatorClass).generate(this)
    } as Iterable<T>

    private fun <T> initializeGenerator(generatorClass: Class<out Generator<T>>): Generator<T> {
        try {
            val constructor = generatorClass.getConstructor()
            @suppress("UNCHECKED_CAST")
            return constructor.newInstance()
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("Can't initialize generator ${generatorClass.getName()}", e)
        }
    }

    public override fun isExcluded(node: ClassNode) =
            node.fqName in config.excludedClasses || "${node.packageName}.*" in config.excludedClasses

    public override fun isExcluded(node: MethodNodeWithClass) =
            (node.clazz.fqName + "#" + node.method.name) in config.excludedMethods

}
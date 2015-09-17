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

package org.jetbrains.android.anko.utils

import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.FieldNode

val FieldNode.isPublic: Boolean
    get() = ((access and Opcodes.ACC_PUBLIC) != 0)

val FieldNode.isFinal: Boolean
    get() = ((access and Opcodes.ACC_FINAL) != 0)

val FieldNode.isStatic: Boolean
    get() = ((access and Opcodes.ACC_STATIC) != 0)
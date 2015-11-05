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

package org.jetbrains.anko.db

interface SqlType {
    open val name: String
    open val modifier: String?
}

interface SqlTypeModifier {
    open val modifier: String
}

operator fun SqlType.plus(m: SqlTypeModifier) : SqlType {
    return SqlTypeImpl(name, if (modifier == null) m.toString() else "$modifier $m")
}

val NULL: SqlType = SqlTypeImpl("NULL")
val INTEGER: SqlType = SqlTypeImpl("INTEGER")
val REAL: SqlType = SqlTypeImpl("REAL")
val TEXT: SqlType = SqlTypeImpl("TEXT")

val BLOB: SqlType = SqlTypeImpl("BLOB")

fun FOREIGN_KEY(columnName: String, referenceTable: String, referenceColumn: String): SqlType {
    return SqlTypeImpl("FOREIGN KEY($columnName) REFERENCES $referenceTable($referenceColumn)")
}

val PRIMARY_KEY: SqlTypeModifier = SqlTypeModifierImpl("PRIMARY KEY")
val NOT_NULL: SqlTypeModifier = SqlTypeModifierImpl("NOT_NULL")
val AUTOINCREMENT: SqlTypeModifier = SqlTypeModifierImpl("AUTOINCREMENT")
val UNIQUE: SqlTypeModifier = SqlTypeModifierImpl("UNIQUE")

fun DEFAULT(value: String): SqlTypeModifier = SqlTypeModifierImpl("DEFAULT $value")

private open class SqlTypeImpl(name: String, modifier: String? = null) : SqlType {
    override val name: String = name

    override val modifier: String? = modifier
    override fun toString(): String {
        return if (modifier == null) name else "$name $modifier"
    }
}

private open class SqlTypeModifierImpl(modifier: String) : SqlTypeModifier {
    override val modifier: String = modifier
    override fun toString(): String = modifier
}

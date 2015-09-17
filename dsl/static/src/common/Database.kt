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

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.concurrent.atomic.AtomicInteger
import java.util.regex.Pattern

public enum class SqlOrderDirection { ASC, DESC }

public class TransactionAbortException : RuntimeException()

public fun SQLiteDatabase.insert(tableName: String, vararg values: Pair<String, Any>): Long {
    return insert(tableName, null, values.toContentValues())
}

public fun SQLiteDatabase.insertOrThrow(tableName: String, vararg values: Pair<String, Any>): Long {
    return insertOrThrow(tableName, null, values.toContentValues())
}

public fun SQLiteDatabase.replace(tableName: String, vararg values: Pair<String, Any>): Long {
    return replace(tableName, null, values.toContentValues())
}

public fun SQLiteDatabase.replaceOrThrow(tableName: String, vararg values: Pair<String, Any>): Long {
    return replaceOrThrow(tableName, null, values.toContentValues())
}

public fun SQLiteDatabase.transaction(code: SQLiteDatabase.() -> Unit) {
    try {
        beginTransaction()
        code()
        setTransactionSuccessful()
    } catch (e: TransactionAbortException) {
        // Do nothing, just stop the transaction
    } finally {
        endTransaction()
    }
}

public fun SQLiteDatabase.select(tableName: String): SelectQueryBuilder {
    return SelectQueryBuilder(this, tableName)
}

public fun SQLiteDatabase.select(tableName: String, vararg columns: String): SelectQueryBuilder {
    val builder = SelectQueryBuilder(this, tableName)
    builder.columns(*columns)
    return builder
}

public fun SQLiteDatabase.update(tableName: String, vararg values: Pair<String, Any>): UpdateQueryBuilder {
    return UpdateQueryBuilder(this, tableName, values)
}

public fun SQLiteDatabase.delete(tableName: String, whereClause: String = "", vararg args: Pair<String, Any>): Int {
    return delete(tableName, applyArguments(whereClause, *args), null)
}

public fun SQLiteDatabase.createTable(tableName: String, ifNotExists: Boolean = false, vararg columns: Pair<String, SqlType>) {
    val escapedTableName = tableName.replace("`", "``")
    val ifNotExistsText = if (ifNotExists) "IF NOT EXISTS" else ""
    execSQL(
        columns.map { col ->
            "${col.first} ${col.second}"
        }.joinToString(", ", prefix = "CREATE TABLE $ifNotExistsText `$escapedTableName`(", postfix = ");")
    )
}

public fun SQLiteDatabase.dropTable(tableName: String, ifExists: Boolean = false) {
    val escapedTableName = tableName.replace("`", "``")
    val ifExistsText = if (ifExists) "IF EXISTS" else ""
    execSQL("DROP TABLE $ifExistsText `$escapedTableName`;")
}

private val ARG_PATTERN: Pattern = Pattern.compile("([^\\\\])\\{([^\\{}]+)\\}")

internal fun applyArguments(whereClause: String, vararg args: Pair<String, Any>): String {
    val argsMap = args.fold(hashMapOf<String, Any>()) { map, arg ->
        map.put(arg.first, arg.second)
        map
    }
    return applyArguments(whereClause, argsMap)
}

fun applyArguments(whereClause: String, args: Map<String, Any>): String {
    val matcher = ARG_PATTERN.matcher(whereClause)
    val buffer = StringBuffer(whereClause.length())
    while (matcher.find()) {
        val key = matcher.group(2)
        val value = args.get(key) ?: throw IllegalStateException("Can't find a value for key $key")

        val valueString = if (value is Int || value is Long || value is Byte || value is Short) {
            value.toString()
        } else if (value is Boolean) {
            if (value) "1" else "0"
        } else if (value is Float || value is Double) {
            value.toString()
        } else {
            '\'' + value.toString().replace("'", "''") + '\''
        }
        matcher.appendReplacement(buffer, matcher.group(1) + valueString)
    }
    matcher.appendTail(buffer)
    return buffer.toString()
}

internal fun Array<out Pair<String, Any>>.toContentValues(): ContentValues {
    val values = ContentValues()
    for ((key, value) in this) {
        when(value) {
            is Boolean -> values.put(key, value)
            is Byte -> values.put(key, value)
            is ByteArray -> values.put(key, value)
            is Double -> values.put(key, value)
            is Float -> values.put(key, value)
            is Int -> values.put(key, value)
            is Long -> values.put(key, value)
            is Short -> values.put(key, value)
            is String -> values.put(key, value)
            else -> throw IllegalArgumentException("Non-supported value type: ${value.javaClass.name}")
        }
    }
    return values
}

public abstract class ManagedSQLiteOpenHelper(
    ctx: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = 1
): SQLiteOpenHelper(ctx, name, factory, version) {

    private val counter = AtomicInteger()
    private var db: SQLiteDatabase? = null

    public fun <T> use(f: SQLiteDatabase.() -> T): T {
        try {
            return openDatabase().f()
        } finally {
            closeDatabase()
        }
    }

    @Synchronized
    private fun openDatabase(): SQLiteDatabase {
        if (counter.incrementAndGet() == 1) {
            db = writableDatabase
        }
        return db!!
    }

    @Synchronized
    private fun closeDatabase() {
        if (counter.decrementAndGet() == 0) {
            db?.close()
        }
    }
}

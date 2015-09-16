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

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.AnkoException
import org.jetbrains.anko.internals.AnkoInternals

public class SelectQueryBuilder(val db: SQLiteDatabase, val tableName: String) {
    private val columns = arrayListOf<String>()
    private val groupBy = arrayListOf<String>()
    private val orderBy = arrayListOf<String>()

    private var distinct: Boolean = false

    private var havingApplied = false
    private var having: String? = null
    private var limit: String? = null

    private var selectionApplied = false
    private var useNativeSelection = false
    private var selection: String? = null
    private var nativeSelectionArgs: Array<out String>? = null

    public fun <T> exec(f: Cursor.() -> T): T {
        val cursor = execInternal()
        return AnkoInternals.useCursor(cursor) {
            cursor.f()
        }
    }

    public fun <T: Any> parseSingle(parser: RowParser<T>): T = AnkoInternals.useCursor(execInternal()) {
        it.parseSingle(parser)
    }

    public fun <T: Any> parseOpt(parser: RowParser<T>): T? = AnkoInternals.useCursor(execInternal()) {
        it.parseOpt(parser)
    }

    public fun <T: Any> parseList(parser: RowParser<T>): List<T> = AnkoInternals.useCursor(execInternal()) {
        it.parseList(parser)
    }

    public fun <T: Any> parseSingle(parser: MapRowParser<T>): T = AnkoInternals.useCursor(execInternal()) {
        it.parseSingle(parser)
    }

    public fun <T: Any> parseOpt(parser: MapRowParser<T>): T? = AnkoInternals.useCursor(execInternal()) {
        it.parseOpt(parser)
    }

    public fun <T: Any> parseList(parser: MapRowParser<T>): List<T> = AnkoInternals.useCursor(execInternal()) {
        it.parseList(parser)
    }

    private fun execInternal(): Cursor {
        val finalSelection = if (selectionApplied) selection else null
        val finalSelectionArgs = if (selectionApplied && useNativeSelection) nativeSelectionArgs else null
        return db.query(distinct, tableName, columns.toTypedArray(),
                finalSelection, finalSelectionArgs,
                groupBy.joinToString(", "), having, orderBy.joinToString(", "), limit)
    }

    fun distinct(): SelectQueryBuilder {
        this.distinct = true
        return this
    }

    public fun column(name: String): SelectQueryBuilder {
        columns.add(name)
        return this
    }

    public fun groupBy(value: String): SelectQueryBuilder {
        groupBy.add(value)
        return this
    }

    public fun orderBy(value: String, direction: SqlOrderDirection = SqlOrderDirection.ASC): SelectQueryBuilder {
        if (direction == SqlOrderDirection.DESC) {
            orderBy.add("$value DESC")
        } else {
            orderBy.add(value)
        }
        return this
    }

    public fun limit(count: Int): SelectQueryBuilder {
        limit = count.toString()
        return this
    }

    public fun limit(offset: Int, count: Int): SelectQueryBuilder {
        limit = "$offset, $count"
        return this
    }

    public fun columns(vararg names: String): SelectQueryBuilder {
        columns.addAll(names)
        return this
    }

    public fun having(having: String): SelectQueryBuilder {
        if (havingApplied) {
            throw AnkoException("Query having was already applied.")
        }

        havingApplied = true
        this.having = having
        return this
    }

    public fun having(having: String, vararg args: Pair<String, Any>): SelectQueryBuilder {
        if (selectionApplied) {
            throw AnkoException("Query having was already applied.")
        }

        havingApplied = true
        this.having = applyArguments(having, *args)
        return this
    }

    public fun where(select: String, vararg args: Pair<String, Any>): SelectQueryBuilder {
        if (selectionApplied) {
            throw AnkoException("Query selection was already applied.")
        }

        selectionApplied = true
        useNativeSelection = false
        selection = applyArguments(select, *args)
        return this
    }

    public fun where(select: String): SelectQueryBuilder {
        if (selectionApplied) {
            throw AnkoException("Query selection was already applied.")
        }

        selectionApplied = true
        useNativeSelection = false
        selection = select
        return this
    }

    public fun `where?`(select: String, vararg args: String): SelectQueryBuilder {
        if (selectionApplied) {
            throw AnkoException("Query selection was already applied.")
        }

        selectionApplied = true
        useNativeSelection = true
        selection = select
        nativeSelectionArgs = args
        return this
    }

    @deprecated("Use `where?`() instead", replaceWith = ReplaceWith("`where?`(select, *args)"))
    public fun whereSupport(select: String, vararg args: String): SelectQueryBuilder {
        return `where?`(select, *args)
    }
}
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

@file:Suppress("NOTHING_TO_INLINE")
package org.jetbrains.anko

import android.app.Fragment
import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text resource.
 */
inline fun AnkoContext<*>.toast(message: Int) = ctx.toast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text resource.
 */
inline fun Fragment.toast(message: Int): Unit = activity.toast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text resource.
 */
fun Context.toast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text.
 */
inline fun AnkoContext<*>.toast(message: CharSequence) = ctx.toast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text.
 */
inline fun Fragment.toast(message: CharSequence): Unit = activity.toast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] duration.
 *
 * @param message the message text.
 */
fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text resource.
 */
inline fun AnkoContext<*>.longToast(message: Int) = ctx.longToast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text resource.
 */
inline fun Fragment.longToast(message: Int): Unit = activity.longToast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text resource.
 */
fun Context.longToast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text.
 */
inline fun AnkoContext<*>.longToast(message: CharSequence) = ctx.longToast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text.
 */
inline fun Fragment.longToast(message: CharSequence): Unit = activity.longToast(message)

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] duration.
 *
 * @param message the message text.
 */
fun Context.longToast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()


/* Alerts */

inline fun AnkoContext<*>.alert(
        message: String,
        title: String? = null,
        noinline init: (AlertDialogBuilder.() -> Unit)? = null
) = ctx.alert(message, title, init)

inline fun Fragment.alert(
        message: String,
        title: String? = null,
        noinline init: (AlertDialogBuilder.() -> Unit)? = null
) = activity.alert(message, title, init)

fun Context.alert(
        message: String,
        title: String? = null,
        init: (AlertDialogBuilder.() -> Unit)? = null
) = AlertDialogBuilder(this).apply {
    if (title != null) title(title)
    message(message)
    if (init != null) init()
}


inline fun AnkoContext<*>.alert(
        message: Int,
        title: Int? = null,
        noinline init: (AlertDialogBuilder.() -> Unit)? = null
) = ctx.alert(message, title, init)

inline fun Fragment.alert(
        message: Int,
        title: Int? = null,
        noinline init: (AlertDialogBuilder.() -> Unit)? = null
) = activity.alert(message, title, init)

fun Context.alert(
        message: Int,
        title: Int? = null,
        init: (AlertDialogBuilder.() -> Unit)? = null
) = AlertDialogBuilder(this).apply {
    if (title != null) title(title)
    message(message)
    if (init != null) init()
}


inline fun AnkoContext<*>.alert(noinline init: AlertDialogBuilder.() -> Unit) = ctx.alert(init)
inline fun Fragment.alert(noinline init: AlertDialogBuilder.() -> Unit): AlertDialogBuilder = activity.alert(init)

fun Context.alert(init: AlertDialogBuilder.() -> Unit) = AlertDialogBuilder(this).apply { init() }


/* Progress dialogs */

inline fun AnkoContext<*>.progressDialog(
        message: Int? = null,
        title: Int? = null,
        noinline init: (ProgressDialog.() -> Unit)? = null
) = ctx.progressDialog(message, title, init)

inline fun Fragment.progressDialog(
        message: Int? = null,
        title: Int? = null,
        noinline init: (ProgressDialog.() -> Unit)? = null
) = activity.progressDialog(message, title, init)

fun Context.progressDialog(
        message: Int? = null,
        title: Int? = null,
        init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(false, message?.let { getString(it) }, title?.let { getString(it) }, init)


inline fun AnkoContext<*>.indeterminateProgressDialog(
        message: Int? = null,
        title: Int? = null,
        noinline init: (ProgressDialog.() -> Unit)? = null
) = ctx.indeterminateProgressDialog(message, title, init)

inline fun Fragment.indeterminateProgressDialog(
        message: Int? = null,
        title: Int? = null,
        noinline init: (ProgressDialog.() -> Unit)? = null
) = activity.progressDialog(message, title, init)

fun Context.indeterminateProgressDialog(
        message: Int? = null,
        title: Int? = null,
        init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(true, message?.let { getString(it) }, title?.let { getString(it) }, init)


inline fun AnkoContext<*>.progressDialog(
        message: String? = null,
        title: String? = null,
        noinline init: (ProgressDialog.() -> Unit)? = null
) = ctx.progressDialog(message, title, init)

inline fun Fragment.progressDialog(
        message: String? = null,
        title: String? = null,
        noinline init: (ProgressDialog.() -> Unit)? = null
) = activity.progressDialog(message, title, init)

fun Context.progressDialog(
        message: String? = null,
        title: String? = null,
        init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(false, message, title, init)


inline fun AnkoContext<*>.indeterminateProgressDialog(
        message: String? = null,
        title: String? = null,
        noinline init: (ProgressDialog.() -> Unit)? = null
) = ctx.indeterminateProgressDialog(message, title, init)

inline fun Fragment.indeterminateProgressDialog(
        message: String? = null,
        title: String? = null,
        noinline init: (ProgressDialog.() -> Unit)? = null
) = activity.indeterminateProgressDialog(message, title, init)

fun Context.indeterminateProgressDialog(
        message: String? = null,
        title: String? = null,
        init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(true, message, title, init)


private fun Context.progressDialog(
        indeterminate: Boolean,
        message: String? = null,
        title: String? = null,
        init: (ProgressDialog.() -> Unit)? = null
) = ProgressDialog(this).apply {
    isIndeterminate = indeterminate
    if (!indeterminate) setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
    if (message != null) setMessage(message)
    if (title != null) setTitle(title)
    if (init != null) init()
    show()
}


/* Selectors */

inline fun AnkoContext<*>.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        noinline onClick: (Int) -> Unit
): Unit = ctx.selector(title, items, onClick)

inline fun Fragment.selector(
        title: CharSequence? = null,
        items: List<CharSequence>,
        noinline onClick: (Int) -> Unit
): Unit = activity.selector(title, items, onClick)

fun Context.selector(
    title: CharSequence? = null,
    items: List<CharSequence>,
    onClick: (Int) -> Unit
) {
    with(AlertDialogBuilder(this)) {
        if (title != null) title(title)
        items(items, onClick)
        show()
    }
}
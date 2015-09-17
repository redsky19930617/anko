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

package org.jetbrains.anko

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import org.jetbrains.anko.custom.addView
import org.jetbrains.anko.internals.NoBinding

public fun <T : View> T.style(style: (View) -> Unit): T {
    applyStyle(this, style)
    return this
}

//deprecated("Use ViewManager.addView() instead")
public fun <T : View> __dslAddView(view: (ctx: Context) -> T, init: T.() -> Unit, manager: ViewManager): T {
    return manager.addView<T> { ctx ->
        val v = view(ctx)
        v.init()
        v
    }
}

//deprecated("Use Context.addView() instead")
public fun <T : View> __dslAddView(view: (ctx: Context) -> T, init: T.() -> Unit, ctx: Context): T {
    return ctx.addView { ctx ->
        val v = view(ctx)
        v.init()
        v
    }
}

//deprecated("Use Activity.addView() instead")
public fun <T : View> __dslAddView(view: (ctx: Context) -> T, init: T.() -> Unit, act: Activity): T {
    return act.addView { ctx ->
        val v = view(ctx)
        v.init()
        v
    }
}

//deprecated("Use Fragment.addView() instead")
public fun <T : View> __dslAddView(view: (ctx: Context) -> T, init: T.() -> Unit, fragment: Fragment): T {
    return fragment.addView { ctx ->
        val v = view(ctx)
        v.init()
        v
    }
}

private fun applyStyle(v: View, style: (View) -> Unit) {
    style(v)
    if (v is ViewGroup) {
        val maxIndex = v.childCount - 1
        for (i in 0 .. maxIndex) {
            v.getChildAt(i)?.let { applyStyle(it, style) }
        }
    }
}

@NoBinding
public fun Context.UI(setContentView: Boolean, init: UiHelper.() -> Unit): UiHelper {
    val dsl = UiHelper(this, setContentView)
    dsl.init()
    return dsl
}

public fun Context.UI(init: UiHelper.() -> Unit): UiHelper {
    val dsl = UiHelper(this, false)
    dsl.init()
    return dsl
}

public fun Activity.UI(init: UiHelper.() -> Unit): UiHelper = UI(true, init)

public fun Fragment.UI(init: UiHelper.() -> Unit): UiHelper = activity.UI(false, init)

public class UiHelper(public val ctx: Context, private val setContentView: Boolean = true) : ViewManager {
    private lateinit var view: View

    fun toView() = view

    override fun addView(view: View, params: ViewGroup.LayoutParams?) {
        this.view = view
        if (setContentView) {
            @Suppress("USELESS_CAST_STATIC_ASSERT_IS_FINE")
            when (ctx) {
                is Activity -> ctx.setContentView(view)
                else -> {
                }
            }
        }
    }

    override fun updateViewLayout(view: View, params: ViewGroup.LayoutParams) {
        throw UnsupportedOperationException()
    }

    override fun removeView(view: View) {
        throw UnsupportedOperationException()
    }
}
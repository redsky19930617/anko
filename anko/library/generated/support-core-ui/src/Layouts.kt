@file:JvmName("SupportCoreUiLayoutsKt")
package org.jetbrains.anko.support.core.ui


import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.support.v4.view.PagerTitleStrip
import android.view.View
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.widget.FrameLayout
import android.support.v4.widget.NestedScrollView
import android.support.v4.widget.SlidingPaneLayout

private val defaultInit: Any.() -> Unit = {}

open class _PagerTitleStrip(ctx: Context): PagerTitleStrip(ctx) {
    fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?,
            init: ViewGroup.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = ViewGroup.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: ViewGroup.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = ViewGroup.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?,
            init: ViewGroup.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = ViewGroup.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

}

open class _ViewPager(ctx: Context): ViewPager(ctx) {
    fun <T: View> T.lparams(
            init: ViewPager.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = ViewPager.LayoutParams()
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            context: Context?,
            attrs: AttributeSet?,
            init: ViewPager.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = ViewPager.LayoutParams(context!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

}

open class _DrawerLayout(ctx: Context): DrawerLayout(ctx) {
    fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?,
            init: DrawerLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: DrawerLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity: Int,
            init: DrawerLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(width, height, gravity)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: DrawerLayout.LayoutParams?,
            init: DrawerLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?,
            init: DrawerLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?,
            init: DrawerLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = DrawerLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

}

open class _NestedScrollView(ctx: Context): NestedScrollView(ctx) {
    fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?,
            init: FrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: FrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity: Int,
            init: FrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(width, height, gravity)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?,
            init: FrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?,
            init: FrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: FrameLayout.LayoutParams?,
            init: FrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = FrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

}

open class _SlidingPaneLayout(ctx: Context): SlidingPaneLayout(ctx) {
    fun <T: View> T.lparams(
            init: SlidingPaneLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = SlidingPaneLayout.LayoutParams()
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: SlidingPaneLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = SlidingPaneLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: ViewGroup.LayoutParams?,
            init: SlidingPaneLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = SlidingPaneLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: ViewGroup.MarginLayoutParams?,
            init: SlidingPaneLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = SlidingPaneLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: SlidingPaneLayout.LayoutParams?,
            init: SlidingPaneLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = SlidingPaneLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            c: Context?,
            attrs: AttributeSet?,
            init: SlidingPaneLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = SlidingPaneLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

}


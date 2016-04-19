private val defaultInit: Any.() -> Unit = {}

open class _PercentFrameLayout(ctx: Context): android.support.percent.PercentFrameLayout(ctx) {
    fun <T: View> T.lparams(
            c: android.content.Context?,
            attrs: android.util.AttributeSet?,
            init: android.support.percent.PercentFrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentFrameLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: android.support.percent.PercentFrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentFrameLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            gravity: Int,
            init: android.support.percent.PercentFrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentFrameLayout.LayoutParams(width, height, gravity)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: android.view.ViewGroup.LayoutParams?,
            init: android.support.percent.PercentFrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentFrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: android.view.ViewGroup.MarginLayoutParams?,
            init: android.support.percent.PercentFrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentFrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: android.widget.FrameLayout.LayoutParams?,
            init: android.support.percent.PercentFrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentFrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: android.support.percent.PercentFrameLayout.LayoutParams?,
            init: android.support.percent.PercentFrameLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentFrameLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

}

open class _PercentRelativeLayout(ctx: Context): android.support.percent.PercentRelativeLayout(ctx) {
    fun <T: View> T.lparams(
            c: android.content.Context?,
            attrs: android.util.AttributeSet?,
            init: android.support.percent.PercentRelativeLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentRelativeLayout.LayoutParams(c!!, attrs!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: android.support.percent.PercentRelativeLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentRelativeLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: android.view.ViewGroup.LayoutParams?,
            init: android.support.percent.PercentRelativeLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentRelativeLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

    fun <T: View> T.lparams(
            source: android.view.ViewGroup.MarginLayoutParams?,
            init: android.support.percent.PercentRelativeLayout.LayoutParams.() -> Unit = defaultInit
    ): T {
        val layoutParams = android.support.percent.PercentRelativeLayout.LayoutParams(source!!)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }

}
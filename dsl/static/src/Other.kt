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

package kotlinx.android.anko

import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.ImageView
import android.widget.TextView
import android.content.Context
import android.app.Fragment
import android.app.Activity
import android.content.BroadcastReceiver
import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.RelativeLayout
import android.text.*
import android.util.TypedValue
import kotlinx.android.anko.internals.UiHelper

/* SECTION COLORS */
//returns 0xC0C0C0 for 0xC0
public val Int.gray: Int
    get() = this or (this shl 8) or (this shl 16)

//returns 0xFFABCDEF for 0xABCDEF
public val Int.opaque: Int
    get() = this or 0xff000000.toInt()
/* END SECTION */


/* SECTION CUSTOM VIEWS */
private val verticalLayoutFactory = { ctx: Context ->
    val v = _LinearLayout(ctx)
    v.setOrientation(LinearLayout.VERTICAL)
    v
}

[suppress("UNCHECKED_CAST")]
private fun <T: View> inflaterFactory(layoutId: Int) = { ctx: Context ->
    ctx.layoutInflater.inflate(layoutId, null) as T
}

public fun ViewManager.verticalLayout(init: _LinearLayout.() -> Unit): LinearLayout =
    __dslAddView(verticalLayoutFactory, init, this): LinearLayout

public fun Fragment.verticalLayout(init: _LinearLayout.() -> Unit): LinearLayout =
    __dslAddView(verticalLayoutFactory, init, this): LinearLayout

public fun Context.verticalLayout(init: _LinearLayout.() -> Unit): LinearLayout =
    __dslAddView(verticalLayoutFactory, init, this): LinearLayout

public fun Activity.verticalLayout(init: _LinearLayout.() -> Unit): LinearLayout =
        __dslAddView(verticalLayoutFactory, init, this): LinearLayout

public fun <T: View> ViewManager.include(layoutId: Int, init: T.() -> Unit): T =
    __dslAddView(inflaterFactory(layoutId), init, this)
/* END SECTION */


/* SECTION CUSTOM VIEW PROPERTIES */
public var View.backgroundColor: Int
    get() = throw AnkoException("'padding' property doesn't have a getter")
    set(value) {this.setBackgroundColor(value)}

public var View.backgroundResource: Int
    get() = throw AnkoException("'padding' property doesn't have a getter")
    set(value) {
        this.setBackgroundResource(value)
    }

public var View.background: Drawable
    get() = getBackground()
    set(value) = this.setBackgroundDrawable(value)

public var View.paddingLeft: Int
    get() = this.getPaddingLeft()
    set(value) {
        this.setPadding(value, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom())
    }

public var View.paddingTop: Int
    get() = this.getPaddingTop()
    set(value) {
        this.setPadding(this.getPaddingLeft(), value, this.getPaddingRight(), this.getPaddingBottom())
    }

public var View.paddingRight: Int
    get() = this.getPaddingRight()
    set(value) {
        this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), value, this.getPaddingBottom())
    }

public var View.paddingBottom: Int
    get() = this.getPaddingBottom()
    set(value) {
        this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), value)
    }

public var View.paddingHorizontal: Int
    get() = throw AnkoException("'paddingHorizontal' property doesn't have a getter")
    set(value) {
        this.setPadding(value, this.getPaddingTop(), value, this.getPaddingBottom())
    }

public var View.paddingVertical: Int
    get() = throw AnkoException("'paddingVertical' property doesn't have a getter")
    set(value) {
        this.setPadding(this.getPaddingLeft(), value, this.getPaddingRight(), value)
    }

public var View.padding: Int
    get() = throw AnkoException("'padding' property doesn't have a getter")
    set(value) {
        this.setPadding(value, value, value, value)
    }

public var LinearLayout.gravity: Int
    get() = throw AnkoException("'gravity' property doesn't have a getter")
    set(value) {
        this.setGravity(value)
    }

public var TextView.isSelectable: Boolean
    get() = isTextSelectable()
    set(value) {
        this.setTextIsSelectable(value)
    }

public var TextView.textColor: Int
    get() = throw AnkoException("'textColor' property doesn't have a getter")
    set(value) {
        this.setTextColor(value)
    }

public var TextView.textSizeDimen: Int
    get() = throw AnkoException("'textSizeDimen' property doesn't have a getter")
    set(value) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(value))
    }

public var ImageView.image: Drawable?
    get() = this.getDrawable()
    set(value) {
        this.setImageDrawable(value)
    }

public var ImageView.imageResource: Int
    get() = throw AnkoException("'textColor' property doesn't have a getter")
    set(value) {
        this.setImageResource(value)
    }
/* END SECTION */


/* SECTION CUSTOM LAYOUT PROPERTIES */
public val matchParent: Int = android.view.ViewGroup.LayoutParams.MATCH_PARENT
public val wrapContent: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT

public var LinearLayout.LayoutParams.margin: Int
    get() = throw AnkoException("'LinearLayout.LayoutParams.margin' property doesn't have a getter")
    set(v) {
        leftMargin = v
        rightMargin = v
        topMargin = v
        bottomMargin = v
    }

public var LinearLayout.LayoutParams.verticalMargin: Int
    get() = throw AnkoException("'LinearLayout.LayoutParams.verticalMargin' property doesn't have a getter")
    set(v) {
        topMargin = v
        bottomMargin = v
    }

public var LinearLayout.LayoutParams.horizontalMargin: Int
    get() = throw AnkoException("'LinearLayout.LayoutParams.horizontalMargin' property doesn't have a getter")
    set(v) {
        leftMargin = v; rightMargin = v
    }

public var RelativeLayout.LayoutParams.margin: Int
    get() = throw AnkoException("'RelativeLayout.LayoutParams.margin' property doesn't have a getter")
    set(v) {
        leftMargin = v
        rightMargin = v
        topMargin = v
        bottomMargin = v
    }

public var RelativeLayout.LayoutParams.verticalMargin: Int
    get() = throw AnkoException("'RelativeLayout.LayoutParams.verticalMargin' property doesn't have a getter")
    set(v) {
        topMargin = v
        bottomMargin = v
    }

public var RelativeLayout.LayoutParams.horizontalMargin: Int
    get() = throw AnkoException("'RelativeLayout.LayoutParams.horizontalMargin' property doesn't have a getter")
    set(v) {
        leftMargin = v; rightMargin = v
    }

[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.topOf(id: Int): Unit = addRule(android.widget.RelativeLayout.ABOVE, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.above(id: Int): Unit = addRule(android.widget.RelativeLayout.ABOVE, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.below(id: Int): Unit = addRule(android.widget.RelativeLayout.BELOW, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.bottomOf(id: Int): Unit = addRule(android.widget.RelativeLayout.BELOW, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.leftOf(id: Int): Unit = addRule(android.widget.RelativeLayout.LEFT_OF, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.rightOf(id: Int): Unit = addRule(android.widget.RelativeLayout.RIGHT_OF, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.sameLeft(id: Int): Unit = addRule(android.widget.RelativeLayout.ALIGN_LEFT, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.sameTop(id: Int): Unit = addRule(android.widget.RelativeLayout.ALIGN_TOP, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.sameRight(id: Int): Unit = addRule(android.widget.RelativeLayout.ALIGN_RIGHT, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.sameBottom(id: Int): Unit = addRule(android.widget.RelativeLayout.ALIGN_BOTTOM, id)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.alignParentTop(): Unit = addRule(android.widget.RelativeLayout.ALIGN_PARENT_TOP)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.alignParentLeft(): Unit = addRule(android.widget.RelativeLayout.ALIGN_PARENT_LEFT)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.alignParentBottom(): Unit = addRule(android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.alignParentRight(): Unit = addRule(android.widget.RelativeLayout.ALIGN_PARENT_RIGHT)

// Unavailable in older versions of SDK
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.alignParentStart(): Unit = addRule(20) //android.widget.RelativeLayout.ALIGN_PARENT_START)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.alignParentEnd(): Unit = addRule(21) //android.widget.RelativeLayout.ALIGN_PARENT_END)

[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.centerHorizontally(): Unit = addRule(android.widget.RelativeLayout.CENTER_HORIZONTAL)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.centerVertically(): Unit = addRule(android.widget.RelativeLayout.CENTER_VERTICAL)
[suppress("NOTHING_TO_INLINE")]
public inline fun android.widget.RelativeLayout.LayoutParams.centerInParent(): Unit = addRule(android.widget.RelativeLayout.CENTER_IN_PARENT)
/* END SECTION */

/* SECTION SERVICES */
public val Context.vibrator: android.os.Vibrator
    get() = getSystemService(Context.VIBRATOR_SERVICE) as android.os.Vibrator
public val Context.layoutInflater: android.view.LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater
/* END SECTION */
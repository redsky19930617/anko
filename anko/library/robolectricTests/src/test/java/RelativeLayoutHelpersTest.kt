package test

import android.app.*
import android.widget.*
import android.os.Bundle
import org.jetbrains.anko.*
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.*
import org.junit.Test
import org.junit.Assert.*
import android.view.View

public open class RelativeLayoutHelpersTestActivity() : Activity()

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class RelativeLayoutHelpersTest() {

    @Test
    public fun test() {
        val activity = Robolectric.buildActivity(RelativeLayoutHelpersTestActivity::class.java).create().get()
        test(activity)

        println("[COMPLETE]")
    }

}

fun test(activity: Activity) {
    val fst = 50
    val snd = 60
    with (activity) {
        fun get(rule: Int, l: RelativeLayout.LayoutParams.(View) -> Unit) = test(fst, snd, l).rules[rule]
        assertTrue(get(RelativeLayout.BELOW) { below(fst) } == fst)
        assertTrue(get(RelativeLayout.BELOW) { bottomOf(fst) } == fst)
        assertTrue(get(RelativeLayout.ABOVE) { above(fst) } == fst)
        assertTrue(get(RelativeLayout.ABOVE) { topOf(fst) } == fst)
        assertTrue(get(RelativeLayout.LEFT_OF) { leftOf(fst) } == fst)
        assertTrue(get(RelativeLayout.RIGHT_OF) { rightOf(fst) } == fst)

        assertTrue(test(fst, snd) { below(it) }.rules[RelativeLayout.BELOW] == fst)

        assertTrue(get(RelativeLayout.ALIGN_LEFT) { sameLeft(fst) } == fst)
        assertTrue(get(RelativeLayout.ALIGN_TOP) { sameTop(fst) } == fst)
        assertTrue(get(RelativeLayout.ALIGN_RIGHT) { sameRight(fst) } == fst)
        assertTrue(get(RelativeLayout.ALIGN_BOTTOM) { sameBottom(fst) } == fst)

        assertTrue(get(RelativeLayout.ALIGN_PARENT_BOTTOM) { alignParentBottom() } == RelativeLayout.TRUE)
        assertTrue(get(RelativeLayout.ALIGN_PARENT_TOP) { alignParentBottom() } == 0)
        assertTrue(get(RelativeLayout.ALIGN_PARENT_LEFT) { alignParentLeft() } == RelativeLayout.TRUE)
        assertTrue(get(RelativeLayout.ALIGN_PARENT_RIGHT) { alignParentRight() } == RelativeLayout.TRUE)
        assertTrue(get(RelativeLayout.ALIGN_PARENT_START) { alignParentStart() } == RelativeLayout.TRUE)
        assertTrue(get(RelativeLayout.ALIGN_PARENT_END) { alignParentEnd() } == RelativeLayout.TRUE)
        assertTrue(get(RelativeLayout.CENTER_VERTICAL) { centerVertically() } == RelativeLayout.TRUE)
        assertTrue(get(RelativeLayout.CENTER_HORIZONTAL) { centerHorizontally() } == RelativeLayout.TRUE)
        assertTrue(get(RelativeLayout.CENTER_IN_PARENT) { centerInParent() } == RelativeLayout.TRUE)

        val compoundRules = test(fst, snd) { alignParentTop(); alignParentLeft(); above(it); sameLeft(it) }.rules
        assertTrue(compoundRules[RelativeLayout.ALIGN_PARENT_TOP] == RelativeLayout.TRUE)
        assertTrue(compoundRules[RelativeLayout.ALIGN_PARENT_LEFT] == RelativeLayout.TRUE)
        assertTrue(compoundRules[RelativeLayout.ALIGN_PARENT_BOTTOM] == 0)
        assertTrue(compoundRules[RelativeLayout.ABOVE] == fst)
        assertTrue(compoundRules[RelativeLayout.ALIGN_LEFT] == fst)
    }
}

fun Activity.test(fst: Int, snd: Int, l: RelativeLayout.LayoutParams.(View) -> Unit) = (UI {
    relativeLayout {
        val fstView = textView {
            id = fst
        }
        textView {
            id = snd
        }.lparams { l(fstView) }
    }
}.view as RelativeLayout).findViewById(snd)?.layoutParams as RelativeLayout.LayoutParams
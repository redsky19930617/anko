package test

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import org.jetbrains.anko.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import org.robolectric.internal.ShadowExtractor
import org.robolectric.shadows.ShadowAlertDialog
import org.robolectric.shadows.ShadowHandler
import org.robolectric.shadows.ShadowToast

public open class DialogsTestActivity() : Activity() {
    public var dialog: AlertDialog? = null

    public override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)

        verticalLayout {
            button {
                id = 1
                onClick { toast("Button 1 toast") }
            }
            button {
                id = 2
                onClick { longToast("Button 2 toast") }
            }
            button {
                id = 3
                onClick {
                    alert("Message", "Title") {
                        positiveButton("Positive") {}
                        negativeButton("Negative") {}
                    }.show()
                }
            }
        }
    }
}

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class DialogsTest() {

    @Test
    public fun test() {
        val activity = Robolectric.buildActivity(DialogsTestActivity::class.java).create().get()
        val button1 = activity.findViewById(1) as Button
        val button2 = activity.findViewById(2) as Button
        val button3 = activity.findViewById(3) as Button

        button1.performClick()
        ShadowHandler.idleMainLooper()
        assertEquals("Button 1 toast", ShadowToast.getTextOfLatestToast())

        button2.performClick()
        ShadowHandler.idleMainLooper()
        assertEquals("Button 2 toast", ShadowToast.getTextOfLatestToast())

        assertEquals(2, ShadowToast.shownToastCount())

        button3.performClick()
        val alert = ShadowAlertDialog.getLatestAlertDialog()
        assertNotNull(alert)
        val alertShadow = ShadowExtractor.extract(alert) as ShadowAlertDialog

        assertEquals("Message", alertShadow.getMessage().toString())
        assertEquals("Title", alertShadow.getTitle().toString())
        assertEquals(true, alertShadow.isCancelable())
        assertEquals(View.VISIBLE, alert.getButton(DialogInterface.BUTTON_POSITIVE).visibility)
        assertEquals(View.VISIBLE, alert.getButton(DialogInterface.BUTTON_NEGATIVE).visibility)
        assertEquals(View.GONE, alert.getButton(DialogInterface.BUTTON_NEUTRAL).visibility)
        alert.dismiss()

        println("[COMPLETE]")
    }

}
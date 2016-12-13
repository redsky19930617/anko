package test

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.vibrator
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config

public open class ServiceTestActivity() : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        linearLayout {}
    }
}

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class ServiceTest() {

    @Test
    public fun test() {
        val activity = Robolectric.buildActivity(ServiceTestActivity::class.java).create().get()

        val vibrator = activity.vibrator
        vibrator.vibrate(100)

        println("[COMPLETE]")
    }

}
package club.dari.androiduitests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import club.dari.androiduitests.utils.click
import club.dari.androiduitests.utils.matchText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun when_click_button_then_text_changed_to_hello() {
        click(R.id.bButton)
        matchText(R.id.tvText, "Hello")
    }


}
package club.dari.androiduitests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import club.dari.androiduitests.utils.click
import club.dari.androiduitests.utils.inputText
import club.dari.androiduitests.utils.matchText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun when_name_edit_text_not_empty_then_text_changed_to_that_text() {
        val name = "Mock Name"

        matchText(R.id.etName, "")

        inputText(R.id.etName, name)

        matchText(R.id.etName, name)

        click(R.id.bButton)

        matchText(R.id.tvText, name)
    }

    @Test
    fun when_name_edit_text_empty_then_text_changed_to_hello() {
        click(R.id.bButton)

        matchText(R.id.tvText, "Hello")
    }

    @Test
    fun when_click_button_then_name_cleared() {
        val name = "Mock Name"

        matchText(R.id.etName, "")

        inputText(R.id.etName, name)

        matchText(R.id.etName, name)

        click(R.id.bButton)

        matchText(R.id.etName, "")
    }


}
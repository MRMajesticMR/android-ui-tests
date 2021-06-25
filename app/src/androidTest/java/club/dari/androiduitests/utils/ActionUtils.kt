package club.dari.androiduitests.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import club.dari.androiduitests.R

fun click(id: Int) {
    onView(withId(id))
        .perform(click())
}

fun matchText(id: Int, text: String) {
    onView(withId(id))
        .check(matches(withText(text)))
}

fun inputText(id: Int, text: String) {
    onView(withId(id))
        .perform(typeText(text), closeSoftKeyboard())
}
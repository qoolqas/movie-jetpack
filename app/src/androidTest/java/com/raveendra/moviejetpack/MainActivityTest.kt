package com.raveendra.moviejetpack

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.raveendra.moviejetpack.utils.DataDummy
import org.junit.Rule
import org.junit.Test

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivityTest{
    private val dummyMovie = DataDummy.generateDataMovieDummy()
    private val dummyTv = DataDummy.generateDataTvShowDummy()
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.movie_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_rv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }
    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.movie_rv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dummyMovie[0].name)))
        onView(withId(R.id.detail_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(withText(dummyMovie[0].desc)))
        onView(withId(R.id.detail_genre)).check(matches(withText(dummyMovie[0].genre)))
        pressBack()
    }
    @Test
    fun loadTv() {
        onView(withId(2131296540)).perform(ViewActions.click())
        onView(withId(R.id.tv_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }
    @Test
    fun loadDetailTv() {
        onView(withId(2131296540)).perform(ViewActions.click())
        onView(withId(R.id.tv_rv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dummyTv[0].name)))
        onView(withId(R.id.detail_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(withText(dummyTv[0].desc)))
        onView(withId(R.id.detail_genre)).check(matches(withText(dummyTv[0].genre)))
        pressBack()
    }
}
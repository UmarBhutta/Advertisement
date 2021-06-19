package com.capricorn.advertisement.ui

import android.content.Intent
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.capricorn.advertisement.POKEMON_ITEM_KEY
import com.capricorn.advertisement.R
import com.capricorn.advertisement.TestDataUtil.initData
import com.capricorn.advertisement.TestDataUtil.advertisementList
import com.capricorn.advertisement.ui.details.DetailsActivity
import com.capricorn.advertisement.utils.EspressoIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class DetailsActivityTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var mActivityTestRule = ActivityTestRule(DetailsActivity::class.java, true, false)
    private var mIdlingResource: IdlingResource? = null


    @Before
    fun setup() {
        initData()
        val intent: Intent = Intent().apply {
            putExtra(POKEMON_ITEM_KEY, advertisementList.advertisements[0])
        }
        mActivityTestRule.launchActivity(intent)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testAdvertisementDetails() {
        //Assert Title
        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName("action_bar")))).check(matches(withText(
            advertisementList.advertisements[0].name)))
        onView(withId(R.id.pokemonImage)).check(matches(isDisplayed()))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }
}
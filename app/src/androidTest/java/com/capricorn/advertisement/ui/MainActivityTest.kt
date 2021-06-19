package com.capricorn.carslist.ui


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.capricorn.advertisement.LoadStatus
import com.capricorn.advertisement.R
import com.capricorn.advertisement.TestDataUtil.dataSuccess
import com.capricorn.advertisement.ui.home.MainActivity
import com.capricorn.advertisement.utils.EspressoIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.not
import org.junit.After



@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, false, false)
    private var mIdlingResource: IdlingResource? = null



    @Before
    fun setup(){

        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun displayAdvertisementList(){
        //tell repo to load
        dataSuccess = LoadStatus.Success
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.rv_pokemon_list)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
    }



    @Test
    fun displayNoData(){
        //tell repo to fail
        dataSuccess = LoadStatus.Fail
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.rv_pokemon_list)).check(matches(not(isDisplayed())))
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tv_no_data)).check(matches(isDisplayed()))
    }


    @Test
    fun testRecyclerViewScrollAndClicking(){
        //tell repo to load
        dataSuccess = LoadStatus.Success
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.rv_pokemon_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        onView(withId(R.id.pokemonImage)).check(matches(isDisplayed()))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }

}
package com.capricorn.advertisement.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.capricorn.advertisement.util.InstantExecutorExtension
import com.capricorn.advertisement.util.MainCoroutineRule
import com.capricorn.advertisement.util.TestAdvertisementModelsGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by Muhammad Umar on 16/06/2021.
 */

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class DetailsViewModelTest {
    //subject under test
    private lateinit var detailsViewModel: DetailsViewModel

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testModelsGenerator: TestAdvertisementModelsGenerator = TestAdvertisementModelsGenerator()

    @Test
    fun `init Intent Data`(){
        //mock data
        val advertisementItem = testModelsGenerator.generateAdvertisementItemModel()
        //call
        detailsViewModel = DetailsViewModel()
        detailsViewModel.initFromIntentData(advertisementItem)

        //active observer for livedata
        detailsViewModel.advertisementData.observeForever{ }

        //verify
        val advertisementData = detailsViewModel.advertisementData.value
        assertEquals(advertisementItem,advertisementData)
    }
}
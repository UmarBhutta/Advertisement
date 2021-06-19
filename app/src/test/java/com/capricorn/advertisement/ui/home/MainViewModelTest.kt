package com.capricorn.advertisement.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.capricorn.advertisement.data.DataRepository
import com.capricorn.advertisement.data.Result
import com.capricorn.advertisement.data.dto.AdvertisementList
import com.capricorn.advertisement.data.error.NETWORK_ERROR
import com.capricorn.advertisement.util.InstantExecutorExtension
import com.capricorn.advertisement.util.MainCoroutineRule
import com.capricorn.advertisement.util.TestAdvertisementModelsGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class MainViewModelTest {

    //subject under test
    private lateinit var mainViewModel: MainViewModel

    //Using a mock repo to be injected into the viewmodel
    private val dataRepository: DataRepository = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var listTitle: String
    private val testModelsGenerator: TestAdvertisementModelsGenerator = TestAdvertisementModelsGenerator()

    @Before
    fun setup(){
        // initialise the repository with no tasks
        listTitle = testModelsGenerator.getStubTitle()
    }


    @Test
    fun `get Advertisement List`() {
        // Let's do an answer for the liveData
        val pokemonList = testModelsGenerator.generateAdvertisementList()

        //1- Mock calls
        coEvery { dataRepository.requestAdvertisementList() } returns flow {
            emit(Result.Success(pokemonList))
        }

        //2-Call
        mainViewModel = MainViewModel(dataRepository)
        mainViewModel.getAdvertisementList()
        //active observer for livedata
        mainViewModel.advertisementList.observeForever { }

        //3-verify
        val isEmptyList = mainViewModel.advertisementList.value?.data?.advertisements.isNullOrEmpty()
        assertEquals(pokemonList, mainViewModel.advertisementList.value?.data)
        assertEquals(false,isEmptyList)
    }

    @Test
    fun `get Advertisement Empty List`() {
        // Let's do an answer for the liveData
        val pokemonListModel = testModelsGenerator.generateAdvertisementListWithEmptyList()

        //1- Mock calls
        coEvery { dataRepository.requestAdvertisementList() } returns flow {
            emit(Result.Success(pokemonListModel))
        }

        //2-Call
        mainViewModel = MainViewModel(dataRepository)
        mainViewModel.getAdvertisementList()
        //active observer for livedata
        mainViewModel.advertisementList.observeForever { }

        //3-verify
        val isEmptyList = mainViewModel.advertisementList.value?.data?.advertisements.isNullOrEmpty()
        assertEquals(pokemonListModel, mainViewModel.advertisementList.value?.data)
        assertEquals(true, isEmptyList)
    }

    @Test
    fun `get Pokemon List Error`() {
        // Let's do an answer for the liveData
        val error: Result<AdvertisementList> = Result.Error(NETWORK_ERROR)

        //1- Mock calls
        coEvery { dataRepository.requestAdvertisementList() } returns flow {
            emit(error)
        }

        //2-Call
        mainViewModel = MainViewModel(dataRepository)
        mainViewModel.getAdvertisementList()
        //active observer for livedata
        mainViewModel.advertisementList.observeForever { }

        //3-verify
        assertEquals(NETWORK_ERROR, mainViewModel.advertisementList.value?.errorCode)
    }


}
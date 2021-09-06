package com.example.sunshine.api

import com.example.sunshine.utils.TestingMocks
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class WeatherServiceTest {

    @Mock
    private val mockWeatherService = mockk<WeatherService>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun getWeatherTest() {
        val fetch = TestingMocks.getMockWeatherPayload()
        coEvery { mockWeatherService.getWeather().blockingFirst() } returns fetch
    }

    @Test
    fun getWeatherFromLocationTest() {
        val fetch = TestingMocks.getMockWeatherPayload()
        coEvery { mockWeatherService.getWeatherFromLocation(35.0, 139.0).blockingFirst() } returns fetch
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}
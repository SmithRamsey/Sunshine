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
class WeatherRepositoryTest {

    @Mock
    private val mockWeatherRepository = mockk<WeatherRepository>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
    }

    @Test
    fun getWeatherPayloadTest() {
        val fetch = TestingMocks.getMockWeatherPayload()
        coEvery { mockWeatherRepository.getWeatherPayload(null, null).blockingFirst() } returns fetch
    }

    @Test
    fun getWeatherPayloadWithLatLongTest() {
        val fetch = TestingMocks.getMockWeatherPayload()
        coEvery { mockWeatherRepository.getWeatherPayload(1.0, 1.0).blockingFirst() } returns fetch
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}
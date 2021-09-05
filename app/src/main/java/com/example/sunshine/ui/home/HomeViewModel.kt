package com.example.sunshine.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunshine.api.WeatherRepository
import com.example.sunshine.model.WeatherPayload
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {


    private lateinit var weatherRepository: WeatherRepository

    suspend fun getWeather() {
        weatherRepository.getWeatherPayload().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : Observer<WeatherPayload> {
                override fun onSubscribe(d: Disposable?) {
                    TODO("Not yet implemented")
                }

                override fun onNext(value: WeatherPayload?) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable?) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }
            })
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}
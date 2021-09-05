package com.example.sunshine.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunshine.api.WeatherRepository
import com.example.sunshine.model.WeatherPayload
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {

    private val weatherRepository by lazy { WeatherRepository.getInstance() }
    private val homeViewModelJob = Job()
    private val homViewModelCoroutine = CoroutineScope(Dispatchers.Default + homeViewModelJob)
    private val _weatherPayload = MutableLiveData<WeatherPayload>()
    val weatherPayload: LiveData<WeatherPayload> = _weatherPayload

    fun getWeather() {
        homViewModelCoroutine.launch {
            weatherRepository.getWeatherPayload().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : Observer<WeatherPayload> {
                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onNext(value: WeatherPayload?) {
                        value?.let {
                            _weatherPayload.postValue(it)
                        }
                        Log.d("testing", "onNext: $value")
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("testing", "onError: $e")
                    }

                    override fun onComplete() {

                    }
                })
        }
    }

    fun onDestroy() {
        homeViewModelJob.cancel()
    }
}
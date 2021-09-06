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
    private val _isLoading = MutableLiveData<Boolean>()
    private val _weatherPayload = MutableLiveData<WeatherPayload>()
    val weatherPayload: LiveData<WeatherPayload> = _weatherPayload
    val isLoading: LiveData<Boolean> = _isLoading

    fun getWeather() {
        homViewModelCoroutine.launch {
            weatherRepository.getWeatherPayload().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : Observer<WeatherPayload> {
                    override fun onSubscribe(d: Disposable?) {
                        _isLoading.postValue(true)
                    }

                    override fun onNext(value: WeatherPayload?) {
                        value?.let {
                            _weatherPayload.postValue(it)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        _isLoading.postValue(false)
                    }

                    override fun onComplete() {
                        _isLoading.postValue(false)
                    }
                })
        }
    }

    fun onDestroy() {
        homeViewModelJob.cancel()
    }
}
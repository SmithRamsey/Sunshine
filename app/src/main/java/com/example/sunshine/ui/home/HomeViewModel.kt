package com.example.sunshine.ui.home

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
    private val _coordinates = MutableLiveData<Pair<Double?, Double?>>()
    val weatherPayload: LiveData<WeatherPayload> = _weatherPayload
    val isLoading: LiveData<Boolean> = _isLoading
    val coordinates: LiveData<Pair<Double?, Double?>> = _coordinates

    fun getWeather(lat: Double? = null, long: Double? = null) {
        homViewModelCoroutine.launch {
            weatherRepository.getWeatherPayload(lat, long).subscribeOn(Schedulers.io())
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

    fun setCoordinates(lat: Double?, long: Double?) {
        _coordinates.postValue(Pair(lat, long))
    }
}
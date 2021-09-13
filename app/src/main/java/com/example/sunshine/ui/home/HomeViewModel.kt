package com.example.sunshine.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunshine.api.WeatherRepository
import com.example.sunshine.model.WeatherPayload
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {

    private val weatherRepository by lazy { WeatherRepository.instance }
    private val _isLoading = MutableLiveData<Boolean>()
    private val _weatherPayload = MutableLiveData<WeatherPayload>()
    val weatherPayload: LiveData<WeatherPayload> = _weatherPayload
    val isLoading: LiveData<Boolean> = _isLoading
    // State variables
    private var lat: Double? = null
    private var long: Double? = null

    fun getWeather() {
        viewModelScope.launch {
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
        this.lat = lat
        this.long = long
        getWeather()
    }
}
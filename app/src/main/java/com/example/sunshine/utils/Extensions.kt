package com.example.sunshine.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.sunshine.R
import com.example.sunshine.model.ListItem
import com.example.sunshine.utils.Constants.HALF
import com.example.sunshine.utils.Constants.ONE_THOUSAND
import com.example.sunshine.utils.Constants.THREE_HUNDRED_SIXTY
import com.example.sunshine.utils.Constants.TWENTY_TWO_AND_HALF
import java.text.SimpleDateFormat
import java.util.*

fun <T : ViewDataBinding> ViewGroup.bind(layoutId: Int, attachToParent: Boolean = false): T =
    DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, attachToParent)

fun View.setAuxiliaryViewVisibility() {
    this.visibility = if (this.visibility == View.VISIBLE) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("loadIcon")
fun loadIcon(view: View, id: String?) {
    view.background = AppCompatResources.getDrawable(
        view.context,
        when (id) {
            "11d" -> R.drawable.weather_stormy
            "09d" -> R.drawable.weather_rainy
            "10d", "13d" -> R.drawable.weather_rainy_2
            "50d", "01d" -> R.drawable.weather_sunny
            "01n" -> R.drawable.weather_moon
            "02d", "03d", "04d" -> R.drawable.weather_cloudy
            "02n", "03n", "04n" -> R.drawable.weather_cloudy_night
            else -> R.drawable.weather_sunny
        }
    )
}

@BindingAdapter("roundTemp")
fun roundTemp(view: TextView, temp: Double) {
    val result = "${temp.toInt()}°"
    view.text = result
}

@BindingAdapter("feelsLikeRoundTemp")
fun feelsLikeRoundTemp(view: TextView, temp: Double) {
    val feelsLike = view.context.getString(R.string.feels_like)
    val result = "$feelsLike ${temp.toInt()}°"
    view.text = result
}

@BindingAdapter("buildWindString")
fun buildWindString(view: TextView, listItem: ListItem) {
    val sector  = ((listItem.deg?.toDouble() ?: THREE_HUNDRED_SIXTY / TWENTY_TWO_AND_HALF) + HALF).toInt()
    val arr = listOf("N","NNE","NE","ENE","E","ESE", "SE", "SSE","S","SSW","SW","WSW","W","WNW","NW","NNW")
    val direction =  arr[(sector % 16)]
    val wind = view.context.getString(R.string.wind)
    val result ="$wind: ${listItem.speed} km/h $direction"
    view.text = result
}

@BindingAdapter("formatDate")
fun formatDate(view: TextView, dt: Int) {
    val sdf = SimpleDateFormat("EEEE", Locale.US)
    val date = Date(dt.toLong() * ONE_THOUSAND)
    val result = sdf.format(date)
    view.text = result
}
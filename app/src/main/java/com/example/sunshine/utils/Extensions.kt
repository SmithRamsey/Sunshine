package com.example.sunshine.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> ViewGroup.bind(layoutId: Int, attachToParent: Boolean = false) : T =
    DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, attachToParent)

fun View.setAuxiliaryTextVisibility() {
    this.visibility = if (this.visibility == View.VISIBLE) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
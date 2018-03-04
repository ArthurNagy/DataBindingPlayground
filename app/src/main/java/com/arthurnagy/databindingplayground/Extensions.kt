/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground

import android.databinding.Observable
import android.databinding.ObservableField

inline fun <T> ObservableField<T>.observe(crossinline observer: (T?) -> Unit) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            observer(this@observe.get())
        }
    })
}
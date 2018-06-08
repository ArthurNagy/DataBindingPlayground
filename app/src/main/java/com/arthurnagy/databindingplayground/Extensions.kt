/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <T> ObservableField<T>.observe(crossinline observer: (T?) -> Unit): Observable.OnPropertyChangedCallback {
    val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            observer(this@observe.get())
        }
    }
    this.addOnPropertyChangedCallback(callback)
    return callback
}

inline fun <D1, D2, T> ObservableField<T>.dependsOn(
    dependencyOne: ObservableField<D1>,
    dependencyTwo: ObservableField<D2>,
    crossinline mapper: (D1?, D2?) -> T
): ObservableField<T> = this.also {
    val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            set(mapper(dependencyOne.get(), dependencyTwo.get()))
        }
    }
    dependencyOne.addOnPropertyChangedCallback(callback)
    dependencyTwo.addOnPropertyChangedCallback(callback)
}

inline fun <reified VM : ViewModel> FragmentActivity.provideViewModel(): VM =
    ViewModelProviders.of(this).get(VM::class.java)
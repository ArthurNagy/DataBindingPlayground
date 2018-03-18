/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.databinding.ObservableField
import android.support.v4.app.FragmentActivity

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
            mapper(dependencyOne.get(), dependencyTwo.get())
        }
    }
    dependencyOne.addOnPropertyChangedCallback(callback)
    dependencyTwo.addOnPropertyChangedCallback(callback)
}

inline fun <reified VM : ViewModel> FragmentActivity.provideViewModel(): VM =
    ViewModelProviders.of(this).get(VM::class.java)
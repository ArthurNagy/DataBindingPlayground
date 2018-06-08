/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

inline fun <T> ObservableField<T>.observe(crossinline observer: (T?) -> Unit): Observable.OnPropertyChangedCallback {
    val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            observer(this@observe.get())
        }
    }
    this.addOnPropertyChangedCallback(callback)
    return callback
}

inline fun <T, D1, D2> ObservableField<T>.dependsOn(
    dependencyOne: ObservableField<D1>,
    dependencyTwo: ObservableField<D2>,
    crossinline mapper: (D1?, D2?) -> T
) = this.also {
    val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            set(mapper(dependencyOne.get(), dependencyTwo.get()))
        }
    }
    dependencyOne.addOnPropertyChangedCallback(callback)
    dependencyTwo.addOnPropertyChangedCallback(callback)
}


inline fun <T, D1, D2> MediatorLiveData<T>.dependsOn(
    dependencyOne: LiveData<D1>,
    dependencyTwo: LiveData<D2>,
    crossinline mapper: (D1?, D2?) -> T
) = this.also {
    addSource(dependencyOne) { value = mapper(it, dependencyTwo.value) }
    addSource(dependencyTwo) { value = mapper(dependencyOne.value, it) }
}

inline fun <reified VM : ViewModel> FragmentActivity.provideViewModel(crossinline factory: () -> VM): VM =
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = factory() as T
    }).get(VM::class.java)
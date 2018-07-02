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


inline fun <T> dependantObservableField(vararg dependencies: Observable, crossinline mapper: () -> T?): ObservableField<T> {
    return object : ObservableField<T>(*dependencies) {
        override fun get(): T? {
            return mapper()
        }
    }
}

inline fun <T> dependantLiveData(vararg dependencies: LiveData<*>, crossinline mapper: () -> T?): MediatorLiveData<T> {
    return MediatorLiveData<T>().also { mediatorLiveData ->
        val observer = Observer<Any> { mediatorLiveData.value = mapper() }
        dependencies.forEach { dependencyLiveData ->
            mediatorLiveData.addSource(dependencyLiveData, observer)
        }
    }
}

inline fun <reified T : Observable> T.observe(crossinline observer: (T) -> Unit): Observable.OnPropertyChangedCallback =
    object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            observer(sender as T)
        }
    }.also { addOnPropertyChangedCallback(it) }

inline fun <reified VM : ViewModel> FragmentActivity.provideViewModel(crossinline factory: () -> VM): VM =
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = factory() as T
    }).get(VM::class.java)
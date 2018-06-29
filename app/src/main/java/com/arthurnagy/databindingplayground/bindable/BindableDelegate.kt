package com.arthurnagy.databindingplayground.bindable

import androidx.databinding.BaseObservable
import kotlin.reflect.KProperty

fun <R : BaseObservable, T : Any> bindable(value: T, bindingId: Int): BindableDelegate<R, T> =
    BindableDelegate(value, bindingId)

class BindableDelegate<in R : BaseObservable, T : Any>(
    private var value: T,
    private val bindingId: Int
) {
    operator fun getValue(thisRef: R, property: KProperty<*>): T = value

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        this.value = value
        thisRef.notifyPropertyChanged(bindingId)
    }
}
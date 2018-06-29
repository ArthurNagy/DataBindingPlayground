package com.arthurnagy.databindingplayground.observablefield

import androidx.databinding.ObservableField


class DependentObservableField<T : Any>(
    private vararg val dependencies: ObservableField<T>,
    private val mapper: (List<T?>) -> T?
) : ObservableField<T>(*dependencies) {

    override fun get(): T? = mapper(dependencies.map { it.get() })

}
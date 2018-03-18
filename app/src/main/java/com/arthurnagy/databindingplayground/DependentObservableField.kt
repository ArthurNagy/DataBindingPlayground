/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground

import android.databinding.ObservableField


class DependentObservableField<T, R>(
    private val dependencies: Array<ObservableField<R>>,
    private val mapper: (Array<ObservableField<R>>) -> T?
) : ObservableField<T>(*dependencies) {

    override fun get(): T? = mapper(dependencies)

}
/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.Observable
import android.databinding.ObservableField

class UserViewModel(
    private val stringProvider: StringProvider
) : ViewModel() {

    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val userName = ObservableField<String>(firstName, lastName)


    init {
        firstName.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

            }
        })
    }

    class MainViewModelFactory(private val stringProvider: StringProvider) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T = UserViewModel(stringProvider) as T

    }

}
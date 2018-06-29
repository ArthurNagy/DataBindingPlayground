/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground.bindable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.arthurnagy.databindingplayground.BR
import com.arthurnagy.databindingplayground.R
import com.arthurnagy.databindingplayground.ResourceProvider

class UserViewModel(private val resourceProvider: ResourceProvider) : BaseObservable() {

//    @get:Bindable
//    var firstName: String = ""
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.firstName)
//        }
//
//    @get:Bindable
//    var lastName: String = ""
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.lastName)
//        }

    @get:Bindable
    var firstName: String by bindable("", BR.firstName)

    @get:Bindable
    var lastName: String by bindable("", BR.lastName)

    val displayName: String
        @Bindable(value = ["firstName", "lastName"])
        get() = resourceProvider.getString(R.string.display_name, firstName, lastName)

}
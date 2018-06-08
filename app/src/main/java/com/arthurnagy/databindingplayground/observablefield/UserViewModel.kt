/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground.observablefield

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.arthurnagy.databindingplayground.R
import com.arthurnagy.databindingplayground.ResourceProvider
import com.arthurnagy.databindingplayground.dependsOn

class UserViewModel(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()

    val displayName = ObservableField<String>().dependsOn(firstName, lastName) { firstName, lastName ->
        resourceProvider.getString(R.string.display_name, firstName.orEmpty(), lastName.orEmpty())
    }

}
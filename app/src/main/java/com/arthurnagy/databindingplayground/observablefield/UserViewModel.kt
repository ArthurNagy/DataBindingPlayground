package com.arthurnagy.databindingplayground.observablefield

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.arthurnagy.databindingplayground.R
import com.arthurnagy.databindingplayground.ResourceProvider
import com.arthurnagy.databindingplayground.dependantObservableField

class UserViewModel(private val resourceProvider: ResourceProvider) : ViewModel() {

    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()

//    val displayName: ObservableField<String> = object : ObservableField<String>(firstName, lastName) {
//        override fun get() =
//            resourceProvider.getString(R.string.display_name, firstName.get().orEmpty(), lastName.get().orEmpty())
//    }

    val displayName = dependantObservableField(firstName, lastName) {
        resourceProvider.getString(R.string.display_name, firstName.get().orEmpty(), lastName.get().orEmpty())
    }

}
package com.arthurnagy.databindingplayground.livedata

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arthurnagy.databindingplayground.R
import com.arthurnagy.databindingplayground.ResourceProvider
import com.arthurnagy.databindingplayground.dependsOn

class UserViewModel(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()

    val displayName = MediatorLiveData<String>().dependsOn(firstName, lastName) { firstName, lastName ->
        resourceProvider.getString(R.string.display_name, firstName.orEmpty(), lastName.orEmpty())
    }

}
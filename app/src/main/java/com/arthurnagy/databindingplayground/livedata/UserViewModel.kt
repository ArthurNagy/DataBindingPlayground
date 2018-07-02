package com.arthurnagy.databindingplayground.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arthurnagy.databindingplayground.R
import com.arthurnagy.databindingplayground.ResourceProvider
import com.arthurnagy.databindingplayground.dependantLiveData

class UserViewModel(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()

    val displayName = dependantLiveData(firstName, lastName) {
        resourceProvider.getString(R.string.display_name, firstName.value.orEmpty(), lastName.value.orEmpty())
    }

}
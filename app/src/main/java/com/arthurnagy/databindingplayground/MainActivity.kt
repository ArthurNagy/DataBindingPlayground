package com.arthurnagy.databindingplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.arthurnagy.databindingplayground.livedata.UserViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: MainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.setLifecycleOwner(this)
        binding.viewModel = provideViewModel { UserViewModel(ResourceProvider(applicationContext)) }
    }
}

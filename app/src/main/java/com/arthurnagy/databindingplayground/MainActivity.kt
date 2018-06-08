/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<MainBinding>(this, R.layout.activity_main)
        binding.viewModel = provideViewModel()
        binding.viewModel?.resourceProvider = ResourceProvider(applicationContext)
    }
}

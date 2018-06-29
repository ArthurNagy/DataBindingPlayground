package com.arthurnagy.databindingplayground

import android.content.Context
import androidx.annotation.StringRes


class ResourceProvider(private val context: Context) {

    fun getString(@StringRes stringId: Int, vararg formatArgs: Any): String =
        context.getString(stringId, *formatArgs)

}
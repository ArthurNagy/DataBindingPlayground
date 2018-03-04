/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground

import android.content.Context
import android.support.annotation.StringRes


class StringProvider(private val context: Context) {

    fun getString(@StringRes stringId: Int, vararg formatArgs: Any): String =
        context.getString(stringId, *formatArgs)

}
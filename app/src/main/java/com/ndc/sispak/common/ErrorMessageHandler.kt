package com.ndc.sispak.common

import android.content.Context
import com.ndc.sispak.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMessageHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun fromCode(code: Int): String = when (code) {
        401 -> context.getString(R.string.error_authorization)
        500 -> context.getString(R.string.error_internal_server)
        -1 -> context.getString(R.string.error_connection_timeout)
        -2 -> context.getString(R.string.error_no_connection)
        else -> context.getString(R.string.error_unknown)
    }

    fun fromRes(reId: Int) = context.getString(R.string.dob)
}

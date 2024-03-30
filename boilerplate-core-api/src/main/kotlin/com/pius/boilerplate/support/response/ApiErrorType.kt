package com.pius.boilerplate.support.response

import com.pius.boilerplate.support.error.ErrorCode

data class ApiErrorType(
    val code: ErrorCode,
    val message: String
)

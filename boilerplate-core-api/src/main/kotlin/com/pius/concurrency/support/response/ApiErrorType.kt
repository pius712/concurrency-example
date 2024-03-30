package com.pius.concurrency.support.response

import com.pius.concurrency.support.error.ErrorCode

data class ApiErrorType(
    val code: ErrorCode,
    val message: String
)

package com.pius.boilerplate.support.error

open class BaseException(
    val exceptionType: ErrorType,
    val data: Any?
) : RuntimeException() {
}
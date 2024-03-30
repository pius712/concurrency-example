package com.pius.concurrency.support.error

open class BaseException(
    val exceptionType: ErrorType,
    val data: Any?
) : RuntimeException() {
}
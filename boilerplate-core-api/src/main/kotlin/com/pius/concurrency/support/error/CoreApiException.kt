package com.pius.concurrency.support.error

class CoreApiException(
    exceptionType: ErrorType, data: Any?
) : BaseException(exceptionType, data)
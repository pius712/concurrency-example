package com.pius.boilerplate.support.error

class CoreApiException(
    exceptionType: ErrorType, data: Any?
) : BaseException(exceptionType, data)
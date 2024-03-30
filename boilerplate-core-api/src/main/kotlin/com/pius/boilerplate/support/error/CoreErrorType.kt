package com.pius.boilerplate.support.error

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class CoreErrorType(
    override val status: HttpStatus,
    override val errorCode: ErrorCode,
    override val message: String,
    override val logLevel: LogLevel,
) : ErrorType {

    NOT_FOUND(HttpStatus.NOT_FOUND, CoreErrorCode.C000, "", LogLevel.INFO)
}
package com.pius.boilerplate.support.error

import com.pius.boilerplate.support.error.ErrorCode
import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

interface ErrorType {
    val status: HttpStatus
    val errorCode: ErrorCode
    val message: String
    val logLevel: LogLevel
}

package com.pius.boilerplate.controller.advice

import com.pius.boilerplate.support.error.BaseException
import com.pius.boilerplate.support.response.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BaseExceptionAdvice {

    val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(BaseException::class)
    fun baseException(exception: BaseException): ResponseEntity<Any> {
        when (exception.exceptionType.logLevel) {
            LogLevel.ERROR -> log.error(exception.exceptionType.message, exception)
            LogLevel.INFO -> log.info(exception.exceptionType.message, exception)
            LogLevel.WARN -> log.warn(exception.exceptionType.message, exception)
            else -> log.debug(exception.exceptionType.message, exception)
        }

        return ResponseEntity(
            ApiResponse.error(exception.exceptionType, exception.data),
            exception.exceptionType.status
        )
    }
}
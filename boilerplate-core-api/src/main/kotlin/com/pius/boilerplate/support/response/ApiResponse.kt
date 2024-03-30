package com.pius.boilerplate.support.response

import com.pius.boilerplate.support.error.ErrorType

class ApiResponse<T>(
    val result: ApiResult,
    val data: T? = null,
    val error: ApiErrorType? = null
) {

    companion object {
        fun ok(): ApiResponse<Unit> {
            return ApiResponse(ApiResult.SUCCESS)
        }

        fun <T> ok(data: T): ApiResponse<T> {
            return ApiResponse(ApiResult.SUCCESS, data)
        }

        fun <T> error(exception: ErrorType, data: T? = null): ApiResponse<T> {
            return ApiResponse(ApiResult.ERROR, data, ApiErrorType(exception.errorCode, exception.message));
        }
    }
}
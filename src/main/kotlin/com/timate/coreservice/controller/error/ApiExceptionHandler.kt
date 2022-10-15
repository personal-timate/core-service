package com.timate.coreservice.controller.error

import com.timate.coreservice.errors.CacheClearingFailed
import com.timate.coreservice.errors.PermissionDenied
import com.timate.coreservice.model.error.ApiErrorResponse
import com.timate.coreservice.model.error.ApiErrorResponse.Companion.toApiErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.net.ConnectException
import java.util.*

/**
 * @author Nicholas Dietz
 **/
@ControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(
        ResponseStatusException::class
    )
    protected fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<ApiErrorResponse> {
        return ResponseEntity.status(ex.status).body(
            ApiErrorResponse(
                code = ex.status.value(), message = ex.localizedMessage, cause = ex.cause?.message
            )
        )
    }

    @ExceptionHandler(
        PermissionDenied::class
    )
    protected fun handlePermissionDeniedErrors(ex: Exception): ResponseEntity<ApiErrorResponse> {
        return ex.toApiErrorResponse(HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(
        Exception::class
    )
    protected fun handleUnknownErrors(ex: Exception): ResponseEntity<ApiErrorResponse> {
        val uuid = UUID.randomUUID().toString()
        logger.error("Unknown error occurred. stackTraceId: $uuid", ex)
        return ex.toApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, uuid)
    }

    @ExceptionHandler(
        CacheClearingFailed::class
    )
    protected fun handleClearingCacheExceptions(ex: Exception): ResponseEntity<ApiErrorResponse> {
        val uuid = UUID.randomUUID().toString()
        logger.error("Failed to clear cache(s). StackTraceId: $uuid", ex)
        return ex.toApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, uuid)
    }

    @ExceptionHandler(
        ConnectException::class
    )
    protected fun handleConnectExceptions(ex: Exception): ResponseEntity<ApiErrorResponse> {
        val uuid = UUID.randomUUID().toString()
        logger.error("Failed to connect to certain service. stackTraceId: $uuid", ex)
        return ex.toApiErrorResponse(HttpStatus.FAILED_DEPENDENCY, uuid, "Failed to connect to another service.")
    }
}

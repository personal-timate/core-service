package com.timate.coreservice.model.error

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiErrorResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    val timestamp: Date = Date(),

    val code: Int,
    val message: String,
    val cause: String? = null,
    val stackTraceId: String? = null
) {

    companion object {
        fun Exception.toApiErrorResponse(status: HttpStatus, uuid: String, overrideMessage: String): ResponseEntity<ApiErrorResponse> {
            return ResponseEntity.status(status).body(
                ApiErrorResponse(
                    code = status.value(),
                    message = overrideMessage,
                    cause = this.cause?.message,
                    stackTraceId = uuid
                )
            )
        }

        fun Exception.toApiErrorResponse(status: HttpStatus, uuid: String): ResponseEntity<ApiErrorResponse> {
            return ResponseEntity.status(status).body(
                ApiErrorResponse(
                    code = status.value(),
                    message = this.localizedMessage,
                    cause = this.cause?.message,
                    stackTraceId = uuid
                )
            )
        }

        fun Exception.toApiErrorResponse(status: HttpStatus): ResponseEntity<ApiErrorResponse> {
            return ResponseEntity.status(status).body(
                ApiErrorResponse(
                    code = status.value(),
                    message = this.localizedMessage,
                    cause = this.cause?.message
                )
            )
        }
    }
}

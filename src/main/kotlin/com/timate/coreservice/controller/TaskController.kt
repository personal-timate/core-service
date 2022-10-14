package com.timate.coreservice.controller

import com.timate.coreservice.constants.ApiVersion
import com.timate.coreservice.model.dto.*
import com.timate.coreservice.services.base.task.TaskBaseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Task Controller")
@RequestMapping("api/${ApiVersion.VERSION_1}/tasks")
@RestController
class TaskController(
    val taskBaseService: TaskBaseService
) {

    @Operation(summary = "Get all task urgencies.")
    @GetMapping("/urgencies", produces = ["application/hal+json"])
    fun getAllTaskUrgencies(): ResponseEntity<ListResponse<TaskUrgencyDto>> {
        return taskBaseService.getAllTaskUrgencies().buildResponse()
    }

    @Operation(summary = "Get all task impacts.")
    @GetMapping("/impacts", produces = ["application/hal+json"])
    fun getAllTaskImpacts(): ResponseEntity<ListResponse<TaskImpactDto>> {
        return taskBaseService.getAllTaskImpacts().buildResponse()
    }

    @Operation(summary = "Get all task types.")
    @GetMapping("/types", produces = ["application/hal+json"])
    fun getAllTaskTypes(): ResponseEntity<ListResponse<TaskTypeDto>> {
        return taskBaseService.getAllTaskTypes().buildResponse()
    }

}
package com.timate.coreservice.services.base.task

import com.timate.coreservice.model.dto.TaskDto
import com.timate.coreservice.model.dto.TaskImpactDto
import com.timate.coreservice.model.dto.TaskTypeDto
import com.timate.coreservice.model.dto.TaskUrgencyDto

interface TaskBaseService {
    fun getAllTaskUrgencies(): List<TaskUrgencyDto>
    fun getAllTaskImpacts(): List<TaskImpactDto>
    fun getAllTaskTypes(): List<TaskTypeDto>
    fun getAlltasks(userId: String): List<TaskDto>
}
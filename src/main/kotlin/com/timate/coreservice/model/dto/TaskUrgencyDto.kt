package com.timate.coreservice.model.dto

import com.timate.coreservice.model.db.entities.task.TaskUrgencyEntity

data class TaskUrgencyDto(
    val id: Long,
    val urgency: String,
    val description: String? = null
) {

    companion object {
        fun fromEntity(entity: TaskUrgencyEntity): TaskUrgencyDto {
            return TaskUrgencyDto(
                id = entity.id,
                urgency = entity.urgency,
                description = entity.description.orEmpty()
            )
        }
    }

}

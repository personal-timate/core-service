package com.timate.coreservice.model.dto

import com.timate.coreservice.model.db.entities.task.TaskTypeEntity

data class TaskTypeDto(
    val id: Long,
    val type: String,
    val description: String? = null
) {

    companion object {
        fun fromEntity(entity: TaskTypeEntity): TaskTypeDto {
            return TaskTypeDto(
                id = entity.id!!,
                type = entity.type
            )
        }
    }

}

package com.timate.coreservice.model.dto

import com.timate.coreservice.model.db.entities.task.TaskEisenhauerTypeEntity
import com.timate.coreservice.model.general.TaskEisenhauerType

data class TaskTypeDto(
    val id: Long,
    val type: TaskEisenhauerType,
    val description: String? = null
) {

    companion object {
        fun fromEntity(entity: TaskEisenhauerTypeEntity): TaskTypeDto {
            return TaskTypeDto(
                id = entity.id,
                type = entity.type
            )
        }
    }

}

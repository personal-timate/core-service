package com.timate.coreservice.model.dto

import com.timate.coreservice.model.db.entities.task.TaskEntity

data class TaskDto(
    val id: Long,
    val name: String,
    val description: String? = null,
    val typeId: Long,
    val urgencyId: Long,
    val impactId: Long,
    val subtaskIds: List<Long> = emptyList()
) {

    companion object {
        fun fromEntity(entity: TaskEntity): TaskDto {
            return TaskDto(
                id = entity.id!!,
                name = entity.name,
                impactId = entity.impact.id!!,
                urgencyId = entity.urgency.id!!,
                typeId = entity.type.id!!
            )
        }
    }

}

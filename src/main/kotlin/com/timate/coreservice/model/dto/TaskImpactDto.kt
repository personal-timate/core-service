package com.timate.coreservice.model.dto

import com.timate.coreservice.model.db.entities.task.TaskImpactEntity

data class TaskImpactDto(
    val id: Long,
    val impact: String,
    val description: String? = null
) {

    companion object {
        fun fromEntity(entity: TaskImpactEntity): TaskImpactDto {
            return TaskImpactDto(
                id = entity.id!!,
                impact = entity.impact,
                description = entity.description.orEmpty()
            )
        }
    }

}

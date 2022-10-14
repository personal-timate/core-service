package com.timate.coreservice.model.dto

import com.timate.coreservice.model.general.TaskEisenhauerType

data class TaskDto(
    val id: Long,
    val name: String,
    val description: String? = null,
    val typeId: Long,
    val urgencyId: Long,
    val impactId: Long,
    val subtaskIds: List<Long> = emptyList()
)

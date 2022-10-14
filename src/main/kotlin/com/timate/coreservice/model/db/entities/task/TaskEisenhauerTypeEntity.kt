package com.timate.coreservice.model.db.entities.task

import com.timate.coreservice.model.general.TaskEisenhauerType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


/**
 * @author Nicholas Dietz @ USU GmbH
 **/
@Table("TASK_EISENHAUER_TYPE")
data class TaskEisenhauerTypeEntity(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,
    val type: TaskEisenhauerType
)
package com.timate.coreservice.model.db.entities.task

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


/**
 * @author Nicholas Dietz @ Timate
 **/
@Table("TASK_URGENCY")
data class TaskUrgencyEntity(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    val urgency: String,

    val description: String?
)
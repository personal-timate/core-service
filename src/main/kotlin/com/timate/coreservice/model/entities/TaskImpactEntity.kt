package com.timate.coreservice.model.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType


/**
 * @author Nicholas Dietz @ USU GmbH
 **/
@Table("TASK_IMPACT")
data class TaskImpactEntity(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    val impact: String,

    val description: String?
)
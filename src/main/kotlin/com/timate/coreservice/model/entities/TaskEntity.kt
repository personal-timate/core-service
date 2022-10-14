package com.timate.coreservice.model.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.CascadeType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.ManyToOne


/**
 * @author Nicholas Dietz @ USU GmbH
 **/
@Table("TASKS")
data class TaskEntity(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    val name: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    val type: TaskEisenhauerTypeEntity,
)
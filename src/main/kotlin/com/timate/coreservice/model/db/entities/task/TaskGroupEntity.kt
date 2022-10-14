package com.timate.coreservice.model.db.entities.task

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.CascadeType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.ManyToOne
import javax.persistence.OneToMany


/**
 * @author Nicholas Dietz @ USU GmbH
 **/
@Table("TASK_GROUP")
data class TaskGroupEntity(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    @OneToMany(cascade = [CascadeType.ALL])
    val main: TaskEntity,

    @ManyToOne(cascade = [CascadeType.ALL])
    val subtask: TaskEntity
)
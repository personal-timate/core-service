package com.timate.coreservice.model.db.entities.task

import javax.persistence.*


/**
 * @author Nicholas Dietz @ Timate
 **/
@Entity(name = "task_type")
@Table(name = "task_type")
class TaskTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "type", nullable = false)
    val type: String
)
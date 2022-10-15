package com.timate.coreservice.model.db.entities.task

import javax.persistence.*


/**
 * @author Nicholas Dietz @ Timate
 **/
@Entity(name = "task_urgency")
@Table(name = "task_urgency")
class TaskUrgencyEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "urgency", nullable = false)
    val urgency: String,

    @Column(name = "description", nullable = true)
    val description: String? = null

)
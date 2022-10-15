package com.timate.coreservice.model.db.entities.task

import javax.persistence.*


/**
 * @author Nicholas Dietz @ Timate
 **/
@Entity(name = "task_impact")
@Table(name = "task_impact")
class TaskImpactEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "impact", nullable = false)
    val impact: String,

    @Column(name = "description", nullable = true)
    val description: String? = null
)
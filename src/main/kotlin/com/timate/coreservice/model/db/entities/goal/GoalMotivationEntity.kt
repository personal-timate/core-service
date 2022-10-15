package com.timate.coreservice.model.db.entities.goal

import javax.persistence.*


/**
 * @author Nicholas Dietz @ Timate
 **/
@Entity(name = "goal_motivation")
@Table(name = "goal_motivation")
class GoalMotivationEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "description", nullable = true)
    val description: String? = null,

)
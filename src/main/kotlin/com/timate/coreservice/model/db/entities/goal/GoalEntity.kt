package com.timate.coreservice.model.db.entities.goal

import javax.persistence.*


/**
 * @author Nicholas Dietz @ Timate
 **/
@Entity(name = "goals")
@Table(name = "goals")
class GoalEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "description", nullable = true)
    val description: String? = null,

    @OneToOne(cascade = [CascadeType.MERGE, CascadeType.REMOVE], fetch = FetchType.LAZY, optional = true)
    val motivation: GoalMotivationEntity? = null,

)
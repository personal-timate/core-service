package com.timate.coreservice.model.db.entities.project

import com.timate.coreservice.model.db.entities.goal.GoalEntity
import com.timate.coreservice.model.db.entities.task.TaskEntity
import com.timate.coreservice.model.db.entities.user.UserEntity
import javax.persistence.*


/**
 * @author Nicholas Dietz @ Timate
 **/
@Entity(name = "projects")
@Table(name = "projects")
class ProjectEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "description", nullable = true)
    val description: String? = null,

    @ManyToOne(cascade = [CascadeType.REFRESH], fetch = FetchType.LAZY)
    val user: UserEntity,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val goals: List<GoalEntity> = emptyList(),

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val tasks: List<TaskEntity> = emptyList(),

)
package com.timate.coreservice.model.db.entities.task

import com.timate.coreservice.model.db.entities.goal.GoalEntity
import com.timate.coreservice.model.db.entities.project.ProjectEntity
import javax.persistence.*


/**
 * @author Nicholas Dietz @ Timate
 **/
@Entity(name = "tasks")
@Table(name = "tasks")
class TaskEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "major", nullable = false)
    val major: Boolean = false,

    @OneToMany(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    var subtasks: List<TaskEntity> = emptyList(),

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    val type: TaskTypeEntity,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    val urgency: TaskUrgencyEntity,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    val impact: TaskImpactEntity,

    @ManyToOne(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    val project: ProjectEntity,

    @ManyToMany(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    val linkedGoals: List<GoalEntity> = emptyList()

)
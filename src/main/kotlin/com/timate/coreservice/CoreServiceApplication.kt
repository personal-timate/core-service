package com.timate.coreservice

import com.timate.coreservice.config.log.StartupLogger
import com.timate.coreservice.model.db.entities.goal.GoalEntity
import com.timate.coreservice.model.db.entities.project.ProjectEntity
import com.timate.coreservice.model.db.entities.task.TaskEntity
import com.timate.coreservice.model.db.entities.task.TaskImpactEntity
import com.timate.coreservice.model.db.entities.task.TaskTypeEntity
import com.timate.coreservice.model.db.entities.task.TaskUrgencyEntity
import com.timate.coreservice.model.db.entities.user.UserEntity
import com.timate.coreservice.model.db.repository.goal.GoalMotivationRepository
import com.timate.coreservice.model.db.repository.goal.GoalRepository
import com.timate.coreservice.model.db.repository.project.ProjectRepository
import com.timate.coreservice.model.db.repository.task.TaskImpactRepository
import com.timate.coreservice.model.db.repository.task.TaskRepository
import com.timate.coreservice.model.db.repository.task.TaskTypeRepository
import com.timate.coreservice.model.db.repository.task.TaskUrgencyRepository
import com.timate.coreservice.model.db.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import java.util.*

@EnableCaching
@ConfigurationPropertiesScan
@SpringBootApplication(exclude = [RedisAutoConfiguration::class])
class CoreServiceApplication: CommandLineRunner {

	@Autowired
	private lateinit var taskRepository: TaskRepository

	@Autowired
	private lateinit var taskUrgencyRepository: TaskUrgencyRepository

	@Autowired
	private lateinit var taskTypeRepository: TaskTypeRepository

	@Autowired
	private lateinit var taskImpactRepository: TaskImpactRepository

	@Autowired
	private lateinit var userRepository: UserRepository

	@Autowired
	private lateinit var goalRepository: GoalRepository

	@Autowired
	private lateinit var goalMotivationRepository: GoalMotivationRepository

	@Autowired
	private lateinit var projectRepository: ProjectRepository

	override fun run(vararg args: String?) {
		val user = userRepository.save(
			UserEntity(
				id = UUID.randomUUID().toString()
			)
		)
		val projects = projectRepository.saveAll(
			listOf(
				ProjectEntity(
					name = "Project 1",
					user = user,
					goals = listOf(
						GoalEntity(
							name = "Deploy it"
						),
						GoalEntity(
							name = "Deliver it"
						)
					)
				),
				ProjectEntity(
					name = "Project 2",
					user = user,
					goals = listOf(
						GoalEntity(
							name = "~80% Test Coverage"
						),
						GoalEntity(
							name = "Finish implementation"
						)
					)
				)
			)
		)

		taskImpactRepository.saveAll(
			listOf(
				TaskImpactEntity(
					impact = "High",
					description = "High impact"
				),
				TaskImpactEntity(
					impact = "Medium",
					description = "Medium impact"
				),
				TaskImpactEntity(
					impact = "Low",
					description = "Low impact"
				),
				TaskImpactEntity(
					impact = "No",
					description = "No impact"
				)
			)
		)
		taskTypeRepository.saveAll(
			listOf(
				TaskTypeEntity(
					type = "A"
				),
				TaskTypeEntity(
					type = "B"
				),
				TaskTypeEntity(
					type = "C"
				),
				TaskTypeEntity(
					type = "D"
				)
			)
		)
		taskUrgencyRepository.saveAll(
			listOf(
				TaskUrgencyEntity(
					urgency = "High",
					description = "High urgency"
				),
				TaskUrgencyEntity(
					urgency = "Medium",
					description = "Medium urgency"
				),
				TaskUrgencyEntity(
					urgency = "Low",
					description = "Low urgency"
				),
				TaskUrgencyEntity(
					urgency = "No",
					description = "No urgency"
				)
			)
		)
		val allImpacts = taskImpactRepository.findAll().toList()
		val allTypes = taskTypeRepository.findAll().toList()
		val allUrgencies = taskUrgencyRepository.findAll().toList()
		val subtasks = listOf(
			TaskEntity(
				name = "subtask-1",
				major = false,
				type = allTypes.first(),
				urgency = allUrgencies.first(),
				impact = allImpacts.first(),
				project = projects.toList().first(),
				linkedGoals = listOf(
					projects.toList().first().goals.first()
				)
			),
			TaskEntity(
				name = "subtask-2",
				major = false,
				type = allTypes[1],
				urgency = allUrgencies[1],
				impact = allImpacts[1],
				project = projects.toList().first(),
				linkedGoals = listOf(
					projects.toList().first().goals.first()
				)
			),
			TaskEntity(
				name = "subtask-3",
				major = false,
				type = allTypes[2],
				urgency = allUrgencies[2],
				impact = allImpacts[2],
				project = projects.toList().first(),
				linkedGoals = listOf(
					projects.toList().first().goals.first()
				)
			)
		)
		taskRepository.saveAll(subtasks)

		val alltasks = taskRepository.findAll()
		taskRepository.save(
			TaskEntity(
				name = "test-task-for-subs",
				major = true,
				subtasks = alltasks.toList(),
				type = allTypes.first(),
				urgency = allUrgencies.first(),
				impact = allImpacts.first(),
				project = projects.toList().first(),
				linkedGoals = listOf(
					projects.toList().first().goals[1]
				)
			)
		)
	}
}

fun main(args: Array<String>) {
	runApplication<CoreServiceApplication>(*args) {
		addListeners(StartupLogger())
	}
}

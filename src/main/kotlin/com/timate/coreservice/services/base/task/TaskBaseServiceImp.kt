package com.timate.coreservice.services.base.task

import com.timate.coreservice.model.db.repository.task.TaskImpactRepository
import com.timate.coreservice.model.db.repository.task.TaskRepository
import com.timate.coreservice.model.db.repository.task.TaskTypeRepository
import com.timate.coreservice.model.db.repository.task.TaskUrgencyRepository
import com.timate.coreservice.model.dto.TaskDto
import com.timate.coreservice.model.dto.TaskImpactDto
import com.timate.coreservice.model.dto.TaskTypeDto
import com.timate.coreservice.model.dto.TaskUrgencyDto
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class TaskBaseServiceImp(
    val taskRepository: TaskRepository,
    val taskUrgencyRepository: TaskUrgencyRepository,
    val taskImpactRepository: TaskImpactRepository,
    val taskTypeRepository: TaskTypeRepository
): TaskBaseService {

    @Cacheable("task:urgencies", key = "'all'")
    override fun getAllTaskUrgencies(): List<TaskUrgencyDto> {
        return taskUrgencyRepository.findAll()
            .map { TaskUrgencyDto.fromEntity(it) }
    }

    @Cacheable("task:impacts", key = "'all'")
    override fun getAllTaskImpacts(): List<TaskImpactDto> {
        return taskImpactRepository.findAll()
            .map { TaskImpactDto.fromEntity(it) }
    }

    @Cacheable("task:types", key = "'all'")
    override fun getAllTaskTypes(): List<TaskTypeDto> {
        return taskTypeRepository.findAll()
            .map { TaskTypeDto.fromEntity(it) }
    }

    @Cacheable("task:tasks_by_userid", key = "#userId")
    override fun getAlltasks(userId: String): List<TaskDto> {
        return taskRepository.findAll()
            .map { TaskDto.fromEntity(it) }
    }

}
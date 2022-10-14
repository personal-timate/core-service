package com.timate.coreservice.services.base.task

import com.timate.coreservice.model.db.repository.*
import com.timate.coreservice.model.dto.TaskImpactDto
import com.timate.coreservice.model.dto.TaskTypeDto
import com.timate.coreservice.model.dto.TaskUrgencyDto
import org.springframework.stereotype.Service

@Service
class TaskBaseServiceImp(
    val taskRepository: TaskRepository,
    val taskGroupRepository: TaskGroupRepository,
    val taskUrgencyRepository: TaskUrgencyRepository,
    val taskImpactRepository: TaskImpactRepository,
    val taskEisenhauerTypeRepository: TaskEisenhauerTypeRepository
): TaskBaseService {

    override fun getAllTaskUrgencies(): List<TaskUrgencyDto> {
        return taskUrgencyRepository.findAll()
            .map { TaskUrgencyDto.fromEntity(it) }
    }

    override fun getAllTaskImpacts(): List<TaskImpactDto> {
        return taskImpactRepository.findAll()
            .map { TaskImpactDto.fromEntity(it) }
    }

    override fun getAllTaskTypes(): List<TaskTypeDto> {
        return taskEisenhauerTypeRepository.findAll()
            .map { TaskTypeDto.fromEntity(it) }
    }

}
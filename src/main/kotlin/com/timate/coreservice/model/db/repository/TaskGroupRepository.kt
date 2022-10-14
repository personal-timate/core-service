package com.timate.coreservice.model.db.repository

import com.timate.coreservice.model.db.entities.task.TaskGroupEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskGroupRepository: CrudRepository<TaskGroupEntity, Int> {
}
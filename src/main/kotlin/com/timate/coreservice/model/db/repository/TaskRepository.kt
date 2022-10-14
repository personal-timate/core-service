package com.timate.coreservice.model.db.repository

import com.timate.coreservice.model.db.entities.task.TaskEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: CrudRepository<TaskEntity, Int> {
}
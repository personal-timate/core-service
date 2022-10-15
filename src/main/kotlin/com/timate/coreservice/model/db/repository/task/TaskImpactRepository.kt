package com.timate.coreservice.model.db.repository.task

import com.timate.coreservice.model.db.entities.task.TaskImpactEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskImpactRepository: CrudRepository<TaskImpactEntity, Int> {
}
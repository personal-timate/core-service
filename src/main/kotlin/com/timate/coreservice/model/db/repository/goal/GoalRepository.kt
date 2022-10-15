package com.timate.coreservice.model.db.repository.goal

import com.timate.coreservice.model.db.entities.goal.GoalEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GoalRepository: CrudRepository<GoalEntity, Int> {
}
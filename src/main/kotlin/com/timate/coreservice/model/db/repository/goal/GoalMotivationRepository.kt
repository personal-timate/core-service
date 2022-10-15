package com.timate.coreservice.model.db.repository.goal

import com.timate.coreservice.model.db.entities.goal.GoalMotivationEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GoalMotivationRepository: CrudRepository<GoalMotivationEntity, Int> {
}
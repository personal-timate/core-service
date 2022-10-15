package com.timate.coreservice.model.db.repository.project

import com.timate.coreservice.model.db.entities.project.ProjectEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: CrudRepository<ProjectEntity, Int> {
}
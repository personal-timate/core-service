package com.timate.coreservice.model.db.repository.user

import com.timate.coreservice.model.db.entities.user.UserEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<UserEntity, String> {
}
package com.timate.coreservice.model.db.entities.user

import com.timate.coreservice.model.db.entities.project.ProjectEntity
import com.timate.coreservice.model.general.SupportedLocale
import javax.persistence.*

@Entity(name = "users")
@Table(name = "users")
class UserEntity(

    @Id
    val id: String,

    @Column(name = "locale", nullable = false)
    val locale: SupportedLocale = SupportedLocale.ENGLISH,

    @Column(name = "pictureId", nullable = true)
    val pictureId: String? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val projects: List<ProjectEntity> = emptyList(),

)
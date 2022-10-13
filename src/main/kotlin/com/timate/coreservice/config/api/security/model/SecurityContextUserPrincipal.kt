package com.timate.coreservice.config.api.security.model

import org.springframework.security.oauth2.jwt.Jwt

data class SecurityContextUserPrincipal(
    val id: String,
    val username: String,
    val email: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val fullName: String? = null,
    val locale: String?,
    val token: Jwt
)

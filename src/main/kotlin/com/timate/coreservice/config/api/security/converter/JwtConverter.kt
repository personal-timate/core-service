package com.timate.coreservice.config.api.security.converter

import com.timate.coreservice.config.api.security.model.SecurityContextUser
import org.springframework.core.convert.converter.Converter
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter


/**
 * @author Nicholas Dietz
 **/
class JwtConverter: Converter<Jwt, SecurityContextUser> {

    override fun convert(source: Jwt): SecurityContextUser {
        return SecurityContextUser(
            JwtAuthenticationConverter().convert(source)!!.authorities.orEmpty().toMutableList(),
            source
        )
    }
}
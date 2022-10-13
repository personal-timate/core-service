package com.timate.coreservice.utils

import com.timate.coreservice.constants.JwtTokenClaims
import org.springframework.security.oauth2.jwt.Jwt


fun Jwt.getLocale(): String? = this.getClaim(JwtTokenClaims.TOKEN_LOCALE_CLAIM)

fun Jwt.getIssuerAsString(): String = this.getClaim(JwtTokenClaims.TOKEN_ISSUER_CLAIM)

fun Jwt.getType(): String = this.getClaim(JwtTokenClaims.TOKEN_TYPE_CLAIM)

fun Jwt.getFullName(): String? = this.getClaim(JwtTokenClaims.TOKEN_FULL_NAME_CLAIM)

fun Jwt.getUsername(): String = this.getClaim(JwtTokenClaims.TOKEN_USERNAME_CLAIM)

fun Jwt.getFirstName(): String? = this.getClaim(JwtTokenClaims.TOKEN_FIRSTNAME_CLAIM)

fun Jwt.getLastName(): String? = this.getClaim(JwtTokenClaims.TOKEN_LASTNAME_CLAIM)

fun Jwt.getEmail(): String? = this.getClaim(JwtTokenClaims.TOKEN_EMAIL_CLAIM)

fun Jwt.getAuthorizedParty(): String = this.getClaim(JwtTokenClaims.TOKEN_AUTHORIZED_PARTY_CLAIM)

fun Jwt.getUserId(): String = this.getClaim(JwtTokenClaims.TOKEN_USERID_CLAIM)
package com.timate.coreservice.constants

object JwtTokenClaims {

    // locale
    const val TOKEN_LOCALE_CLAIM = "locale"

    // IAM instance where token was retrieved
    const val TOKEN_ISSUER_CLAIM = "iss"

    const val TOKEN_AUDIT_CLAIM = "aud"

    // Type of token
    const val TOKEN_TYPE_CLAIM = "typ"

    // firstname and lastname in one
    const val TOKEN_FULL_NAME_CLAIM = "name"

    // Username
    const val TOKEN_USERNAME_CLAIM = "preferred_username"

    // Firstname
    const val TOKEN_FIRSTNAME_CLAIM = "given_name"

    // Lastname
    const val TOKEN_LASTNAME_CLAIM = "family_name"

    // Email
    const val TOKEN_EMAIL_CLAIM = "email"

    const val TOKEN_AUTHORIZED_PARTY_CLAIM = "azp"

    // Subject of user (id)
    const val TOKEN_USERID_CLAIM = "sub"

    // Realm roles
    const val TOKEN_REALM_ACCESS_CLAIM = "realm_access"

    // Client roles
    const val TOKEN_RESOURCE_ACCESS_CLAIM = "resource_access"

    // General roles
    const val TOKEN_ACCESS_ROLES_CLAIM = "roles"
}
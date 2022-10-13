package com.timate.coreservice.config.api.security.validators

import com.timate.coreservice.constants.JwtTokenClaims
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.Jwt


/**
 * @author Nicholas Dietz @ USU GmbH
 **/
class JwtMultiIssuerValidator(private val issuers: List<String>): OAuth2TokenValidator<Jwt> {

    private val logger = LoggerFactory.getLogger(JwtMultiIssuerValidator::class.java)

    override fun validate(token: Jwt?): OAuth2TokenValidatorResult {
        return token?.let {
            if (issuers.any { token.getClaimAsString(JwtTokenClaims.TOKEN_ISSUER_CLAIM).startsWith(it) }) {
                return@let OAuth2TokenValidatorResult.success()
            } else {
                logger.debug("Token Issuer-Claim Validator failed")
                return@let OAuth2TokenValidatorResult.failure(
                    OAuth2Error(
                        "invalid_request",
                        "Token iss claim is not equal to any of the configured issuers! \n JWT-Token Claim 'iss' contains the url where token is requested and must be *exactly the same* as any of the configured ones.",
                        "https://tools.ietf.org/html/rfc6750#section-3.1"
                    )
                )
            }
        } ?: OAuth2TokenValidatorResult.failure(OAuth2Error("permission_denied"))
    }


}
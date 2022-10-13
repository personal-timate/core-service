package com.timate.coreservice.config.api

import com.timate.coreservice.config.api.security.converter.JwtConverter
import com.timate.coreservice.config.api.security.validators.JwtMultiIssuerValidator
import com.timate.coreservice.config.property.SecurityConfigurationProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.jwt.JwtValidators
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


/**
 * @author Nicholas Dietz @ Nico's Dev
 **/
@Configuration
@EnableWebSecurity
class SecurityConfig(
    val securityConfigurationProperty: SecurityConfigurationProperty
) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .oauth2ResourceServer()
            .jwt()
            .decoder(
                NimbusJwtDecoder
                    .withJwkSetUri("${securityConfigurationProperty.internalAuthServerUrl}/realms/${securityConfigurationProperty.realm}/protocol/openid-connect/certs")
                    .build()
                    .apply {
                        this.setJwtValidator(
                            DelegatingOAuth2TokenValidator(
                                listOf(JwtMultiIssuerValidator(securityConfigurationProperty.issuers)).plus(JwtValidators.createDefault())
                            )
                        )
                    }
            )
            .jwtAuthenticationConverter(
                JwtConverter()
            )
            .and()
            .authenticationEntryPoint(
                HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
            )
            .and()
            .authorizeHttpRequests()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/favicon*").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/swagger-ui*/**").permitAll()
            .antMatchers("/v3/api-docs/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/error**").permitAll()
            .antMatchers("/oauth2-redirect.html").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/**").authenticated()
            .and()
            .csrf().disable()
            .cors().configurationSource(
                UrlBasedCorsConfigurationSource().apply {
                    setCorsConfigurations(
                        mapOf(
                            "/**" to CorsConfiguration().apply {
                                allowedOriginPatterns = listOf("*")
                                allowedMethods = listOf(
                                    "GET", "PUT", "POST", "DELETE", "TRACE", "PATCH", "OPTIONS", "HEAD"
                                )
                                allowedOrigins = listOf("*")
                                allowCredentials = true
                                allowedHeaders = listOf(
                                    "Authorization", "Cache-Control", "Content-Type"
                                )
                            }
                        )
                    )
                }
            )
        return http.build()
    }

}
package com.timate.coreservice.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/*
* Create file "./resources/META-INF/spring-configuration-metadata.json" with content as below in order to get these configuration properties recognized in application.yml/application.properties
* {
*   "properties": [
*     {
*       "name": "security.externalAuthServerUrl",
*       "type": "java.lang.String",
*       "description": "External authorization url of Keycloak e.g. https://$host:$port/auth",
*       "defaultValue": ""
*     },
*     {
*       "name": "security.internalAuthServerUrl",
*       "type": "java.lang.String",
*       "description": "Internal authorization url of Keycloak e.g. https://$host:$port/auth",
*       "defaultValue": ""
*     },
*     {
*       "name": "security.realm",
*       "type": "java.lang.String",
*       "description": "Realm which service should use e.g. 'timate'",
*       "defaultValue": "timate"
*     },
*     {
*       "name": "security.clientId",
*       "type": "java.lang.String",
*       "description": "Client which service should use e.g. 'timate_core-service'",
*       "defaultValue": "timate_core-service"
*     },
*     {
*       "name": "security.issuers",
*       "type": "java.util.List<java.lang.String>",
*       "description": "Allowed token issuers e.g. 'https://$host:$port/auth'",
*       "defaultValue": "[]"
*     }
*   ]
* }
*/
@ConfigurationProperties("security")
@ConstructorBinding
data class SecurityConfigurationProperty(
    val internalAuthServerUrl: String,
    val externalAuthServerUrl: String,
    val clientId: String = "timate_core-service",
    val realm: String = "timate",
    val issuers: List<String> = emptyList()
)
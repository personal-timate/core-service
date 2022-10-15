package com.timate.coreservice.controller

import com.timate.coreservice.config.property.SecurityConfigurationProperty
import com.timate.coreservice.constants.ApiVersion
import com.timate.coreservice.services.base.CacheService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.boot.info.BuildProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Tag(name = "General Controller")
@RestController
class GeneralController(
    val securityConfigurationProperty: SecurityConfigurationProperty,
    val buildProperties: BuildProperties,
    val cacheService: CacheService
) {

    @Operation(summary = "Clear cache.")
    @DeleteMapping("api/${ApiVersion.VERSION_1}/cache")
    fun clearCache(): ResponseEntity<String> {
        return cacheService.clearAllCaches()
    }

    @Operation(summary = "Get index page.")
    @GetMapping("/")
    fun index(): ModelAndView {
        val model = listOf(
            "externalAuthServerUrl" to securityConfigurationProperty.externalAuthServerUrl,
            "version" to buildProperties.version
        )
        return ModelAndView("index", model.toMap())
    }

}
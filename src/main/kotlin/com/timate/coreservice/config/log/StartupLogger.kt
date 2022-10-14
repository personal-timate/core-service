package com.timate.coreservice.config.log

import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.EnumerablePropertySource

internal class StartupLogger : ApplicationListener<ApplicationPreparedEvent> {
    private val secretRegex = "password|accessToken|secret".toRegex(RegexOption.IGNORE_CASE)
    private var alreadyRun = false
    private val logger = LoggerFactory.getLogger(StartupLogger::class.java)

    override fun onApplicationEvent(event: ApplicationPreparedEvent) {
        if (alreadyRun) {
            return
        }
        val environment = event.applicationContext.environment
        val echo = environment.propertySources
            .filterIsInstance<EnumerablePropertySource<*>>()
            .flatMap { propertySource ->
                listOf("\t*** ${propertySource.name}") + collectPropertiesFrom(
                    environment,
                    propertySource
                )
            }
            .joinToString(separator = System.lineSeparator())
        logger.info("Properties ${System.lineSeparator()}$echo")
        alreadyRun = true
    }

    private fun collectPropertiesFrom(
        environment: ConfigurableEnvironment,
        source: EnumerablePropertySource<*>,
    ): List<String> {
        return source.propertyNames.sorted()
            .map { propertyName ->
                val (sourceValue, resolvedValue) = getSourceAndResolvedValue(environment, source, propertyName)
                if (sourceValue == resolvedValue)
                    "\t\t$propertyName=$sourceValue"
                else
                    "\t\t$propertyName=$sourceValue OVERRIDDEN to $resolvedValue"
            }
    }

    private fun getSourceAndResolvedValue(
        environment: ConfigurableEnvironment,
        source: EnumerablePropertySource<*>,
        propertyName: String,
    ): Pair<String, String> {
        val sourceValue = source.getProperty(propertyName).toString()
        if (propertyName == "info.build.version") return Pair(sourceValue, sourceValue)
        val resolvedValue = environment.getProperty(propertyName) ?: ""
        if (propertyName == "line.separator") return Pair(lineSeparatorAsName(sourceValue), lineSeparatorAsName(resolvedValue))
        if (shallBeAnonymized(propertyName)) return Pair(anonymousValueOrBlank(sourceValue), anonymousValueOrBlank(resolvedValue))
        return Pair(sourceValue, resolvedValue)
    }

    private fun anonymousValueOrBlank(value: String) =
        if (value.isNotBlank()) "******" else value

    private fun shallBeAnonymized(propertyName: String) =
        propertyName.contains(secretRegex)

    private fun lineSeparatorAsName(lineSeparator: String) =
        when (lineSeparator) {
            "\n" -> "LF"
            "\r" -> "CR"
            "\r\n" -> "CRLF"
            else -> lineSeparator
        }
}

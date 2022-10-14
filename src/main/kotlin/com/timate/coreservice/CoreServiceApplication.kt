package com.timate.coreservice

import com.timate.coreservice.config.log.StartupLogger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class CoreServiceApplication

fun main(args: Array<String>) {
	runApplication<CoreServiceApplication>(*args) {
		addListeners(StartupLogger())
	}
}

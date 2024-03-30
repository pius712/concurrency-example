package com.pius.boilerplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class KtApplication

fun main(args: Array<String>) {
    runApplication<KtApplication>(*args)
}

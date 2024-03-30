package com.pius.boilerplate.client.example

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "example")
internal data class ExampleConfiguration(
    val description: String
)
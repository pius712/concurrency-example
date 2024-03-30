package com.pius.boilerplate.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.pius.boilerplate"])
@EnableJpaRepositories(basePackages = ["com.pius.boilerplate"])
class JpaConfig {
}
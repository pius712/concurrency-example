package com.pius.concurrency.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.pius.concurrency"])
@EnableJpaRepositories(basePackages = ["com.pius.concurrency"])
class JpaConfig {
}
package com.pius.concurrency.balance

import org.springframework.data.jpa.repository.JpaRepository

interface BalanceLockRepository: JpaRepository<BalanceLockEntity, Long> {
    fun deleteByLockKey(key: String)

}
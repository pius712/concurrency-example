package com.pius.concurrency.balance

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AccountRepository : JpaRepository<AccountEntity, Long> {

    @Modifying
    @Query("update AccountEntity a set a.balance = :balance where a.id = :id and a.balance = :originalBalance")
    fun updateByIdAndBalance(
        @Param("id") id: Long,
        @Param("balance") balance: Long,
        @Param("originalBalance") originalBalance: Long
    ): Int

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from AccountEntity a where a.id = :id")
    fun findByIdUsingPessimisticLock(id: Long): AccountEntity


    @Lock(LockModeType.OPTIMISTIC)
    @Query("select a from AccountEntity a where a.id = :id")
    fun findByIdUsingOptimisticLock(id: Long): AccountEntity
}
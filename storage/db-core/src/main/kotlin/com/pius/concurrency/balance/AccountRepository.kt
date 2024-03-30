package com.pius.concurrency.balance

import org.springframework.data.jpa.repository.JpaRepository
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
}
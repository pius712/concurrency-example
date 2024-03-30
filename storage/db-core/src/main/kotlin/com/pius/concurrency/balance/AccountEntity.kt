package com.pius.concurrency.balance

import com.pius.concurrency.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Version


@Entity
data class AccountEntity(
    var balance: Long,
    @Version
    var version: Long = 0
) : BaseEntity() {
}
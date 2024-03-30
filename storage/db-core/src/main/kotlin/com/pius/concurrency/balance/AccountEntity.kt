package com.pius.concurrency.balance

import com.pius.concurrency.BaseEntity
import jakarta.persistence.Entity


@Entity
data class AccountEntity(
    var balance: Long,
): BaseEntity() {
}
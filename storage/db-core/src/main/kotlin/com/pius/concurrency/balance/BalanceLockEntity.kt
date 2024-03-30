package com.pius.concurrency.balance

import com.pius.concurrency.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name="balance_lock",
    indexes = [Index(name = "lock_key_index", columnList = "lockKey", unique = true)]
)
class BalanceLockEntity(
    val lockKey:String
) :BaseEntity(){


}
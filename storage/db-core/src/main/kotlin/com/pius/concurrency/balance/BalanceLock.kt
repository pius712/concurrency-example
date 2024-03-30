package com.pius.concurrency.balance

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
class BalanceLock(
    private val balanceLockRepository: BalanceLockRepository
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun lock(key:String)  {
        balanceLockRepository.save(BalanceLockEntity(key))
    }

    fun release(key:String) {
        balanceLockRepository.deleteByLockKey(key)
    }

}
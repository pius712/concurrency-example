package com.pius.concurrency.core.application

import com.pius.concurrency.balance.AccountEntity
import com.pius.concurrency.balance.AccountRepository
import com.pius.concurrency.balance.BalanceLock
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AccountWriter(
    private val lock: BalanceLock,
    private val accountRepository: AccountRepository
) {
    fun createAccount(money: Long): AccountEntity {
        return accountRepository.save(AccountEntity(money))
    }

    @Transactional
    fun decrementBalance(accountId: Long, amount: Long) {
        val account = accountRepository.findByIdOrNull(accountId) ?: throw RuntimeException("Account not found")
        account.balance -= amount

    }


    @Transactional
    fun decreaseBalanceV2(accountId: Long, amount: Long): Boolean {
        try {
            lock.lock(accountId.toString())
        } catch (_: Exception) {
            throw RuntimeException("Lock failed")
        }

        return try {
            val account = accountRepository.findByIdOrNull(accountId) ?: throw RuntimeException("Account not found")
            account.balance -= amount
            true
        } finally {
            lock.release(accountId.toString())
        }


    }


    @Transactional
    fun decreaseBalanceV3(id: Long, amount: Long) {

        val account = accountRepository.findByIdOrNull(id) ?: throw RuntimeException("Account not found")
        accountRepository.updateByIdAndBalance(id, account.balance - amount, account.balance)
    }
    
    fun decreaseBalanceV4(id: Long, amount: Long) {

    }
}
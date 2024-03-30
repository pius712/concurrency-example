package com.pius.concurrency.core.application

import com.pius.concurrency.balance.AccountEntity
import org.springframework.stereotype.Service

@Service
class AccountServiceV1(

    private val writer: AccountWriter
) {
    fun createAccount(money: Long): AccountEntity {
        return writer.createAccount(money)
    }

    fun decrementBalance(id: Long, amount: Long) {
        writer.decrementBalance(id, amount)
    }

    fun decreaseBalanceV2(id: Long, amount: Long) {
        while (true) {
            try {
                writer.decreaseBalanceV2(id, amount)
                return
            } catch (e: Exception) {
                println("Retrying")
                Thread.sleep(500)
            }

        }
    }


}


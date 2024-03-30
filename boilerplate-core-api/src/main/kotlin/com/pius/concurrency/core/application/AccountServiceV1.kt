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

    /**
     * 동시성 제어 X
     * */
    fun decreaseV1(id: Long, amount: Long) {
        writer.decrementBalance(id, amount)
    }


    /**
     * 동시성 제어 - table lock
     * */
    fun decreaseV2(id: Long, amount: Long) {
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

    /**
     * where 절 사용하는 방법
     * -> 실패한다 왜냐하면, where 절에서 동시성 발생한 경우,
     *   이미 balance 값이 변경되어 버려서 where 절에서 조건을 만족하지 않기 때문이다.
     */
    fun decreaseV3(id: Long, amount: Long) {
        writer.decreaseBalanceV3(id, amount)
    }


    /**
     * 동시성 제어 - pessimistic lock
     * */
    fun decreaseV4(id: Long, amount: Long) {
        writer.decreaseBalanceV4(id, amount)

}


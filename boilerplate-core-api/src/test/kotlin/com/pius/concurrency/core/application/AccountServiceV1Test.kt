package com.pius.concurrency.core.application

import com.pius.concurrency.balance.AccountRepository
import com.pius.concurrency.balance.BalanceLock
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.TestConstructor
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class AccountServiceV1Test(
    private val accountService: AccountServiceV1,
    private val repository: AccountRepository,
    private val lock: BalanceLock
) {


    @Test
    fun `아무런 락 없이 진행`() {
        // 테스트 실패
        val totalCount = 100
        val amount = 100L
        val latch = CountDownLatch(totalCount)
        val account = accountService.createAccount(totalCount * amount)
        val executorService = Executors.newFixedThreadPool(32)

        for (i in 1..100) {
            executorService.execute {
                accountService.decreaseV1(account.id!!, amount)
                latch.countDown()
            }
        }

        // 테스트 스레드 대기
        latch.await()

        val saved = repository.findByIdOrNull(account.id!!) ?: throw RuntimeException("Account not found")
        Assertions.assertThat(saved.balance).isEqualTo(0)
    }


    @Test
    fun `balance lock 테이블 사용`() {
        // 테스트 실패
        val totalCount = 100
        val amount = 100L
        val latch = CountDownLatch(totalCount)
        val account = accountService.createAccount(totalCount * amount)
        val executorService = Executors.newFixedThreadPool(32)
        var success = 0
        for (i in 1..100) {
            executorService.execute {
                accountService.decreaseV2(account.id!!, amount)
//                if (isSuccess) success++
                latch.countDown()
            }
        }

        // 테스트 스레드 대기
        latch.await()

        val saved = repository.findByIdOrNull(account.id!!) ?: throw RuntimeException("Account not found")
        Assertions.assertThat(saved.balance).isEqualTo(0)
    }


    @Test
    fun `where 절 사용`() {
        // 테스트 실패
        val totalCount = 100
        val amount = 100L
        val latch = CountDownLatch(totalCount)
        val account = accountService.createAccount(totalCount * amount)
        println(totalCount * amount)
        val executorService = Executors.newFixedThreadPool(32)
        var success = 0
        for (i in 1..totalCount) {
            executorService.execute {
                accountService.decreaseV3(account.id!!, amount)
//                if (isSuccess) success++
                latch.countDown()
            }
        }

        // 테스트 스레드 대기
        latch.await()

        val saved = repository.findByIdOrNull(account.id!!) ?: throw RuntimeException("Account not found")
        Assertions.assertThat(saved.balance).isEqualTo(0)
    }

}
package com.pius.boilerplate.client.example

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ExampleClientTest(
    private val exampleClient: ExampleClient
) {

    @Test
    fun `example client`() {
        val result = exampleClient.getExample()
        println(result)
    }
}
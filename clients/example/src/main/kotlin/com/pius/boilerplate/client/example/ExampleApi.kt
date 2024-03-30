package com.pius.boilerplate.client.example

import com.pius.boilerplate.client.example.spec.response.ExampleResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "example", url = "\${example.api.url}")
internal interface ExampleApi {

    @GetMapping("/posts/{id}")
    fun getExample(@PathVariable id: Long): ExampleResponseDto
}
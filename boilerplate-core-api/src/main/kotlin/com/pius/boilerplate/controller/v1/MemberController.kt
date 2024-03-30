package com.pius.boilerplate.controller.v1

import com.pius.boilerplate.controller.v1.response.MemberResponseDto
import com.pius.boilerplate.core.application.MemberService
import com.pius.boilerplate.support.response.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {


    @PostMapping("/create")
    fun create(): Long {
        return memberService.save()
    }

    @GetMapping("/{memberId}")
    fun getMember(
        @PathVariable memberId: Long
    ): ApiResponse<MemberResponseDto> {
        val member = memberService.find(memberId)
        return ApiResponse.ok(MemberResponseDto(member.name))
    }
}
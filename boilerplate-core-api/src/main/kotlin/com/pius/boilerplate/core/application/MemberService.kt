package com.pius.boilerplate.core.application

import com.pius.boilerplate.member.MemberEntity
import com.pius.boilerplate.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    fun save(): Long {
        val entity = memberRepository.save(MemberEntity("test"))
        return entity.id!!
    }

    fun find(memberId: Long): MemberEntity {
        return memberRepository.findByIdOrNull(memberId) ?: throw RuntimeException("Not found")
    }
}
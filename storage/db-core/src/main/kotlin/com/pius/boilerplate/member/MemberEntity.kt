package com.pius.boilerplate.member

import com.pius.boilerplate.BaseEntity
import jakarta.persistence.Entity


@Entity
data class MemberEntity(
    val name:String
): BaseEntity() {
}
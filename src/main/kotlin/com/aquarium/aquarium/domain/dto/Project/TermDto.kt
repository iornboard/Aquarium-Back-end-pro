package com.aquarium.aquarium.domain.dto.Project

import com.aquarium.aquarium.domain.dto.User.UserDto
import com.aquarium.aquarium.domain.entity.Project.Term
import com.aquarium.aquarium.domain.entity.User.User
import java.time.LocalDateTime



data class TermDto(

    var termId : Int = 0,
    var termTitle : String? = null,
    var termDescription : String? = null,
    var termText : String? = null,
    var termRequired : Boolean = false,
    var termOwnerId : Int = 0,
    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var agreesId : List<Int>? = null,
    var agreesInfo : List<UserDto>? = null,

) {

    fun toTerm(agrees : Set<User>) : Term {
        return Term(
            termId = termId,
            termTitle = termTitle,
            termDescription = termDescription,
            termText = termText,
            termRequired = termRequired,
            termOwnerId = termOwnerId,
            createdAt = createdAt,
            agrees = agrees
        )
    }


}

package com.aquarium.aquarium.domain.repository.Project

import com.aquarium.aquarium.domain.entity.Project.Term
import com.aquarium.aquarium.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository

interface TermRepository : JpaRepository<Term?, Int?> {

    open fun findByAgrees(user : User) : List<Term>?
}
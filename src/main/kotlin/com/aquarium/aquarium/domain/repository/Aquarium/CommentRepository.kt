package com.aquarium.aquarium.domain.repository.Aquarium

import com.aquarium.aquarium.domain.entity.Aquarium.Comment
import com.aquarium.aquarium.domain.entity.Aquarium.Mention
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment?, Int?> {

    open fun findAllByMent(mention: Mention) : List<Comment>?

}
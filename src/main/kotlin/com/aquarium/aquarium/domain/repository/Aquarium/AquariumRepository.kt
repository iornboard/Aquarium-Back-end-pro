package com.aquarium.aquarium.domain.repository.Aquarium


import com.aquarium.aquarium.domain.entity.Aquarium.Aquarium
import com.aquarium.aquarium.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository

interface AquariumRepository : JpaRepository<Aquarium?, Int?> {
    open fun findAllByUser(user : User) : List<Aquarium>?
}
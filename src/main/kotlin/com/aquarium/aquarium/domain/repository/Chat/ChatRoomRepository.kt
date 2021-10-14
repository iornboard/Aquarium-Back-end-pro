package com.aquarium.aquarium.domain.repository.Chat

import com.aquarium.aquarium.domain.entity.Chat.ChatRoom
import com.aquarium.aquarium.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<ChatRoom?, Int?> {
    open fun findChatRoomsByUser(user : User) : MutableList<ChatRoom?>
}
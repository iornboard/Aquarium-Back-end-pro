package com.aquarium.aquarium.domain.repository.Chat

import com.aquarium.aquarium.domain.entity.Chat.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageRepository : JpaRepository<ChatMessage?, Int?> {
    open fun findAllByRoom_RoomId(roomId : Int) : MutableList<ChatMessage?>
}
package com.aquarium.aquarium.domain.entity.Aquarium

import com.aquarium.aquarium.domain.dto.Aquarium.AquariumDto
import com.aquarium.aquarium.domain.dto.User.UserDto
import com.aquarium.aquarium.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Aquarium(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    @Column(name = "aqrm_id")
    var aqrmId : Int = 0,

    var aqrmTitle : String? = null,
    var aqrmtext : String? = null,
    var aqrmThumbnail : String? = null,
    var aqrmContentType : String? = null,

    var aqrmImgUrl : String? = null,
    var aqrmVideoUrl : String? = null,

    var aqrmMentionCount : Int = 0,
    var aqrmLikeCount : Int = 0,
    var aqrmViewCount : Int = 0,

    var aqrmIsPrivate : Boolean = false,
    var aqrmIsBlinded : Boolean = false,

    @CreatedDate
    @JsonIgnore
    var createdAt : LocalDateTime? = LocalDateTime.now(),

    @LastModifiedBy
    @JsonIgnore
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonManagedReference
    var user : User
) {

    fun toAquariumDto() : AquariumDto {
        return AquariumDto(
            aqrmId = aqrmId,
            aqrmTitle = aqrmTitle,
            aqrmtext = aqrmtext,
            aqrmThumbnail = aqrmThumbnail,
            aqrmContentType = aqrmContentType,
            aqrmImgUrl = aqrmImgUrl,
            aqrmVideoUrl = aqrmVideoUrl,
            aqrmMentionCount = aqrmMentionCount,
            aqrmLikeCount = aqrmLikeCount,
            aqrmViewCount = aqrmViewCount,
            aqrmIsPrivate = aqrmIsPrivate,
            aqrmIsBlinded = aqrmIsBlinded,
            createdAt = createdAt,
            updatedAt = updatedAt,

            userId = user.userId
        )
    }

    fun toJoinedAquariumDto() : AquariumDto {
        return AquariumDto(
            aqrmId = aqrmId,
            aqrmTitle = aqrmTitle,
            aqrmtext = aqrmtext,
            aqrmThumbnail = aqrmThumbnail,
            aqrmContentType = aqrmContentType,
            aqrmImgUrl = aqrmImgUrl,
            aqrmVideoUrl = aqrmVideoUrl,
            aqrmMentionCount = aqrmMentionCount,
            aqrmLikeCount = aqrmLikeCount,
            aqrmViewCount = aqrmViewCount,
            aqrmIsPrivate = aqrmIsPrivate,
            aqrmIsBlinded = aqrmIsBlinded,
            createdAt = createdAt,
            updatedAt = updatedAt,

            userInfo = UserDto().toUserDto(user)
        )
    }
}

package com.aquarium.aquarium.domain.dto.Aquarium

import java.time.LocalDateTime

data class MentionMakerDto (

    var mentId : Int = 0,

    var mentText : String? = null,

    var x : Int = 0,
    var y : Int = 0,
    var start : Int = 0,
    var end : Int = 0,

    var avatarImg : String? = null,

    var mentIsPrivate : Boolean = false,
    var mentIsBlinded : Boolean = false,

    var createdAt : LocalDateTime? = null,
    var updatedAt : LocalDateTime? = null


)

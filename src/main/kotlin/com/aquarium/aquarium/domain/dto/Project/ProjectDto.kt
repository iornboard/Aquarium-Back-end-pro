package com.aquarium.aquarium.domain.entity.Project

import com.aquarium.aquarium.domain.dto.User.UserDto
import com.aquarium.aquarium.domain.entity.User.User
import java.time.LocalDateTime


data class ProjectDto (

    var projectId : Int = 0,
    var projectName : String? = null,
    var projectDescription : String? = null,
    var projectTaskCount : Int = 0,
    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    var userId : Int? = null,

    var userInfo : UserDto? = null

    ) {
    fun toProject( user: User ) : Project{
        return Project(
            projectId = projectId,
            projectName = projectName,
            projectDescription = projectDescription,
            projectTaskCount = projectTaskCount,
            createdAt = createdAt,
            updatedAt = updatedAt,
            user = user
        )
    }
}
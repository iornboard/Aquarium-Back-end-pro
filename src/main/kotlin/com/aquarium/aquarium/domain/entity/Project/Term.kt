package com.aquarium.aquarium.domain.entity.Project

import com.aquarium.aquarium.domain.dto.Project.TermDto
import com.aquarium.aquarium.domain.dto.User.UserDto
import com.aquarium.aquarium.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Term (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        @Column(name = "term_Id")
        var termId : Int = 0,

        var termTitle : String? = null,
        var termDescription : String? = null,
        var termText : String? = null,
        var termRequired : Boolean = false,

        var termOwnerId : Int = 0,

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @ManyToMany
        @JoinTable(
                name = "agree_assined",
                joinColumns = [JoinColumn(name = "term_Id" ,referencedColumnName = "term_Id")],
                inverseJoinColumns = [JoinColumn(name = "agree_id" ,referencedColumnName = "user_Id")]
        )
        @JsonIgnore
        var agrees : Set<User>,


        )  {

        fun toTermDto() : TermDto {
                return TermDto(
                        termId = termId,
                        termTitle = termTitle,
                        termDescription = termDescription,
                        termText = termText,
                        termRequired = termRequired,
                        termOwnerId = termOwnerId,
                        createdAt = createdAt,
                        agreesInfo = agrees.map{it -> UserDto().toUserDto(it)},
                        agreesId =  agrees.map{it -> it.userId}
                )
        }
}
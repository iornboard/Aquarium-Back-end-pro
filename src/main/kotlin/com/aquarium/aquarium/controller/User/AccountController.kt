package com.aquarium.aquarium.controller.User


import com.aquarium.aquarium.config.jwt.JwtProperties
import com.aquarium.aquarium.domain.dto.User.UserDto
import com.aquarium.aquarium.domain.dto._Ect.AuthDto
import com.aquarium.aquarium.domain.entity.User.User
import com.aquarium.aquarium.domain.repository.User.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

import org.springframework.web.bind.annotation.RequestBody

import org.springframework.web.bind.annotation.PostMapping


@RestController
@RequestMapping("/api")
internal class AccountController (userRepository: UserRepository) {

    @Autowired
    private val userRepository: UserRepository

    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @GetMapping("/auth")
    fun auth(@RequestHeader( JwtProperties.HEADER_STRING ) jwtHeader : String):  ResponseEntity<Any> {

        // 해더가 있는지 or 우리 토큰이 맟는지 -> 없으면 끝냄
        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return ResponseEntity.status(400).body("Failed to fetch information!!")
        }

        // 위에서 받은 토큰확인해서 username을 확인
        val token: String = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "")

        val username: String? = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
            .getClaim("username").asString()

        val user : User? = userRepository.findByUsername(username)
        val responseAuth = AuthDto()

        if(user != null){
            responseAuth.userId = user.userId
            responseAuth.userEmail = user.userEmail
            responseAuth.userFullname = user.userFullname
            responseAuth.userNickname = user.userNickname
            responseAuth.userImgUrl = user.userImgUrl
            responseAuth.userRole = user.userRole
        }
        return ResponseEntity.ok(responseAuth)
    }

    @GetMapping("/auth-userpage")
    fun authUserPage( @RequestParam  userNickname : String ): ResponseEntity<Any> {

        println(userNickname)

        val user : User? = userRepository.findByUserNickname(userNickname)

        if(user != null){
            return ResponseEntity.ok(UserDto().toUserDto(user))

        }else{
            return ResponseEntity.status(400).body("Failed to fetch information!!")
        }
    }


    @PostMapping("/join")
    fun join( @RequestBody  user : User?) : ResponseEntity<Any?> {

        if(user?.userEmail!=""&& user?.password!=""&& user?.userNickname!=""&& user?.userFullname!=""){

            return user?.let{
                user.userRole = "USER_ROLE"
                user.username = user.userEmail
                val rawPassword : String? = user.password
                val encPassword = bCryptPasswordEncoder!!.encode(rawPassword)
                user.password = encPassword
                user.provider = "aquarium"
                user.providerId ="aquarium"

                userRepository.save(user)

                return ResponseEntity.status(200).build()
            } ?: let{
                return ResponseEntity.status(400).body("not valid information")
            }

        } else {
            return ResponseEntity.status(400).body("not valid information")
        }
    }



    @PatchMapping("/user-image")
    fun userImgUpdate( @RequestBody responseUser : User) : ResponseEntity<Any?> {

        val targetUser : User? = userRepository.findByIdOrNull(responseUser.userId)

        return targetUser?.let{
            targetUser.userImgUrl = responseUser.userImgUrl
            userRepository.save(targetUser)

            return ResponseEntity.ok().build()

        } ?: let {
            return ResponseEntity.status(400).body("not found User!!")
        }

    }




    @GetMapping("/user-info")
    fun getUserIdToInfo( @RequestParam userId : Int ): ResponseEntity<Any?> {

        val targetUserInfo : User? = userRepository.findByIdOrNull(userId)

        return targetUserInfo?.let{
            return ResponseEntity.ok(targetUserInfo)
        } ?: let{
            return ResponseEntity.status(400).body("not found User!!")
        }
    }




    @GetMapping("/user-info-all")
    fun getAllUserIdToInfo( @RequestParam userId : Int? ): ResponseEntity<Any?> {

        val targetUserInfo = userRepository.findAll()

        if(userId != null){
            val requestUser = userRepository.findByIdOrNull(userId)

            if(requestUser != null) return ResponseEntity.ok(targetUserInfo.toSet() - setOf<User>(requestUser))

            else return  ResponseEntity.status(400).body("not found User!!")

        }
        else{
            return ResponseEntity.ok( targetUserInfo.toSet() )
        }
    }

    init {
        this.userRepository = userRepository
    }

}
package com.aquarium.aquarium.service.User

import com.aquarium.aquarium.domain.dto.User.UserDto
import com.aquarium.aquarium.domain.entity.User.User
import com.aquarium.aquarium.domain.repository.User.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    //C
    fun userCreate(userDto: UserDto): User?{

        return userDto.let{
            User().toUser(userDto)
        }.let{
            userRepository.save(it)
        }

    }

    //R
    fun userRead(userId : Int) : User?{
        return userRepository.getOne(userId)
    }

    //U
    fun userUpdate(userDto: UserDto): User?{
        return userDto.let{
            User().toUser(userDto)
        }.let{
            userRepository.save(it)
        }
    }

    //D
    fun userDelete(userId : Int){
        userId.let{
            userRepository.getOne(userId)
        }.let{
            userRepository.delete(it)
        }
    }


}
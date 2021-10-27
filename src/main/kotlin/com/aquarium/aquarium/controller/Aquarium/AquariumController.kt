package com.aquarium.aquarium.controller.Aquarium

import com.aquarium.aquarium.domain.dto.Aquarium.AquariumDto
import com.aquarium.aquarium.domain.repository.Aquarium.AquariumRepository
import com.aquarium.aquarium.domain.repository.User.UserRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class AquariumController(userRepository : UserRepository, aquariumRepository: AquariumRepository) {

    private val userRepository : UserRepository
    private val aquariumRepository: AquariumRepository

    @PostMapping("/create-aqrm")
    fun createAquarium( @RequestBody aqrm : AquariumDto) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(aqrm.userId)

        return user?.let {
            var res = aquariumRepository.save(aqrm.toAquarium(it))
            return ResponseEntity.ok(res)
        } ?: let {
            return ResponseEntity.status(400).build()
        }
    }


    @GetMapping("/aqrm")
    fun readAquarium(@RequestParam aqrmId : Int) : ResponseEntity<Any?> {

        val aqrm = aquariumRepository.findByIdOrNull(aqrmId)

        return aqrm?.let {
            return ResponseEntity.ok( it.toAquariumDto() )
        } ?: let {
            return ResponseEntity.status(400).build()
        }
    }


    @GetMapping("/all-aqrm")
    fun readAllAquarium(@RequestParam userId: Int) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(userId)

        return user?.let{
            aquariumRepository.findAllByUser(it)?.let {
                return ResponseEntity.ok( it.map{it.toAquariumDto()} )

            } ?: run {
                return ResponseEntity.ok().body(null)
            }

        } ?: let {
            return ResponseEntity.status(400).build()
        }
    }

    @GetMapping("/pull-aqrm")
    fun readPullAquarium() : ResponseEntity<Any?> {
            aquariumRepository.findAll(Sort.by(Sort.Direction.DESC,"aqrmId")).let {
                return ResponseEntity.ok( it.map{it?.toJoinedAquariumDto()} )
            }
    }


    @GetMapping("/update-aquarium")
    fun updateAquarium() {

    }


    @GetMapping("/delete-aquarium")
    fun deleteAquarium() {

    }


    init {
        this.userRepository = userRepository
        this.aquariumRepository = aquariumRepository
    }


}
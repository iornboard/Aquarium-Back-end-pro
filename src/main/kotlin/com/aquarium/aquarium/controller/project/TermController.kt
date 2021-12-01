package com.aquarium.aquarium.controller.project

import com.aquarium.aquarium.domain.dto.Project.TermDto
import com.aquarium.aquarium.domain.entity.User.User
import com.aquarium.aquarium.domain.repository.Project.TermRepository
import com.aquarium.aquarium.domain.repository.User.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class TermController(userRepository : UserRepository, termRepository : TermRepository) {

    private val userRepository : UserRepository
    private val termRepository : TermRepository

    @PostMapping("/create-term")
    fun createTerm( @RequestBody term : TermDto) : ResponseEntity<Any?> {

        term.agreesId?.let{
            val agrees = userRepository.findAllById(it).toSet() as Set<User>?


            agrees?.let{
                val res = termRepository.save(term.toTerm(agrees)).toTermDto()
                return ResponseEntity.status(200).body(res)
            } ?: let{
                return ResponseEntity.status(400).body("not found User!!")
            }

        } ?: let{
            return ResponseEntity.status(400).build()
        }

    }


    @GetMapping("/term")
    fun readTerm( @RequestParam termId : Int ) : ResponseEntity<Any?> {

        val term = termRepository.findByIdOrNull(termId)

        term?.let{
            return ResponseEntity.ok(it.toTermDto())
        } ?: let{
            return ResponseEntity.status(400).build()
        }
    }

    @GetMapping("/all-user-term")
    fun readUserTerm( @RequestParam userId : Int ) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(userId)

        user?.let{
            val terms = termRepository.findByAgrees(user)

            terms?.let{
                return ResponseEntity.ok(it.map{ it.toTermDto()})
            } ?: let{
                return ResponseEntity.ok().body(null)
            }

        } ?: let{
            return ResponseEntity.status(400).body("not found User!!")
        }

    }



    @PutMapping("/update-term")
    fun updateTerm( @RequestBody newTerm : TermDto ) : ResponseEntity<Any?>  {

        newTerm.agreesId?.let{
            val agrees = userRepository.findAllById(it).toSet() as Set<User>?

            agrees?.let{
                val res = termRepository.save(newTerm.toTerm(agrees)).toTermDto()
                return ResponseEntity.status(200).body(res)
            } ?: let{
                return ResponseEntity.status(400).body("not found User!!")
            }

        } ?: let{
            return ResponseEntity.status(400).build()
        }

    }


    @GetMapping("/delete-term")
    fun delete() {

    }


    init {
        this.userRepository  = userRepository
        this.termRepository  = termRepository
    }


}
package com.example.notes.endpoint

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("notes")
class NotesController {

    @GetMapping
    fun retrieveNotes(): List<NotesResponse> {
        return listOf(NotesResponse("1", "First note in Kotlin"))
    }

    @GetMapping("{id}")
    fun getNoteById(@PathVariable("id") id: String): NotesResponse {
        return NotesResponse("1", "First note in Kotlin")
    }
}

class NotesResponse(val id: String, val text: String)
package com.example.notes.endpoint

import com.example.notes.domain.Note
import com.example.notes.domain.NotesRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notes")
class NotesController(private val notesRepository: NotesRepository) {

    @GetMapping
    fun retrieveNotes(): List<NotesResponse> {
        return notesRepository.retrieveAll().map { note -> NotesResponse(note) }
    }

    @GetMapping("{id}")
    fun getNoteById(@PathVariable("id") id: String): NotesResponse {
        return NotesResponse("1", "First note in Kotlin")
    }
}

class NotesResponse(val id: String, val text: String) {

    constructor(note: Note): this(note.id, note.text)
}
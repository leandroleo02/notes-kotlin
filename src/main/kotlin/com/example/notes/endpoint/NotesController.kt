package com.example.notes.endpoint

import com.example.notes.application.NoteService
import com.example.notes.application.config.Logging
import com.example.notes.application.config.logger
import com.example.notes.domain.Note
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notes")
class NotesController(private val noteService: NoteService) {

    companion object: Logging {
        val logger = logger()
    }

    @GetMapping
    fun retrieveNotes(): List<NotesResponse> {
        logger.info("Retrieving Notes")
        return noteService.retrieveNotes()
                .convert { NotesResponse(it) }
    }

    @GetMapping("{id}")
    fun getNoteById(@PathVariable("id") id: String): ResponseEntity<NotesResponse> {
        logger.info("Retrieving Note $id")
        return noteService.getNoteById(id)?.let {
            ResponseEntity(NotesResponse(it), HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }
}

data class NotesResponse(val id: String,
                    val title: String,
                    val category: String,
                    val text: String,) {
    constructor(note: Note):
            this(note.id, note.title, note.category, note.text)
}
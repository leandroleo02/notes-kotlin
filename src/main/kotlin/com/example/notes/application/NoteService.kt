package com.example.notes.application

import com.example.notes.application.config.Logging
import com.example.notes.application.config.logger
import com.example.notes.domain.Note
import com.example.notes.domain.Notes
import com.example.notes.domain.NotesRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class NoteService(private val mongoNotesRepository: NotesRepository) {

    companion object: Logging {
        val logger = logger()
    }

    fun retrieveNotes(): Notes {
        logger.info("Retrieving Notes")
        return mongoNotesRepository.retrieveAll()
    }

    fun getNoteById(@PathVariable("id") id: String): Note? {
        logger.info("Retrieving Note $id")
        return mongoNotesRepository.findById(id)
    }
}
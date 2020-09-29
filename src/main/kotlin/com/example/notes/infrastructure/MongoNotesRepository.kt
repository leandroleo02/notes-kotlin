package com.example.notes.infrastructure

import com.example.notes.domain.Note
import com.example.notes.domain.Notes
import com.example.notes.domain.NotesRepository
import com.example.notes.infrastructure.documents.NoteDocument
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
@Profile("!dev")
class MongoNotesRepository(private val mongoRepositoryDelegate: MongoRepositoryDelegate): NotesRepository {

    override fun retrieveAll(): Notes {
        return mongoRepositoryDelegate.findAll()
                .map(::toNote)
                .let(::Notes)
    }

    override fun findById(id: String): Note? {
        return mongoRepositoryDelegate.findById(id)
                .map(::toNote)
                .orElse(null)
    }

    private fun toNote(noteDocument: NoteDocument): Note {
        return Note(noteDocument.id, noteDocument.title, noteDocument.category, noteDocument.text)
    }
}

@Repository
@Profile("!dev")
interface MongoRepositoryDelegate: MongoRepository<NoteDocument, String>

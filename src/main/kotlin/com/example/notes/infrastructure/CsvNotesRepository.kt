package com.example.notes.infrastructure

import com.example.notes.domain.Note
import com.example.notes.domain.NotesRepository
import org.springframework.stereotype.Repository

@Repository
class CsvNotesRepository(private val csvReader: CsvReader): NotesRepository {

    private val filePath = filePath()

    override fun retrieveAll(): List<Note> {
        return csvReader.readFile(filePath!!)
                .map(::toNotes)
    }

    override fun findById(id: String): Note? {
        return csvReader.readFile(filePath!!)
                .map(::toNotes)
                .find { note -> note.id == id }
    }

    private fun toNotes(line: Array<String>): Note {
        return Note(line[0], line[1])
    }

    private fun filePath(): String? {
        return this::class.java.classLoader
                .getResource("notes/notes.csv")?.path
    }
}

package com.example.notes.infrastructure

import com.example.notes.domain.Note
import com.example.notes.domain.NotesRepository
import org.springframework.stereotype.Repository
import java.io.InputStream

@Repository
class CsvNotesRepository(private val csvReader: CsvReader): NotesRepository {

    override fun retrieveAll(): List<Note> {
        return csvReader.readFile(file()!!)
                .map(::toNotes)
    }

    override fun findById(id: String): Note? {
        return csvReader.readFile(file()!!)
                .map(::toNotes)
                .find { note -> note.id == id }
    }

    private fun toNotes(line: Array<String>): Note {
        return Note(line[0], line[1])
    }

    private fun file(): InputStream? {
        return this::class.java.classLoader
                .getResourceAsStream("notes/notes.csv")
    }
}

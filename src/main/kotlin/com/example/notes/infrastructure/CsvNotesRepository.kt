package com.example.notes.infrastructure

import com.example.notes.domain.Note
import com.example.notes.domain.Notes
import com.example.notes.domain.NotesRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.io.InputStream

@Repository
@Profile("dev")
class CsvNotesRepository(private val csvReader: CsvReader): NotesRepository {

    override fun retrieveAll(): Notes {
        val notes = csvReader.readFile(file()!!)
                .map(::toNote)
        return Notes(notes)
    }

    override fun findById(id: String): Note? {
        return csvReader.readFile(file()!!)
                .map(::toNote)
                .find { note -> note.id == id }
    }

    private fun toNote(line: Array<String>): Note {
        return Note(line[0], line[1], line[2], line[3])
    }

    private fun file(): InputStream? {
        return this::class.java.classLoader
                .getResourceAsStream("notes/notes.csv")
    }
}

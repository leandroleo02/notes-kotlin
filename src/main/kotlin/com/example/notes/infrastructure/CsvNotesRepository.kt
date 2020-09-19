package com.example.notes.infrastructure

import com.example.notes.domain.Note
import com.example.notes.domain.NotesRepository
import org.springframework.stereotype.Repository

@Repository
class CsvNotesRepository(private val csvReader: CsvReader): NotesRepository {

    private val fileUrl = this::class.java.classLoader.getResource("notes/notes.csv")

    override fun retrieveAll(): List<Note> {
        return csvReader.readFile(fileUrl.path)
                .map { line -> Note(line[0], line[1]) }
    }
}
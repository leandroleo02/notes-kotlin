package com.example.notes.domain

import com.example.notes.infrastructure.CsvNotesRepository
import com.example.notes.infrastructure.CsvReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NotesTest {

    private lateinit var notes: Notes

    @BeforeEach
    fun setUp() {
        val csvReader = CsvReader()
        val csvNotesRepository = CsvNotesRepository(csvReader)
        notes = Notes(csvNotesRepository.retrieveAll())
    }

    @Test
    fun findNotesByCategory() {
        val notes = notes.findNotesByCategory("gold")
        assertThat(notes).hasSize(2)
    }
}
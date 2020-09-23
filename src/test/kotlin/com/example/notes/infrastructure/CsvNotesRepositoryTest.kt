package com.example.notes.infrastructure

import com.example.notes.domain.Note
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CsvNotesRepositoryTest {

    private val csvReader = CsvReader()
    private val csvNotesRepository = CsvNotesRepository(csvReader)

    @Test
    fun readAllNotes() {
        val notes = csvNotesRepository.retrieveAll()
        assertThat(notes).hasSize(5)
    }

    @Test
    fun findNoteById() {
        val note = csvNotesRepository.findById("5")
        assertThat(note).isNotNull
    }

    @Test
    fun findNoteWithAllValues() {
        val note = csvNotesRepository.findById("5")
        assertThat(note)
                .usingRecursiveComparison()
                .isEqualTo(Note("5", "Cold", "white", "Winter is coming!"))
    }
}
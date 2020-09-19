package com.example.notes.infrastructure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CsvNotesRepositoryTest {

    private val csvNotesRepository: CsvNotesRepository = CsvNotesRepository()

    @Test
    fun readAllNotes() {
        val notes = csvNotesRepository.retrieveAll()
        assertThat(notes).hasSize(4)
    }
}
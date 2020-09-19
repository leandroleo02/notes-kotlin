package com.example.notes.infrastructure

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
}
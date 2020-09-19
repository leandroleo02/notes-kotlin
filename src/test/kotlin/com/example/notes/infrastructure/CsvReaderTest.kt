package com.example.notes.infrastructure

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIOException
import org.junit.jupiter.api.Test

class CsvReaderTest {

    @Test
    fun throwsExceptionWhenFileNotExists() {
        val csvReader = CsvReader("notes.csv")
        assertThatIOException().isThrownBy { csvReader.readFile() }
    }

    @Test
    fun readDataFromFile() {
        val fileUrl = this::class.java.classLoader.getResource("notes/notes.csv")
        val csvReader = CsvReader(fileUrl.path)
        assertThat(csvReader.readFile()).hasSize(5)
    }
}
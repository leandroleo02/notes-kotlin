package com.example.notes.infrastructure

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIOException
import org.junit.jupiter.api.Test

class CsvReaderTest {

    @Test
    fun throwsExceptionWhenFileNotExists() {
        val csvReader = CsvReader()
        assertThatIOException().isThrownBy { csvReader.readFile("notes.csv") }
    }

    @Test
    fun readDataFromFile() {
        val fileUrl = this::class.java.classLoader.getResource("notes/notes.csv")
        val csvReader = CsvReader()
        assertThat(csvReader.readFile(fileUrl.path)).hasSize(5)
    }
}
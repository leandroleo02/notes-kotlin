package com.example.notes.infrastructure

import com.example.notes.domain.Note
import com.example.notes.domain.NotesRepository
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.InputStreamReader

class CsvNotesRepository: NotesRepository {

    override fun retrieveAll(): List<Note> {
        return readFile()
    }

    private fun readFile(): List<Note> {
        val inputStream = this::class.java.classLoader.getResourceAsStream(Constants.NOTES_FILE_PATH)
        val fileReader = BufferedReader(InputStreamReader(inputStream))
        val csvReader = CSVReader(fileReader)
        csvReader.skip(1)
        return csvReader.readAll().map { line -> Note(line[0], line[1]) }
    }

    object Constants {
        const val NOTES_FILE_PATH = "notes/notes.csv"
    }
}
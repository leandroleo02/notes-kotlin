package com.example.notes.infrastructure

import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.FileReader

class CsvReader(private val filePath: String) {

    fun readFile(): List<Array<String>> {
        val fileReader = BufferedReader(FileReader(filePath))
        val csvReader = CSVReader(fileReader)
        csvReader.skip(1)
        return csvReader.readAll()
    }
}
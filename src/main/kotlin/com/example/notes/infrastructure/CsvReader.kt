package com.example.notes.infrastructure

import com.opencsv.CSVReader
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class CsvReader {

    fun readFile(filePath: String): List<Array<String>> {
        val fileReader = BufferedReader(FileReader(filePath))
        val csvReader = CSVReader(fileReader)
        csvReader.skip(1)
        return csvReader.readAll()
    }
}
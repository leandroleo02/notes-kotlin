package com.example.notes.domain

class Notes(private val notes: List<Note>) {

    fun findNotesByCategory(category: String): List<Note> {
        return notes.filter { it.category == category }
    }

    fun extractTitles(): List<String> {
        return notes.map { it.title }
    }

    fun <R> convert(transform: (Note) -> R): List<R> {
        return notes.map(transform)
    }
}
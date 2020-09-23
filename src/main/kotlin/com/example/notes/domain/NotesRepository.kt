package com.example.notes.domain

interface NotesRepository {

    fun retrieveAll(): Notes
    fun findById(id: String): Note?
}
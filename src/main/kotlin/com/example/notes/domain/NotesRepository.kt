package com.example.notes.domain

interface NotesRepository {

    fun retrieveAll(): List<Note>
    fun retrieveAllNew(): Notes
    fun findById(id: String): Note?
}
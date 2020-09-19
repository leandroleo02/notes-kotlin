package com.example.notes.domain

interface NotesRepository {

    fun retrieveAll(): List<Note>
}
package com.example.notes.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NotesTest {

    private lateinit var notes: Notes

    @BeforeEach
    fun setUp() {
        notes = Notes(noteFixture())
    }

    @Test
    fun findNotesByCategory() {
        val notes = notes.findNotesByCategory("gold")
        assertThat(notes).hasSize(2)
    }

    @Test
    fun returnEmptyListWhenCategoryNotExists() {
        val notes = notes.findNotesByCategory("yellow")
        assertThat(notes).isEmpty()
    }

    @Test
    fun returnOnlyTitlesFromNotes() {
        val titles = notes.extractTitles()
        assertThat(titles).hasSize(5)
    }

    @Test
    fun convertNotes() {
        val notesConverted = notes.convert { it.id }
        assertThat(notesConverted).contains("1", "2", "3", "4", "5")
    }

    private fun noteFixture(): List<Note> {
        return listOf(
            Note("1", "Learning","green","First Note in Kotlin"),
            Note("2", "Goku","orange","Olá, eu sou o Goku"),
            Note("3", "Tyrion","gold","I drink and I know things"),
            Note("4", "Funny","gold","His legs flail about as if independent from his body!"),
            Note("5", "Cold","white","Winter is coming!"),)
    }
}
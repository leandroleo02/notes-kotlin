package com.example.notes.infrastructure

import com.example.notes.infrastructure.documents.NoteDocument
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class MongoNotesRepositoryTest {

    private lateinit var mongoNotesRepository: MongoNotesRepository
    private lateinit var mockMongoDelegate: MongoRepositoryDelegate

    @BeforeEach
    fun setTup() {
        mockMongoDelegate = Mockito.mock(MongoRepositoryDelegate::class.java)
        mongoNotesRepository = MongoNotesRepository(mockMongoDelegate)
    }

    @Test
    fun readAllNotes() {
        Mockito.`when`(mockMongoDelegate.findAll()).thenReturn(noteFixture())
        val notes = mongoNotesRepository.retrieveAll()
        assertThat(notes).isNotNull
    }

    @Test
    fun findNoteById() {
        Mockito.`when`(mockMongoDelegate.findById("5")).thenReturn(Optional.of(NoteDocument("5", "Cold","white","Winter is coming!")))
        val note = mongoNotesRepository.findById("5")
        assertThat(note).isNotNull
    }

    private fun noteFixture(): List<NoteDocument> {
        return listOf(
                NoteDocument("1", "Learning","green","First Note in Kotlin"),
                NoteDocument("2", "Goku","orange","Olá, eu sou o Goku"),
                NoteDocument("3", "Tyrion","gold","I drink and I know things"),
                NoteDocument("4", "Funny","gold","His legs flail about as if independent from his body!"),
                NoteDocument("5", "Cold","white","Winter is coming!"),)
    }
}
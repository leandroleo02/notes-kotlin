package com.example.notes.infrastructure

import com.example.notes.infrastructure.documents.NoteDocument
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class MongoNotesRepositoryTest {

    private lateinit var mongoNotesRepository: MongoNotesRepository
    private lateinit var mockMongoDelegate: MongoNotesRepository.MongoRepositoryDelegate

    @BeforeEach
    fun setTup() {
        mockMongoDelegate = Mockito.mock(MongoNotesRepository.MongoRepositoryDelegate::class.java)
        mongoNotesRepository = MongoNotesRepository(mockMongoDelegate)
    }

    @Test
    fun readAllNotes() {
        Mockito.`when`(mockMongoDelegate.findAll()).thenReturn(noteFixture())
        val notes = mongoNotesRepository.retrieveAll()
        assertThat(notes).isNotNull
    }

    private fun noteFixture(): List<NoteDocument> {
        return listOf(
                NoteDocument("1", "Learning","green","First Note in Kotlin"),
                NoteDocument("2", "Goku","orange","Olá, eu sou o Goku"),
                NoteDocument("3", "Tyrion","gold","I drink and I know things"),
                NoteDocument("4", "Funny","gold","His legs flail about as if independent from his body!"),
                NoteDocument("5", "Cold","white","Winter is coming!"),)
    }

//    @Test
//    fun findNoteById() {
//        val note = csvNotesRepository.findById("5")
//        assertThat(note).isNotNull
//    }
//
//    @Test
//    fun findNoteWithAllValues() {
//        val note = csvNotesRepository.findById("5")
//        assertThat(note)
//                .usingRecursiveComparison()
//                .isEqualTo(Note("5", "Cold", "white", "Winter is coming!"))
//    }
}
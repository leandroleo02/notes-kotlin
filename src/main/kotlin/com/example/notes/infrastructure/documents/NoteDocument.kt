package com.example.notes.infrastructure.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class NoteDocument(@Id val id: String,
                        val title: String,
                        val category: String,
                        val text: String,)
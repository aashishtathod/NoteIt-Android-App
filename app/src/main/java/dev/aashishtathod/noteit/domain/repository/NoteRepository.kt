package dev.aashishtathod.noteit.domain.repository

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import dev.aashishtathod.noteit.domain.model.Note

interface NoteRepository {
	suspend fun getNoteById(noteId: String): Either<Note>
	
	suspend fun getAllNotes(): Either<NotesResponse>
	
	suspend fun addNote(title: String, note: String): Either<String>
	
	suspend fun updateNote(
		noteId: String,
		title: String,
		note: String
	): Either<String>
	
	suspend fun deleteNote(noteId: String): Either<String>
	
	suspend fun pinNote(noteId: String, isPinned: Boolean): Either<String>
	
	suspend fun deleteAllNotes()
	
}
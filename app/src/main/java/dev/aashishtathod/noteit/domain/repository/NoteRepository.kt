package dev.aashishtathod.noteit.domain.repository

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dto.NoteResponse
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import dev.aashishtathod.noteit.domain.model.Note

interface NoteRepository {
	suspend fun getNoteById(noteId: String): Either<NoteResponse>
	
	suspend fun getAllNotes(): Either<NotesResponse>
	
	suspend fun addNote(title: String, note: String): Either<NoteResponse>
	
	suspend fun updateNote(
		noteId: Int,
		title: String,
		note: String
	): Either<NoteResponse>
	
	suspend fun deleteNote(noteId: String): Either<String>
	
	suspend fun pinNote(noteId: String, isPinned: Boolean): Either<String>
	
//	suspend fun deleteAllNotes()
	
}
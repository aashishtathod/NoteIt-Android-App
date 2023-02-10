package dev.aashishtathod.noteit.data.repositoryImpl

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dataSource.NoteRemoteDataSource
import dev.aashishtathod.noteit.data.source.remote.dto.NoteResponse
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import dev.aashishtathod.noteit.data.source.remote.request.NoteRequest
import dev.aashishtathod.noteit.domain.model.Note
import dev.aashishtathod.noteit.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
	private val noteRemoteDataSource: NoteRemoteDataSource
) : NoteRepository {
	
	override suspend fun getNoteById(noteId: String): Either<Note> {
		TODO("Not yet implemented")
	}
	
	override suspend fun getAllNotes(): Either<NotesResponse> {
		return noteRemoteDataSource.getAllNotes()
	}
	
	override suspend fun addNote(title: String, note: String): Either<NoteResponse> {
		return noteRemoteDataSource.addNote(NoteRequest(title, note))
	}
	
	override suspend fun updateNote(noteId: String, title: String, note: String): Either<String> {
		TODO("Not yet implemented")
	}
	
	override suspend fun deleteNote(noteId: String): Either<String> {
		TODO("Not yet implemented")
	}
	
	override suspend fun pinNote(noteId: String, isPinned: Boolean): Either<String> {
		TODO("Not yet implemented")
	}
	
	override suspend fun deleteAllNotes() {
		TODO("Not yet implemented")
	}
	
}
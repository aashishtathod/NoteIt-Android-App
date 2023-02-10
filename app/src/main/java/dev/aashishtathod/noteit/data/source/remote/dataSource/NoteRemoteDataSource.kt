package dev.aashishtathod.noteit.data.source.remote.dataSource

import dev.aashishtathod.noteit.core.data.BaseRemoteDataSource
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.api.NoteService
import dev.aashishtathod.noteit.data.source.remote.dto.NoteResponse
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import dev.aashishtathod.noteit.data.source.remote.request.NoteRequest
import javax.inject.Inject

class NoteRemoteDataSource @Inject constructor(
	private val apiService: NoteService
) : BaseRemoteDataSource() {
	
	suspend fun getAllNotes(): Either<NotesResponse> = safeApiCall {
		apiService.getAllNotes()
	}
	
	suspend fun addNote(noteRequest: NoteRequest):Either<NoteResponse> = safeApiCall {
		apiService.addNote(noteRequest)
	}
}

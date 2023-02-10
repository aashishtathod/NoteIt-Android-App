package dev.aashishtathod.noteit.data.source.remote.api

import dev.aashishtathod.noteit.data.source.remote.dto.NoteResponse
import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import dev.aashishtathod.noteit.data.source.remote.request.NoteRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteService {
	
	@GET("notes")
	suspend fun getAllNotes(): NotesResponse
	
	@POST("note/new")
	suspend fun addNote(
		@Body request: NoteRequest
	): NoteResponse
}
package dev.aashishtathod.noteit.data.source.remote.api

import dev.aashishtathod.noteit.data.source.remote.dto.NotesResponse
import retrofit2.http.GET

interface NoteService {
	
	@GET("notes")
	suspend fun getAllNotes(): NotesResponse
}
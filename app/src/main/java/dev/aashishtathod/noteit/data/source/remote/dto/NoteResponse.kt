package dev.aashishtathod.noteit.data.source.remote.dto

import dev.aashishtathod.noteit.core.data.BaseResponse

data class NoteResponse(
	override val status: Int,
	override val message: String,
	val noteId: String?
) : BaseResponse

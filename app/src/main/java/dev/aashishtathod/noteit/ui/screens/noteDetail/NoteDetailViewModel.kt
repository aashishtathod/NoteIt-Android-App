package dev.aashishtathod.noteit.ui.screens.noteDetail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.aashishtathod.noteit.core.presentation.BaseViewModel
import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.domain.model.Note
import dev.aashishtathod.noteit.domain.usecase.GetNoteUseCase
import dev.aashishtathod.noteit.domain.usecase.NoteValidationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
	private val noteValidationUseCase: NoteValidationUseCase,
	private val getNoteUseCase: GetNoteUseCase
) : BaseViewModel<NoteDetailState>(NoteDetailState()) {
	
	private lateinit var currentNote: Note
	
	fun setTitle(title: String) {
		setState { state -> state.copy(title = title) }
		validateNote()
	}
	
	fun setNote(note: String) {
		setState { state -> state.copy(note = note) }
		validateNote()
	}
	
	private fun validateNote() {
		viewModelScope.launch {
			val title = currentState.title
			val note = currentState.note
			noteValidationUseCase(title, note).collect {
				setState { state -> state.copy(showSave = it) }
			}
		}
	}
	
	fun loadNote(noteId: String) {
		viewModelScope.launch {
			setState { state -> state.copy(isLoading = true) }
			getNoteUseCase(noteId).collect {
				when (it) {
					is Either.Success -> {
						currentNote = it.data.note!!
						
						setState { state ->
							state.copy(
								isLoading = false,
								title = currentNote.title,
								note = currentNote.note,
								isPinned = currentNote.isPinned
							)
						}
					}
					
					is Either.Error -> setState { state ->
						state.copy(isLoading = false, error = it.message)
					}
					
				}
			}
		}
	}
	
}
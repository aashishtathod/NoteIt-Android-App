package dev.aashishtathod.noteit.ui.screens.notes

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.aashishtathod.noteit.core.utils.ext.collectState
import dev.aashishtathod.noteit.domain.model.Note
import dev.aashishtathod.noteit.ui.components.note.NotesList
import dev.aashishtathod.noteit.ui.components.scaffold.NoteItAppBar
import dev.aashishtathod.noteit.ui.components.scaffold.NoteItScaffold
import dev.aashishtathod.noteit.R
import dev.aashishtathod.noteit.core.utils.NotyPreview

@Composable
fun NotesScreen(
	viewModel: NotesViewModel,
	onNavigateToAddNote: () -> Unit,
	onNavigateToNoteDetail: (String) -> Unit,
	onNavigateToLogin: () -> Unit
) {
	val state by viewModel.collectState()
	
	NotesContent(
		isLoading = state.isLoading,
		notes = state.notes,
	//	isConnectivityAvailable = state.isConnectivityAvailable,
	//	onRefresh = viewModel::syncNotes,
	//	onToggleTheme = { viewModel.setDarkMode(!isInDarkMode) },
	//	onAboutClick = onNavigateToAbout,
		onAddNoteClick = onNavigateToAddNote,
	//	onLogoutClick = { showLogoutConfirmation = true },
		onNavigateToNoteDetail = onNavigateToNoteDetail
	)
	
	val isUserLoggedIn = state.isUserLoggedIn
	
	LaunchedEffect(isUserLoggedIn) {
		if (isUserLoggedIn == false) {
			onNavigateToLogin()
		}
	}
}

@Composable
fun NotesContent(
	isLoading: Boolean,
	notes: List<Note>,
//	isConnectivityAvailable: Boolean?, Todo
	error: String? = null,
//	onRefresh: () -> Unit,      Todo
	onAddNoteClick: () -> Unit,
//	onLogoutClick: () -> Unit,      Todo
	onNavigateToNoteDetail: (String) -> Unit
) {
	NoteItScaffold(
		error = error,
		appBar = {
			NoteItAppBar(
				title = "Notes",
				actions = {
					// LogoutAction(onLogout = onLogoutClick)
				}
			)
		},
		content = {
			Column {
				NotesList(
					notes = notes,
					onClick = { note ->
						onNavigateToNoteDetail(note.noteId.toString())
					}
				)
			}
		},
		
		floatingActionButton = {
			FloatingActionButton(
				onClick = onAddNoteClick,
				backgroundColor = MaterialTheme.colors.primary
			) {
				Icon(
					Icons.Filled.Add,
					"Add",
					tint = Color.White
				)
			}
		}
	)
}

@Preview
@Composable
fun NotesScreenPreview() = NotyPreview {
	NotesContent(
		isLoading = false,
		notes = emptyList(),
		onAddNoteClick = {  },
		onNavigateToNoteDetail ={}
	)
}

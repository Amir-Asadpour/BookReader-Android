package nl.appical.bookreader.presentation.home

import kotlinx.coroutines.flow.Flow
import nl.appical.bookreader.presentation.models.UiBook

sealed class HomeUiState {
    data object Progress : HomeUiState()
    data object TryAgain : HomeUiState()
    data class Content(val books: Flow<List<UiBook>>, val searchQuery: String = "") : HomeUiState()
}
package nl.appical.bookreader.presentation.home

import nl.appical.bookreader.presentation.models.UiBook

sealed class HomeUiState {
    data object Progress : HomeUiState()
    data object TryAgain : HomeUiState()
    data class Content(val books: List<UiBook>) : HomeUiState()
}
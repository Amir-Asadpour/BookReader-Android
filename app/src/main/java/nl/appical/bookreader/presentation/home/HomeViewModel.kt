package nl.appical.bookreader.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.appical.bookreader.domain.usecases.GetAllBooksUseCase
import nl.appical.bookreader.presentation.models.toUi
import nl.appical.bookreader.utils.mapList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllBooksUseCase: GetAllBooksUseCase) :
    ViewModel() {

    var uiState = mutableStateOf<HomeUiState>(HomeUiState.Progress)
        private set

    fun getBooks() {
        if (uiState.value is HomeUiState.Content) return

        uiState.value = HomeUiState.Progress

        viewModelScope.launch {
            uiState.value = try {
                HomeUiState.Content(books = getAllBooksUseCase().mapList { it.toUi() })
            } catch (e: Exception) {
                HomeUiState.TryAgain
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        if (uiState.value !is HomeUiState.Content) return
        // TODO: Searching should be implemented
    }
}
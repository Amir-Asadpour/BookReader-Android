package nl.appical.bookreader.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.appical.bookreader.domain.usecases.GetAllBooksUseCase
import nl.appical.bookreader.presentation.models.UiBook
import nl.appical.bookreader.presentation.models.toUi
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllBooksUseCase: GetAllBooksUseCase) :
    ViewModel() {

    private var allBooks: List<UiBook> = arrayListOf()

    var uiState = mutableStateOf<HomeUiState>(HomeUiState.Progress)
        private set

    fun getBooks() {
        if (uiState.value is HomeUiState.Content) return

        uiState.value = HomeUiState.Progress

        viewModelScope.launch {
            uiState.value = try {
                allBooks = getAllBooksUseCase().map { it.toUi() }
                HomeUiState.Content(books = allBooks)
            } catch (e: Exception) {
                HomeUiState.TryAgain
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        if (uiState.value !is HomeUiState.Content) return

        uiState.value = HomeUiState.Content(
            books = if (query.isEmpty())
                allBooks
            else
                allBooks.filter {
                    it.title.lowercase().contains(query.lowercase()) ||
                            it.author.lowercase().contains(query.lowercase())
                },
            searchQuery = query
        )
    }
}
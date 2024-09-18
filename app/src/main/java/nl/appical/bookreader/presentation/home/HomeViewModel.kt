package nl.appical.bookreader.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.appical.bookreader.domain.usecases.GetAllBooksUseCase
import nl.appical.bookreader.presentation.models.toUi
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
                HomeUiState.Content(getAllBooksUseCase().map { it.toUi() })
            } catch (e: Exception) {
                HomeUiState.TryAgain
            }
        }
    }
}
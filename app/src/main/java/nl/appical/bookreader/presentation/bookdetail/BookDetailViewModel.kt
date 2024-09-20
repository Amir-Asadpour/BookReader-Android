package nl.appical.bookreader.presentation.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nl.appical.bookreader.domain.models.Book
import nl.appical.bookreader.domain.usecases.GetBookUseCase
import nl.appical.bookreader.domain.usecases.ToggleBookFavoriteUseCase
import nl.appical.bookreader.presentation.models.toUi

@HiltViewModel(assistedFactory = BookDetailViewModel.Factory::class)
class BookDetailViewModel @AssistedInject constructor(
    @Assisted private val bookId: String,
    getBookUseCase: GetBookUseCase,
    private val toggleBookFavoriteUseCase: ToggleBookFavoriteUseCase
) :
    ViewModel() {

    private var latestBook: Book? = null

    val book = getBookUseCase(bookId).onEach { latestBook = it }.map { it.toUi() }

    fun toggleBookFavorite() {
        latestBook?.let {
            viewModelScope.launch { toggleBookFavoriteUseCase.invoke(it) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(bookId: String): BookDetailViewModel
    }
}
package nl.appical.bookreader.presentation.bookdetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.appical.bookreader.domain.usecases.GetBookUseCase
import nl.appical.bookreader.presentation.models.blankUiBook
import nl.appical.bookreader.presentation.models.toUi
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(private val getBookUseCase: GetBookUseCase) :
    ViewModel() {
    var book = mutableStateOf(blankUiBook)
        private set

    fun getBook(bookId: String) {
        viewModelScope.launch {
            book.value = getBookUseCase(bookId).toUi()
        }
    }
}
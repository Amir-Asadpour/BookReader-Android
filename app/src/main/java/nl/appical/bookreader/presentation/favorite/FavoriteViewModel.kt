package nl.appical.bookreader.presentation.favorite

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.appical.bookreader.domain.usecases.GetFavoriteBooksUseCase
import nl.appical.bookreader.presentation.models.toUi
import nl.appical.bookreader.application.common.mapList
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(getFavoriteBooksUseCase: GetFavoriteBooksUseCase) :
    ViewModel() {
    val books = getFavoriteBooksUseCase().mapList { it.toUi() }
}
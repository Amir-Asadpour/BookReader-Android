package nl.appical.bookreader.domain.usecases

import nl.appical.bookreader.domain.models.Book
import nl.appical.bookreader.domain.repositories.BooksRepository
import javax.inject.Inject

class ToggleBookFavoriteUseCase @Inject constructor(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(book: Book) {
        booksRepository.updateBook(book.copy(isFavorite = !book.isFavorite))
    }
}
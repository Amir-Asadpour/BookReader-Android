package nl.appical.bookreader.domain.usecases

import nl.appical.bookreader.domain.repositories.BooksRepository
import javax.inject.Inject

class GetBookUseCase @Inject constructor(private val booksRepository: BooksRepository) {
    operator fun invoke(bookId: String) = booksRepository.getBook(bookId)
}
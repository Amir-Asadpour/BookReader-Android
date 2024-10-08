package nl.appical.bookreader.domain.usecases

import nl.appical.bookreader.domain.repositories.BooksRepository
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(private val booksRepository: BooksRepository) {
    suspend operator fun invoke() = booksRepository.getBooks()
}
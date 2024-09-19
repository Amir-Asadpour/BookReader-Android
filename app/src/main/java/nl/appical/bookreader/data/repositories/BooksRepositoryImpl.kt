package nl.appical.bookreader.data.repositories

import nl.appical.bookreader.data.models.toDomain
import nl.appical.bookreader.data.network.RemoteService
import nl.appical.bookreader.domain.models.Book
import nl.appical.bookreader.domain.repositories.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(private val remoteService: RemoteService) :
    BooksRepository {

    private var cachedBooks: List<Book> = emptyList()

    override suspend fun getBooks() =
        remoteService.getBooks().map { it.toDomain(false) }.also { cachedBooks = it }

    override suspend fun searchBooks(query: String): List<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun getBook(bookId: String) =
        requireNotNull(cachedBooks.find { it.id == bookId })
}
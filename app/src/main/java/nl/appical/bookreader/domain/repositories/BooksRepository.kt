package nl.appical.bookreader.domain.repositories

import kotlinx.coroutines.flow.Flow
import nl.appical.bookreader.domain.models.Book

interface BooksRepository {
    suspend fun getBooks(): Flow<List<Book>>

    fun searchBooks(query: String): Flow<List<Book>>

    fun getBook(bookId: String): Flow<Book>

    suspend fun updateBook(book: Book)

    fun getFavoriteBooks(): Flow<List<Book>>
}
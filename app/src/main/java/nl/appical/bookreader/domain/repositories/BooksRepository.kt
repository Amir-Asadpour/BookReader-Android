package nl.appical.bookreader.domain.repositories

import kotlinx.coroutines.flow.Flow
import nl.appical.bookreader.domain.models.Book

interface BooksRepository {
    suspend fun getBooks(): List<Book>

    suspend fun searchBooks(query: String): List<Book>

    fun getBook(bookId: String): Flow<Book>

    suspend fun updateBook(book: Book)

    fun getFavoriteBooks(): Flow<List<Book>>
}
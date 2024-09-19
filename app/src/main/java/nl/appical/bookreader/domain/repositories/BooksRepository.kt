package nl.appical.bookreader.domain.repositories

import nl.appical.bookreader.domain.models.Book

interface BooksRepository {
    suspend fun getBooks(): List<Book>

    suspend fun searchBooks(query: String): List<Book>

    suspend fun getBook(bookId: String): Book
}
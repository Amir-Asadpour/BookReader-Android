package nl.appical.bookreader.domain.repositories

import nl.appical.bookreader.domain.models.Book

interface BooksRepository {
    suspend fun getBooks(): List<Book>
}
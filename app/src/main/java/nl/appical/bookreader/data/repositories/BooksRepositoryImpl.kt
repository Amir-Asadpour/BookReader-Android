package nl.appical.bookreader.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.appical.bookreader.data.database.dao.BookDao
import nl.appical.bookreader.data.models.toDb
import nl.appical.bookreader.data.models.toDomain
import nl.appical.bookreader.data.network.RemoteService
import nl.appical.bookreader.domain.models.Book
import nl.appical.bookreader.domain.repositories.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val remoteService: RemoteService,
    private val bookDao: BookDao
) :
    BooksRepository {

    override suspend fun getBooks(): Flow<List<Book>> {
        val favBooksId = bookDao.getAllFavoriteBooksId()
        remoteService.getBooks().map { it.toDomain(favBooksId.contains(it.id)) }.also {
            val dbBooks = it.map { book -> book.toDb() }
            bookDao.insertAll(*dbBooks.toTypedArray())
        }
        return bookDao.getAllBooks().map { it.map { dbBook -> dbBook.toDomain() } }
    }

    override fun searchBooks(query: String): Flow<List<Book>> {
        TODO("Not yet implemented")
    }

    override fun getBook(bookId: String): Flow<Book> =
        bookDao.getBookById(bookId).map { it.toDomain() }

    override suspend fun updateBook(book: Book) {
        bookDao.update(book.toDb())
    }

    override fun getFavoriteBooks() =
        bookDao.getAllFavoriteBooks().map { it.map { dbBook -> dbBook.toDomain() } }
}
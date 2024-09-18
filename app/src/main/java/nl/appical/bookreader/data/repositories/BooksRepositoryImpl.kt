package nl.appical.bookreader.data.repositories

import nl.appical.bookreader.data.models.toDomain
import nl.appical.bookreader.data.network.RemoteService
import nl.appical.bookreader.domain.repositories.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(private val remoteService: RemoteService) :
    BooksRepository {
    override suspend fun getBooks() = remoteService.getBooks().map { it.toDomain(false) }
}
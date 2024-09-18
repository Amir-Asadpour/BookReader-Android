package nl.appical.bookreader.data.network

import nl.appical.bookreader.data.models.RemoteBook
import retrofit2.http.GET

interface RemoteService {
    @GET("books")
    suspend fun getBooks(): List<RemoteBook>
}
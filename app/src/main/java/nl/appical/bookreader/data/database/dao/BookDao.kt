package nl.appical.bookreader.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import nl.appical.bookreader.data.models.DbBook

@Dao
interface BookDao {
    @Query("SELECT * FROM books WHERE (:query = '' OR title LIKE '%' || :query || '%' OR author LIKE '%' || :query || '%')")
    suspend fun getAllBooks(query: String = ""): List<DbBook>

    @Query("SELECT * FROM books WHERE isFavorite=1")
    fun getAllFavoriteBooks(): Flow<List<DbBook>>

    @Query("SELECT id FROM books WHERE isFavorite=1")
    suspend fun getAllFavoriteBooksId(): List<String>

    @Query("SELECT * FROM books WHERE id=:id")
    fun getBookById(id: String): Flow<DbBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: DbBook)

    @Update
    suspend fun update(book: DbBook)
}
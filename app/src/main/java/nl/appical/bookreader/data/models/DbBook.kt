package nl.appical.bookreader.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import nl.appical.bookreader.domain.models.Book

@Entity(tableName = "books")
data class DbBook(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val image: String,
    val description: String,
    val isFavorite: Boolean
)

fun Book.toDb() = DbBook(id, title, author, image, description, isFavorite)

fun DbBook.toDomain() = Book(id, title, author, image, description, isFavorite)
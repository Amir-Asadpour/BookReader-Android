package nl.appical.bookreader.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.appical.bookreader.domain.models.Book

@Serializable
data class RemoteBook(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("author") val author: String,
    @SerialName("image") val image: String,
    @SerialName("description") val description: String
)

fun RemoteBook.toDomain(isFavorite: Boolean) =
    Book(id, title, author, image, description, isFavorite)

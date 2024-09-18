package nl.appical.bookreader.domain.models

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val image: String,
    val description: String,
    val isFavorite: Boolean
)

package nl.appical.bookreader.presentation.models

import nl.appical.bookreader.domain.models.Book

data class UiBook(
    val id: String,
    val title: String,
    val author: String,
    val image: String,
    val description: String,
    val isFavorite: Boolean
)

val blankUiBook
    get() = UiBook("", "", "", "", "", false)

fun Book.toUi() = UiBook(id, title, author, image, description, isFavorite)

fun UiBook.toDomain() = Book(id, title, author, image, description, isFavorite)

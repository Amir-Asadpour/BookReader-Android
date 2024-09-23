package nl.appical.bookreader.presentation

import nl.appical.bookreader.domain.models.Book
import java.util.UUID

object TestModels {
    val sampleBook: Book
        get() = Book(
            id = UUID.randomUUID().toString(),
            title = "Title",
            author = "Author",
            image = "image",
            description = "description",
            isFavorite = false
        )
}
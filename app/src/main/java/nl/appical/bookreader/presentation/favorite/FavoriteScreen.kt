package nl.appical.bookreader.presentation.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.appical.bookreader.R
import nl.appical.bookreader.presentation.home.BooksListView
import nl.appical.bookreader.presentation.models.UiBook

@Composable
fun FavoriteScreen(books: List<UiBook>, onBookClicked: (UiBook) -> Unit) {
    if (books.isEmpty()) {
        Text(
            text = stringResource(R.string.msg_no_fav_books),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    } else {
        BooksListView(
            modifier = Modifier.fillMaxSize(),
            books = books,
            onBookClicked = onBookClicked
        )
    }
}

@Preview
@Composable
private fun FavoriteScreenPreview() {
    FavoriteScreen(books = emptyList()) {}
}
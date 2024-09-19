package nl.appical.bookreader.presentation.bookdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import nl.appical.bookreader.R
import nl.appical.bookreader.presentation.models.UiBook
import nl.appical.bookreader.presentation.models.UiMock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(book: UiBook, onFavClicked: () -> Unit, onBackClicked: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.book_detail)) },
                navigationIcon = {
                    IconButton(onBackClicked) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.size(24.dp))

            Card(
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 20.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(150.dp)
                        .aspectRatio(2.1f / 3f),
                    model = book.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = book.title
                )
            }

            Spacer(Modifier.size(24.dp))
            Text(book.title, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.size(8.dp))
            Text(
                book.author,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.78f)
            )
            Spacer(Modifier.size(16.dp))
            Card(shape = MaterialTheme.shapes.large) {
                Text(
                    book.description,
                    modifier = Modifier.padding(24.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun BookDetailScreenPreview() {
    BookDetailScreen(book = UiMock.books[0], onFavClicked = {}) { }
}
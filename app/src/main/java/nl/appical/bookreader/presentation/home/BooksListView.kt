package nl.appical.bookreader.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import nl.appical.bookreader.presentation.designsystem.AppTheme
import nl.appical.bookreader.presentation.models.UiBook
import nl.appical.bookreader.presentation.models.UiMock

@Composable
fun BooksListView(modifier: Modifier = Modifier, books: List<UiBook>) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(books) {
            Card(
                onClick = {},
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                    Card(elevation = CardDefaults.elevatedCardElevation()) {
                        AsyncImage(
                            modifier = Modifier
                                .width(100.dp)
                                .aspectRatio(2.1f / 3f),
                            model = it.image,
                            contentScale = ContentScale.Crop,
                            contentDescription = it.title
                        )
                    }

                    Spacer(Modifier.size(16.dp))

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(it.title, style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.size(16.dp))
                        Text(it.author, style = MaterialTheme.typography.bodyMedium)
                    }

                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
private fun BooksListViewPreview() {
    AppTheme {
        BooksListView(books = UiMock.books)
    }
}
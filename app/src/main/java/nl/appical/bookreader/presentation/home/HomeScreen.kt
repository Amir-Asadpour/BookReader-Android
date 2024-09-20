package nl.appical.bookreader.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import nl.appical.bookreader.R
import nl.appical.bookreader.presentation.designsystem.components.TryAgainView
import nl.appical.bookreader.presentation.models.UiBook

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onLoadContent: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onBookClicked: (UiBook) -> Unit
) {
    LaunchedEffect(Unit) { onLoadContent() }

    Column {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is HomeUiState.Content -> HomeContent(uiState, onSearchQueryChanged, onBookClicked)
                HomeUiState.Progress -> CircularProgressIndicator()
                HomeUiState.TryAgain -> TryAgainView { onLoadContent() }
            }
        }
    }
}

@Composable
private fun HomeContent(
    uiState: HomeUiState.Content,
    onSearchQueryChanged: (String) -> Unit,
    onBookClicked: (UiBook) -> Unit
) {
    val books by uiState.books.collectAsStateWithLifecycle(emptyList())

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            value = uiState.searchQuery,
            onValueChange = onSearchQueryChanged,
            label = { Text(stringResource(R.string.hint_search)) },
            trailingIcon = {
                if (uiState.searchQuery.isNotEmpty())
                    IconButton(
                        onClick = { onSearchQueryChanged("") },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(Icons.Rounded.Clear, contentDescription = "Clear")
                    }
            },
            shape = RoundedCornerShape(16.dp)
        )
        if (books.isEmpty()) {
            Text(
                text = stringResource(R.string.msg_no_books_available),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            BooksListView(
                modifier = Modifier.weight(1f),
                books = books,
                onBookClicked = onBookClicked
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState.Content(MutableStateFlow(emptyList())),
        onLoadContent = { },
        onSearchQueryChanged = {},
        onBookClicked = {}
    )
}
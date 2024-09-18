package nl.appical.bookreader.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.appical.bookreader.presentation.designsystem.components.TryAgainView

@Composable
fun HomeScreen(uiState: HomeUiState, onLoadContent: () -> Unit) {
    LaunchedEffect(Unit) { onLoadContent() }

    Column {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is HomeUiState.Content -> BooksListView(books = uiState.books)
                HomeUiState.Progress -> CircularProgressIndicator()
                HomeUiState.TryAgain -> TryAgainView { onLoadContent() }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(uiState = HomeUiState.Progress, onLoadContent = { })
}
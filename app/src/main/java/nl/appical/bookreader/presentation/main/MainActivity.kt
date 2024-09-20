package nl.appical.bookreader.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import nl.appical.bookreader.presentation.bookdetail.BookDetailScreen
import nl.appical.bookreader.presentation.bookdetail.BookDetailViewModel
import nl.appical.bookreader.presentation.designsystem.AppTheme
import nl.appical.bookreader.presentation.models.blankUiBook

@Serializable
data object Main

@Serializable
data class BookDetail(val bookId: String)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Main) {
                    composable<Main> {
                        MainScreen {
                            navController.navigate(BookDetail(it.id))
                        }
                    }

                    composable<BookDetail> { backStackEntry ->
                        val bookId = (backStackEntry.toRoute() as BookDetail).bookId
                        val viewModel =
                            hiltViewModel<BookDetailViewModel, BookDetailViewModel.Factory> { factory ->
                                factory.create(bookId)
                            }

                        val book by viewModel.book.collectAsStateWithLifecycle(blankUiBook)

                        BookDetailScreen(
                            book = book,
                            onFavClicked = viewModel::toggleBookFavorite,
                            onBackClicked = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
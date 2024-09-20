package nl.appical.bookreader.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.IntOffset
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

                NavHost(
                    navController = navController,
                    startDestination = Main,
                    enterTransition = {
                        slideIn(
                            animationSpec = spring(stiffness = 200f),
                            initialOffset = { IntOffset(it.width, 0) }
                        )
                    },
                    exitTransition = {
                        slideOut(
                            animationSpec = spring(stiffness = 200f),
                            targetOffset = { IntOffset(-it.width, 0) })
                    },
                    popEnterTransition = {
                        slideIn(
                            animationSpec = spring(stiffness = 200f),
                            initialOffset = { IntOffset(-it.width, 0) })
                    },
                    popExitTransition = {
                        slideOut(
                            animationSpec = spring(stiffness = 200f),
                            targetOffset = { IntOffset(it.width, 0) })
                    }
                ) {
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
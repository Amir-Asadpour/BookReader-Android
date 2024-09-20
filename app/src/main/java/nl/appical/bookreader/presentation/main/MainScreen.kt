package nl.appical.bookreader.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import nl.appical.bookreader.presentation.designsystem.AppTheme
import nl.appical.bookreader.presentation.favorite.FavoriteScreen
import nl.appical.bookreader.presentation.favorite.FavoriteViewModel
import nl.appical.bookreader.presentation.home.HomeScreen
import nl.appical.bookreader.presentation.home.HomeViewModel
import nl.appical.bookreader.presentation.main.components.BottomNavigationBar
import nl.appical.bookreader.presentation.main.models.BottomNavigationTab
import nl.appical.bookreader.presentation.models.UiBook

@Serializable
data object Home

@Serializable
data object Favorites

@Composable
fun MainScreen(onBookClicked: (UiBook) -> Unit) {
    val navController = rememberNavController()

    var selectedTab by rememberSaveable { mutableStateOf(BottomNavigationTab.Home) }

    AppTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    selectedTab = selectedTab,
                    onTabClicked = {
                        selectedTab = it
                        val destination = when (it) {
                            BottomNavigationTab.Home -> Home
                            BottomNavigationTab.Favorite -> Favorites
                        }
                        navController.navigate(destination) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = Home,
                modifier = Modifier.padding(contentPadding)
            ) {
                composable<Home> {
                    val viewModel = hiltViewModel<HomeViewModel>()
                    val uiState by viewModel.uiState

                    HomeScreen(
                        uiState = uiState,
                        onLoadContent = viewModel::getBooks,
                        onSearchQueryChanged = viewModel::onSearchQueryChanged,
                        onBookClicked = onBookClicked
                    )
                }

                composable<Favorites> {
                    val viewModel = hiltViewModel<FavoriteViewModel>()
                    FavoriteScreen(
                        books = viewModel.books.collectAsStateWithLifecycle(emptyList()).value,
                        onBookClicked = onBookClicked
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen {

    }
}
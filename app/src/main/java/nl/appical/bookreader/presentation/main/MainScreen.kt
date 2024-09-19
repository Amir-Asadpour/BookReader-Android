package nl.appical.bookreader.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import nl.appical.bookreader.presentation.designsystem.AppTheme
import nl.appical.bookreader.presentation.home.HomeScreen
import nl.appical.bookreader.presentation.home.HomeViewModel
import nl.appical.bookreader.presentation.main.components.BottomNavigationBar
import nl.appical.bookreader.presentation.main.models.BottomNavigationTab

@Serializable
data object Home

@Serializable
data object Favorites

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    var selectedTab by remember { mutableStateOf(BottomNavigationTab.Home) }

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
                        onSearchQueryChanged = viewModel::onSearchQueryChanged
                    )
                }

                composable<Favorites> { Text("Fav Screen") }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
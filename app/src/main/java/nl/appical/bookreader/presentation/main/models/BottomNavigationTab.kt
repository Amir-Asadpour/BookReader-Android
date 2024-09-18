package nl.appical.bookreader.presentation.main.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import nl.appical.bookreader.R

enum class BottomNavigationTab(@StringRes val title: Int, val icon: ImageVector) {
    Home(R.string.home, Icons.Rounded.Home), Favorite(R.string.favorites, Icons.Rounded.Favorite)
}
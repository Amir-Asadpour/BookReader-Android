package nl.appical.bookreader.presentation.main.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import nl.appical.bookreader.presentation.main.models.BottomNavigationTab

@Composable
fun BottomNavigationBar(
    selectedTab: BottomNavigationTab,
    onTabClicked: (BottomNavigationTab) -> Unit
) {
    NavigationBar {
        BottomNavigationTab.entries.forEach {
            NavigationBarItem(
                icon = { Icon(it.icon, contentDescription = stringResource(it.title)) },
                label = { Text(stringResource(it.title)) },
                selected = selectedTab == it,
                onClick = { onTabClicked(it) }
            )
        }
    }
}
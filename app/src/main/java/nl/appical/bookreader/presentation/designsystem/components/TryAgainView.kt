package nl.appical.bookreader.presentation.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nl.appical.bookreader.R

@Composable
fun TryAgainView(modifier: Modifier = Modifier, onTryAgainClicked: () -> Unit) {
    Column {
        Text(stringResource(R.string.error_general))
        Button(onClick = onTryAgainClicked) {
            Text(stringResource(R.string.try_again))
        }
    }
}

@Preview
@Composable
private fun TryAgainPreview() {
    TryAgainView(onTryAgainClicked = {})
}
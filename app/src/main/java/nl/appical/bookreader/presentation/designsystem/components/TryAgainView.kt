package nl.appical.bookreader.presentation.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.appical.bookreader.R

@Composable
fun TryAgainView(modifier: Modifier = Modifier, onTryAgainClicked: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(R.string.error_general))
        Spacer(Modifier.size(16.dp))
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
package co.k2.newsbits.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

class LoadingPreviewProvider : PreviewParameterProvider<String> {
    override val values = listOf("Newsly").asSequence()
}

@Composable
@Preview
fun LoadingUi(@PreviewParameter(LoadingPreviewProvider::class) title: String) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = title, style = NewslyTypography.h3)
            LinearProgressIndicator(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(0.6F)
                    .height(1.dp)
            )
        }
    }
}

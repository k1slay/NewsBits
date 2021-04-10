package co.k2.newsbits.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.k2.newsbits.R


@Composable
fun ErrorUi(message: String, clickListener: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "(×﹏×)", style = NewslyTypography.h2)
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = message, style = NewslyTypography.body2, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(24.dp))
            RetryButton(clickListener = clickListener)
        }
    }
}

@Composable
fun RetryButton(
    clickListener: () -> Unit,
    text: String = LocalContext.current.getString(R.string.retry),
    modifier: Modifier = Modifier.padding(16.dp, 2.dp)
) {
    Button(
        onClick = { clickListener.invoke() },
        shape = RoundedCornerShape(50),
        modifier = modifier,
        content = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_reload),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                    modifier = Modifier.size(18.dp),
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(text)
            }
        }
    )
}
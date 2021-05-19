package co.k2.newsbits.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import co.k2.newsbits.R
import co.k2.newsbits.data.models.Article
import co.k2.newsbits.data.models.Source

private val dummyArticle = Article(
    1, Source(null, "ABC"), "ABC.com", "Title", "Description",
    "https://abc.xyz", "https://abc.xyz/abc.jpg", null, "Blah.."
)

class NewsItemPreviewProvider : PreviewParameterProvider<Article> {
    override val values: Sequence<Article>
        get() = listOf(dummyArticle).asSequence()
}

@Composable
fun HeadlinesList(
    articles: List<Article>,
    offline: Boolean,
    clickListener: (article: Article) -> Unit,
    retryClickListener: () -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        Box {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(articles) { index, article ->
                    Headline(article = article, index, clickListener)
                }
            }
            if (offline) {
                RetryButton(
                    clickListener = retryClickListener,
                    modifier = Modifier
                        .padding(16.dp, 24.dp)
                        .align(Alignment.TopCenter),
                    text = LocalContext.current.getString(R.string.offline)
                )
            }
        }
    }
}

@Composable
fun Headline(
    article: Article,
    position: Int,
    onClick: (article: Article) -> Unit
) {
    Column(modifier = Modifier
        .clickable {
            onClick.invoke(article)
        }
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
    ) {
        if (position != 0) {
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        val imageOnSide = position > 0 && article.urlToImage != null
        if (imageOnSide.not()) {
            article.urlToImage?.let { RemoteImage(url = it, aspectRatio = 1.7F) }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Row {
            val width = if (imageOnSide) 0.7F else 1.0F
            Column(Modifier.fillMaxWidth(width)) {
                Text(
                    article.title,
                    style = NewslyTypography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                article.description?.let {
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        article.title,
                        style = NewslyTypography.subtitle1,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                val miniWidth = if (imageOnSide) 1.0F else 0.7F
                Row(modifier = Modifier.fillMaxWidth(miniWidth)) {
                    CaptionText(text = article.author)
                    CaptionText(article.getPublishedSince(LocalContext.current))
                }
            }
            if (imageOnSide) {
                Spacer(modifier = Modifier.width(8.dp))
                article.urlToImage?.let { RemoteImage(url = it, aspectRatio = 1.0F) }
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
fun CaptionText(text: String?) {
    Text(
        text = text ?: LocalContext.current.getString(R.string.not_available),
        modifier = Modifier.fillMaxWidth(0.5F),
        style = NewslyTypography.caption,
        color = MaterialTheme.colors.onSecondary
    )
}

package co.k2.newsbits.headlines

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import co.k2.newsbits.App
import co.k2.newsbits.R
import co.k2.newsbits.common.countryIsoCode
import co.k2.newsbits.ui.*
import javax.inject.Inject

class HeadlinesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HeadlineViewModel> { viewModelFactory }

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = applicationContext as App
        app.appComponent.headlinesComponent().create().inject(this)
        viewModel.fetchNewArticles(countryIsoCode)
        setContent {
            InitUi()
        }
    }

    @Composable
    fun InitUi() {
        NewslyTheme {
            ColorStatusBar(window = window)
            when {
                viewModel.headlines.value != null -> {
                    HeadlinesList(
                        articles = viewModel.headlines.value!!,
                        offline = viewModel.error.value.first,
                        clickListener = { article ->
                            navigator.navigateToWebPage(this@HeadlinesActivity, article.url)
                        },
                        retryClickListener = retryClickListener
                    )
                }
                viewModel.error.value.first -> {
                    ErrorUi(
                        viewModel.error.value.second ?: getString(R.string.error_headline_service),
                        retryClickListener
                    )
                }
                else -> {
                    LoadingUi(getString(R.string.app_name))
                }
            }
        }
    }

    private val retryClickListener: () -> Unit = { viewModel.fetchNewArticles(countryIsoCode) }

}

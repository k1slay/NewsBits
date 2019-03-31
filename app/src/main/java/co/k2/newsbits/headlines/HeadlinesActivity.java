package co.k2.newsbits.headlines;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.k2.newsbits.R;
import co.k2.newsbits.common.Utils;
import co.k2.newsbits.data.models.Article;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class HeadlinesActivity extends DaggerAppCompatActivity implements HeadlineClickListener {

    @Inject
    HeadlineViewModelFactory viewModelFactory;
    HeadlineViewModel viewModel;
    private CompositeDisposable disposables = new CompositeDisposable();

    @BindView(R.id.headlines_list)
    RecyclerView headlinesList;

    @BindView(R.id.loading)
    View loading;

    @BindView(R.id.error)
    View error;

    @BindView(R.id.error_text)
    TextView errorText;

    @BindView(R.id.offline_retry_chip)
    Chip retryButton;

    public HeadlinesAdapter adapter;
    private long lastUpdateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HeadlineViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Fetch news if last refresh was more that 10 minutes back
        if ((System.currentTimeMillis() - lastUpdateTime) > TimeUnit.MINUTES.toMillis(10))
            fetchNews();
        if (adapter == null) {
            // Get news from cache if list not set-up yet
            disposables.add(viewModel.fetchCachedArticles()
                    .subscribe(articles -> {
                        if (!articles.isEmpty())
                            setupList(articles);
                    }, Throwable::printStackTrace)
            );
        }
    }

    private void fetchNews() {
        if (adapter == null || adapter.getItemCount() == 0)
            loading.setVisibility(View.VISIBLE);
        error.setVisibility(View.INVISIBLE);
        retryButton.setVisibility(View.INVISIBLE);
        String country = getResources().getConfiguration().locale.getCountry();
        disposables.add(viewModel.fetchNewArticles(country)
                .subscribe(articles -> {
                    lastUpdateTime = System.currentTimeMillis();
                    setupList(articles);
                    disposables.add(viewModel.updateCache(articles));
                }, this::showError)
        );
    }

    public void showError(Throwable throwable) {
        loading.setVisibility(View.INVISIBLE);
        boolean hasInternet = Utils.isInternetConnected(HeadlinesActivity.this);
        String exception = hasInternet ? throwable.getMessage() : getString(R.string.error_no_internet);
        String error = String.format("%1$s: %2$s", getString(R.string.error_headline_service), exception);
        if (adapter != null && adapter.getItemCount() > 0) {
            this.error.setVisibility(View.INVISIBLE);
            retryButton.setVisibility(View.VISIBLE);
            Snackbar.make(headlinesList, error, Snackbar.LENGTH_LONG).show();
        } else {
            retryButton.setVisibility(View.INVISIBLE);
            this.error.setVisibility(View.VISIBLE);
            errorText.setText(error);
        }
    }

    public void setupList(List<Article> articles) {
        retryButton.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);
        if (adapter == null) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                    HeadlinesActivity.this, DividerItemDecoration.VERTICAL
            );
            adapter = new HeadlinesAdapter(articles, this);
            headlinesList.setLayoutManager(new LinearLayoutManager(HeadlinesActivity.this));
            headlinesList.addItemDecoration(dividerItemDecoration);
            headlinesList.setAdapter(adapter);
        } else {
            adapter.update(articles);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposables.clear();
    }

    @Override
    public void onClick(int position, Article article) {
        viewModel.openNewsArticle(this, article);
    }

    @OnClick({R.id.offline_retry_chip, R.id.error_retry_chip})
    void retry(View v) {
        fetchNews();
    }

}

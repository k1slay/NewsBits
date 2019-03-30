package co.k2.newsbits.headlines;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
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

    private HeadlinesAdapter adapter;

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
        if (adapter == null || adapter.getItemCount() == 0)
            loading.setVisibility(View.VISIBLE);
        error.setVisibility(View.INVISIBLE);
        disposables.add(viewModel.fetchNewArticles("in")
                .subscribe(articles -> {
                    setupList(articles);
                    disposables.add(viewModel.updateCache(articles));
                }, this::showError)
        );
        disposables.add(viewModel.fetchCachedArticles()
                .subscribe(articles -> {
                    if (!articles.isEmpty())
                        setupList(articles);
                }, Throwable::printStackTrace)
        );
    }

    private void showError(Throwable throwable) {
        loading.setVisibility(View.INVISIBLE);
        boolean hasInternet = Utils.isInternetConnected(HeadlinesActivity.this);
        String exception = hasInternet ? throwable.getMessage() : getString(R.string.error_no_internet);
        String error = String.format("%1$s: %2$s", getString(R.string.error_headline_service), exception);
        if (adapter != null && adapter.getItemCount() > 0)
            Snackbar.make(headlinesList, error, Snackbar.LENGTH_LONG).show();
        else {
            this.error.setVisibility(View.VISIBLE);
            errorText.setText(error);
        }
    }

    private void setupList(List<Article> articles) {
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

}

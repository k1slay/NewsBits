package co.k2.newsbits.headlines;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import co.k2.newsbits.R;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class HeadlinesActivity extends DaggerAppCompatActivity {

    @Inject
    HeadlineViewModelFactory viewModelFactory;
    HeadlineViewModel viewModel;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HeadlineViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        disposables.add(viewModel.fetchNewArticles("in")
                .subscribe(articles -> {
                    disposables.add(viewModel.updateCache(articles));
                    articles.size();
                }, throwable -> {
                    throwable.printStackTrace();
                })
        );
        disposables.add(viewModel.fetchCachedArticles()
                .subscribe(articles -> {
                    articles.size();
                }, throwable -> {
                    throwable.printStackTrace();
                })
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposables.clear();
    }
}

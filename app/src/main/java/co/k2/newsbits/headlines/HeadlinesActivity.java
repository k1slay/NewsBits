package co.k2.newsbits.headlines;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import co.k2.newsbits.R;
import dagger.android.support.DaggerAppCompatActivity;

public class HeadlinesActivity extends DaggerAppCompatActivity {

    @Inject
    HeadlineViewModelFactory viewModelFactory;
    HeadlineViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HeadlineViewModel.class);
    }

}

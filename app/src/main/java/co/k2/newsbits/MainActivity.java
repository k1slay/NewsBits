package co.k2.newsbits;

import android.os.Bundle;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import co.k2.newsbits.common.Constants;
import co.k2.newsbits.data.models.ApiResponse;
import co.k2.newsbits.data.source.remote.NewsApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

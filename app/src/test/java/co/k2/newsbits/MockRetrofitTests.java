package co.k2.newsbits;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import co.k2.newsbits.common.Constants;
import co.k2.newsbits.data.models.ApiResponse;
import co.k2.newsbits.data.source.remote.NewsApiService;
import io.reactivex.subscribers.TestSubscriber;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class MockRetrofitTests {

    private BehaviorDelegate<NewsApiService> delegate;

    @Before
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NewsApi.BASE_URL)
                .build();
        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
        delegate = mockRetrofit.create(NewsApiService.class);
    }

    @Test
    public void testSuccessResponse() throws IOException {
        TestSubscriber<ApiResponse> subscriber = new TestSubscriber<>();

        SuccessMockNewsService successMockNewsService = new SuccessMockNewsService(delegate);
        successMockNewsService.query(null).toFlowable().subscribe(subscriber);

        subscriber.assertValueCount(1);
        ApiResponse apiResponse = subscriber.values().get(0);
        assertTrue(apiResponse.success());
        assertEquals(apiResponse.getTotalResults(), new Integer(38));
        assertNull(apiResponse.getErrorMessage());
        assertNull(apiResponse.getErrorCode());

        assertEquals(apiResponse.getArticles().get(0).getTitle(), "SRH vs RR Live Score, IPL 2019 Match at Hyderabad: Williamson & Shankar Depart in Quick Succession - News18");
        assertEquals(apiResponse.getArticles().get(1).getSource().getName(), "The Times of India");
        assertEquals(apiResponse.getArticles().get(2).getUrl(), "https://www.cricbuzz.com/cricket-news/107346/kxip-hope-home-is-where-the-success-is");
        assertEquals(apiResponse.getArticles().get(3).getUrlToImage(), "https://www.thehindu.com/news/international/nxb9cv/article26680358.ece/ALTERNATES/LANDSCAPE_615/30th-zentrifuge-envihab-dlr");
        assertEquals(apiResponse.getArticles().get(4).getDescription(), "International Business News: WASHINGTON: Investigators looking into a Boeing 737 MAX crash in Ethiopia that killed 157 people have reached a preliminary conclusion that an anti-st.");
    }

    @Test
    public void testSuccessError() throws IOException {
        TestSubscriber<ApiResponse> subscriber = new TestSubscriber<>();

        ErrorMockNewsService errorMockNewsService = new ErrorMockNewsService(delegate);
        errorMockNewsService.query(null).toFlowable().subscribe(subscriber);

        subscriber.assertValueCount(1);
        ApiResponse apiResponse = subscriber.values().get(0);
        assertFalse(apiResponse.success());
        assertEquals(apiResponse.getErrorMessage(), "Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.");
        assertEquals(apiResponse.getErrorCode(), "apiKeyInvalid");
    }

}
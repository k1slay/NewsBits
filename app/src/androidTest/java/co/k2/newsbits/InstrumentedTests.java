package co.k2.newsbits;

import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import co.k2.newsbits.headlines.HeadlinesActivity;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTests {

    private HeadlinesActivity headlinesActivity;
    private Context context;

    @Rule
    public ActivityTestRule<HeadlinesActivity> activityTestRule = new ActivityTestRule<>(HeadlinesActivity.class, true, false);

    @Before
    public void setup() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    @Test
    public void uiTest() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        assertEquals("co.k2.newsbits", appContext.getPackageName());
    }
}

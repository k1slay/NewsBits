package co.k2.newsbits;

import android.content.Context;
import android.view.View;

import com.google.gson.Gson;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import co.k2.newsbits.common.Utils;
import co.k2.newsbits.data.models.ApiResponse;
import co.k2.newsbits.headlines.HeadlinesActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTests {

    private HeadlinesActivity headlinesActivity;
    private Context context;
    private static final String RESPONSE_SUCCESS = "{\"status\":\"ok\",\"totalResults\":38,\"articles\":[{\"source\":{\"id\":null,\"name\":\"Espn.com\"},\"author\":null,\"title\":\"Zags' Perkins rues 'bonehead' tech late in loss - ESPN\",\"description\":\"Josh Perkins said he \\\"instinctually\\\" reached for the ball as Texas Tech attempted an inbounds pass, resulting in a critical technical foul late in Gonzaga's 75-69 loss in the Elite Eight.\",\"url\":\"http://www.espn.com/mens-college-basketball/story/_/id/26402922/zags-perkins-rues-bonehead-tech-late-loss\",\"urlToImage\":\"https://a2.espncdn.com/combiner/i?img=%2Fphoto%2F2019%2F0331%2Fr522214_1296x729_16%2D9.jpg\",\"publishedAt\":\"2019-03-31T05:14:22Z\",\"content\":\"ANAHEIM, Calif. -- Gonzaga guard Josh Perkins said he'll never forget his late technical foul in Saturday night's 75-69 loss to Texas Tech in the Elite Eight. Gonzaga was down 71-69 with 11 seconds to play at the Honda Center when Perkins reached across the b… [+1917 chars]\"},{\"source\":{\"id\":\"usa-today\",\"name\":\"USA Today\"},\"author\":\"Andrea Mandell\",\"title\":\"'SNL' mocks Jussie Smollett in sketch that doubts his innocence. Did they go too far? - USA TODAY\",\"description\":\"With host Sandra Oh playing Jussie Smollett's manager, \\\"SNL\\\" imagined a tense meeting between the \\\"Empire\\\" star and Fox execs. Twitter was not impressed.\",\"url\":\"https://www.usatoday.com/story/life/entertainthis/2019/03/31/snl-mocks-jussie-smollett-harsh-sketch-and-people-mad/3324696002/\",\"urlToImage\":\"https://www.gannett-cdn.com/presto/2019/03/31/USAT/88792051-168b-41e1-969a-0d5b48a993bb-AP_APTOPIX_Empire_Cast_Member_Attack.JPG?crop=5319,2992,x0,y300&width=3200&height=1680&fit=bounds\",\"publishedAt\":\"2019-03-31T04:58:00Z\",\"content\":\"Jussie Smollett got the \\\"SNL\\\" treatment Saturday night. (Photo: Paul Beaty, AP) Jussie Smollett took two comedic hits Saturday night. First Chris Rock went after him at the NAACP Image Awards. Then \\\"Saturday Night Live\\\" devoted a sketch to how they imagined S… [+2320 chars]\"},{\"source\":{\"id\":null,\"name\":\"12up.com\"},\"author\":\"Sam Dunn\",\"title\":\"UMBC Congratulates Virginia on Elite 8 Win With Troll-tastic Tweet - 12up\",\"description\":\"\",\"url\":\"https://www.12up.com/posts/6332988-umbc-congratulates-virginia-on-elite-8-win-with-troll-tastic-tweet\",\"urlToImage\":\"https://images2.minutemediacdn.com/image/upload/c_fill,w_1200,h_630,f_auto,q_auto,g_auto/shape/cover/sport/5ca033742e43166709000001.png\",\"publishedAt\":\"2019-03-31T03:40:06Z\",\"content\":\"The last team to beat the Virginia Cavaliers in the NCAA Tournament? Well, you'll have to look back more than a year, but even casual fans of March Madness will tell you with a giddy energy that it was none other than the Terriers of the University of Marylan… [+688 chars]\"},{\"source\":{\"id\":\"engadget\",\"name\":\"Engadget\"},\"author\":\"Jon Fingas\",\"title\":\"Apple Music code hints at Chromecast support - Engadget\",\"description\":\"Apple Music might soon add Chromecast support, letting you stream it to a whole host of Google-powered devices.\",\"url\":\"https://www.engadget.com/2019/03/30/apple-music-chromecast-code/\",\"urlToImage\":\"https://o.aolcdn.com/images/dims?thumbnail=1200%2C630&quality=80&image_uri=https%3A%2F%2Fo.aolcdn.com%2Fimages%2Fdims%3Fcrop%3D1600%252C1063%252C0%252C0%26quality%3D85%26format%3Djpg%26resize%3D1600%252C1063%26image_uri%3Dhttps%253A%252F%252Fs.yimg.com%252Fos%252Fcreatr-uploaded-images%252F2019-03%252F8a737020-5324-11e9-abd6-320ca96d33b6%26client%3Da1acac3e1b3290917d92%26signature%3D2a948af3e4c3d7bb23bf8b6706f1dc2f78911caa&client=amp-blogside-v2&signature=b84ed31750140107976ea2bb9be834764ebaca4d\",\"publishedAt\":\"2019-03-31T03:30:12Z\",\"content\":\"Whether or not Apple Music is coming to Google Home, there are signs you might get to use it with some Google-powered devices. The 9to5Google team has found multiple lines of code in Apple Music's Android app that reference Chromecast support, including some … [+224 chars]\"},{\"source\":{\"id\":\"fox-news\",\"name\":\"Fox News\"},\"author\":null,\"title\":\"Chris Rock slams Jussie Smollett at NAACP awards: 'What the hell was he thinking?' - Fox News\",\"description\":\"Jussie Smollett might have been a no-show at Saturday night's NAACP Image Awards but that didn't stop comedian Chris Rock from blasting the \\\"Empire\\\" star.\",\"url\":\"https://www.foxnews.com/entertainment/chris-rock-slams-jussie-smollett-at-naacp-awards-what-the-hell-was-he-thinking\",\"urlToImage\":\"https://static.foxnews.com/foxnews.com/content/uploads/2019/03/GettyImages-1139386032.jpg\",\"publishedAt\":\"2019-03-31T03:27:38Z\",\"content\":\"Jussie Smollett might have been a no-show at Saturday night's NAACP Image Awards in Hollywood but that didn't stop comedian Chris Rock from blasting the \\\"Empire\\\" star. \\\"They said 'No Jussie Smollett jokes'... What a waste of light skin. Do you know what I cou… [+2271 chars]\"},{\"source\":{\"id\":null,\"name\":\"Bbc.com\"},\"author\":\"https://www.facebook.com/bbcnews\",\"title\":\"Ukraine election: Comedian is front runner ahead of first round - BBC News\",\"description\":\"Petro Poroshenko is seeking re-election but the surprise front-runner is comic Volodymyr Zelenskiy.\",\"url\":\"https://www.bbc.com/news/world-europe-47763176\",\"urlToImage\":\"https://ichef.bbci.co.uk/images/ic/1024x576/p0705hn1.jpg\",\"publishedAt\":\"2019-03-31T02:48:58Z\",\"content\":\"Media caption The comedian who could be president Polls have opened in Ukraine as the country votes in the first round of presidential elections. Current leader Petro Poroshenko is seeking re-election but the surprise front-runner is comedian Volodymyr Zelens… [+2609 chars]\"},{\"source\":{\"id\":null,\"name\":\"Mmafighting.com\"},\"author\":\"Shaun Al-Shatti\",\"title\":\"UFC Philadelphia in Tweets: Pros react to Justin Gaethje knocking out Edson Barboza, more - MMA Fighting\",\"description\":\"The MMA community reacts to Justin Gaethje’s thunderous knockout of Edson Barboza, plus much more from UFC Philadelphia.\",\"url\":\"https://www.mmafighting.com/2019/3/30/18288684/ufc-philadelphia-in-tweets-pros-react-to-justin-gaethje-knocking-out-edson-barboza-more\",\"urlToImage\":\"https://cdn.vox-cdn.com/thumbor/roYl1W-L6nm2mzKJ12wJxqrELbQ=/0x323:4498x2678/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/15997698/usa_today_12447957.jpg\",\"publishedAt\":\"2019-03-31T02:29:30Z\",\"content\":\"Justin Gaethje did it again. The lightweight divisions human highlight reel needed less than a round to knock out Edson Barboza on Saturday night, downing Barboza with a monstrous right hand in the main event of UFC Philadelphia. The win gave Gaethje back-to-… [+3778 chars]\"},{\"source\":{\"id\":\"cbs-news\",\"name\":\"CBS News\"},\"author\":\"CBS/AP\",\"title\":\"Enivronmental activist elected as Slovakia's first female president - CBS News\",\"description\":\"Zuzana Caputova cited journalist Jan Kuciak\u200B's death as one of the reasons she decided to run for president, which is a largely ceremonial role\",\"url\":\"https://www.cbsnews.com/news/zuzana-caputova-elected-slovakias-first-female-president/\",\"urlToImage\":\"https://cbsnews2.cbsistatic.com/hub/i/r/2019/03/31/2dafac65-b5cc-4f58-bfc5-0d6770be219a/thumbnail/1200x630/6fa8532334e65912d111d158b7231715/2019-03-31t002156z-1927270008-rc1d4e93e950-rtrmadp-3-slovakia-election-president.jpg\",\"publishedAt\":\"2019-03-31T02:04:00Z\",\"content\":\"A liberal environmental activist has been elected as the first female president of Slovakia. Relative newcomer Zuzana Caputova had 58 percent of the vote with almost 95 percent of returns counted in Saturday's runoff election, topping European Commission Vice… [+3033 chars]\"},{\"source\":{\"id\":null,\"name\":\"10tv.com\"},\"author\":null,\"title\":\"Suspect arrested in University of South Carolina student's death - 10TV\",\"description\":\"Samantha Josephson was last seen about 2 a.m. Friday, getting into a car outside a bar in the city of Columbia's 5 Points area.\",\"url\":\"https://www.10tv.com/article/suspect-arrested-university-south-carolina-students-death-2019-mar\",\"urlToImage\":\"https://www.10tv.com/sites/default/files/styles/article_image/public/2019/03/30/samantha_jonhson_cbs.jpg?itok=9GRF0kC5\",\"publishedAt\":\"2019-03-31T01:35:08Z\",\"content\":\"COLUMBIA, S.C. (AP) Police in South Carolina say they've arrested a suspect in connection with the death of a college student. Columbia Police Chief Skip Holbrook said at a news conference that 24-year-old Nathaniel David Rowland was detained early Saturday a… [+549 chars]\"},{\"source\":{\"id\":null,\"name\":\"Aol.com\"},\"author\":\"Joe Peters\",\"title\":\"Saudis gained access to Amazon CEO Bezos' phone: Bezos' security chief - AOL\",\"description\":\"The security chief for Amazon chief executive Jeff Bezos said on Saturday that the Saudi government had access to Bezos' phone and gained private info\",\"url\":\"https://www.aol.com/article/finance/2019/03/30/saudis-allegedly-gained-access-to-amazon-ceo-bezos-phone/23703166/\",\"urlToImage\":\"https://o.aolcdn.com/images/dims3/GLOB/crop/1558x1022+347+227/resize/1028x675!/format/jpg/quality/85/https%3A%2F%2Fs.yimg.com%2Fos%2Fcreatr-images%2F2019-03%2F6afa87b0-4a55-11e9-83f5-c2d3600ccb83\",\"publishedAt\":\"2019-03-31T00:59:35Z\",\"content\":\"WASHINGTON, March 30 (Reuters) - The security chief for Amazon chief executive Jeff Bezos said on Saturday that the Saudi government had access to Bezos' phone and gained private information from it. Gavin De Becker, a longtime security consultant, said he ha… [+3406 chars]\"},{\"source\":{\"id\":null,\"name\":\"Bostonglobe.com\"},\"author\":null,\"title\":\"Elizabeth Warren says big agriculture companies should be broken up - The Boston Globe\",\"description\":\"The Massachusetts senator has also called for breaking up such companies as Amazon and Facebook.\",\"url\":\"https://www.bostonglobe.com/news/politics/2019/03/30/elizabeth-warren-says-big-agriculture-companies-should-broken/LIN95D9l12TSWBeE6DUhhJ/story.html\",\"urlToImage\":\"https://www.bostonglobe.com/rf/image_585w/Boston/2011-2020/2019/03/31/BostonGlobe.com/Politics/Images/80347dc163a24acf9ef31f698de9ae64-80347dc163a24acf9ef31f698de9ae64-0.jpg\",\"publishedAt\":\"2019-03-31T00:52:00Z\",\"content\":\"STORM LAKE, Iowa — Democratic presidential contenders Elizabeth Warren and Amy Klobuchar expressed support Saturday for strengthening antitrust laws and enforcement to break up big agriculture monopolies. ‘‘You’ve got these giant corporations that are making … [+7211 chars]\"},{\"source\":{\"id\":null,\"name\":\"Yahoo.com\"},\"author\":null,\"title\":\"Report: NYPD investigating Kristaps Porzingis for alleged rape hours after 2018 ACL tear - Yahoo Sports\",\"description\":\"The NYPD is reportedly investigating a rape allegation against former Knicks star Kristaps Porzingis, which allegedly took place just hours after he tore his ACL in 2018.\",\"url\":\"https://sports.yahoo.com/nypd-investigating-kristaps-porzingis-rape-allegation-acl-tear-new-york-knicks-dallas-mavericks-235716403.html\",\"urlToImage\":\"https://s.yimg.com/uu/api/res/1.2/wg4xGNgXAl8Q4qh_pbiBzA--~B/aD0zNjQ4O3c9NTQ3MjtzbT0xO2FwcGlkPXl0YWNoeW9u/https://img.huffingtonpost.com/asset/5ca000201f00001c017f0aed.jpeg\",\"publishedAt\":\"2019-03-30T23:57:00Z\",\"content\":\"The New York Police Department is currently investigating Kristaps Porzingis after claims that he allegedly raped his neighbor in 2018, just hours after he tore his ACL while playing with the New York Knicks, according to Tina Moore of the New York Post. Acco… [+2368 chars]\"},{\"source\":{\"id\":null,\"name\":\"Aol.com\"},\"author\":\"Joe Peters\",\"title\":\"Judge scraps Trump order for Arctic, Atlantic oil leasing - AOL\",\"description\":\"The decision issued late Friday by U.S. District Court Judge Sharon Gleason leaves intact President Barack Obama’s policies putting areas off drilling\",\"url\":\"https://www.aol.com/article/news/2019/03/30/judge-scraps-trump-order-for-arctic-atlantic-oil-leasing/23703144/\",\"urlToImage\":\"https://o.aolcdn.com/images/dims3/GLOB/crop/3552x2329+943+343/resize/1028x675!/format/jpg/quality/85/https%3A%2F%2Fs.yimg.com%2Fos%2Fcreatr-images%2F2019-03%2F3b6cac10-5344-11e9-bbbf-26a11e35fa25\",\"publishedAt\":\"2019-03-30T23:20:03Z\",\"content\":\"ANCHORAGE, Alaska, March 30 (Reuters) - A federal judge in Alaska has overturned U.S. President Donald Trumps attempt to open vast areas of the Arctic and Atlantic oceans to oil and gas leasing. The decision issued late Friday by U.S. District Court Judge Sha… [+6888 chars]\"},{\"source\":{\"id\":\"nbc-news\",\"name\":\"NBC News\"},\"author\":\"Dennis Romero, Associated Press\",\"title\":\"Trump says he'll step in to help Navy SEAL accused of murder - NBCNews.com\",\"description\":\"Trump tweets that he wants to help Navy SEAL Chief Edward Gallagher, accused of murdering a teen captive in Iraq, get out of the brig while he awaits trial.\",\"url\":\"https://www.nbcnews.com/politics/politics-news/trump-says-he-ll-step-help-navy-seal-accused-murder-n989226\",\"urlToImage\":\"https://media1.s-nbcnews.com/j/newscms/2019_13/2804951/190330-edward-gallagher-al-1633_5530e6215c38861c550efe79bd373a67.nbcnews-fp-1200-630.jpg\",\"publishedAt\":\"2019-03-30T23:13:00Z\",\"content\":\"Get breaking news alerts and special reports. The news and stories that matter, delivered weekday mornings. SUBSCRIBE March 30, 2019, 11:13 PM GMT President Donald Trump vowed Saturday to place a Navy SEAL accused of killing a teenage captive in Iraq in \\\"less… [+2365 chars]\"},{\"source\":{\"id\":\"reuters\",\"name\":\"Reuters\"},\"author\":\"William Schomberg\",\"title\":\"UK's May risks 'total collapse' of government in Brexit impasse: Sunday Times - Reuters\",\"description\":\"British Prime Minister Theresa May risks the \\\"total collapse\\\" of her government if she fails to get her battered Brexit deal through parliament, the Sunday Times newspaper said, amid growing speculation that she might call an early election.\",\"url\":\"https://www.reuters.com/article/us-britain-eu-may-cabinet/uks-may-risks-total-collapse-of-government-in-brexit-impasse-sunday-times-idUSKCN1RB0R4\",\"urlToImage\":\"https://s4.reutersmedia.net/resources/r/?m=02&d=20190330&t=2&i=1371875867&w=1200&r=LYNXNPEF2T0TR\",\"publishedAt\":\"2019-03-30T22:28:00Z\",\"content\":\"LONDON (Reuters) - British Prime Minister Theresa May risks the “total collapse” of her government if she fails to get her battered Brexit deal through parliament, the Sunday Times newspaper said, amid growing speculation that she might call an early election… [+3363 chars]\"},{\"source\":{\"id\":null,\"name\":\"Gizmodo.com\"},\"author\":\"Tom McKay\",\"title\":\"Mark Zuckerberg: OK, Fine, Regulate Facebook - Gizmodo\",\"description\":\"Facebook CEO Mark Zuckerberg—whose company has blundered its way into controversies over everything from user privacy and data breaches to amplification of extremist content and literal genocide as of late—responded to growing criticism of the tech sector by …\",\"url\":\"https://gizmodo.com/mark-zuckerberg-ok-fine-regulate-facebook-1833693687\",\"urlToImage\":\"https://i.kinja-img.com/gawker-media/image/upload/s--qA0Jjn2W--/c_fill,fl_progressive,g_center,h_900,q_80,w_1600/znewabk3fc5kzd5pegng.jpg\",\"publishedAt\":\"2019-03-30T22:25:00Z\",\"content\":\"Facebook CEO Mark Zuckerbergwhose company has blundered its way into controversies over everything from user privacy and data breaches to amplification of extremist content and literal genocide as of lateresponded to growing criticism of the tech sector by ca… [+13482 chars]\"},{\"source\":{\"id\":null,\"name\":\"Pagesix.com\"},\"author\":\"Christine Burroni, Emily Smith\",\"title\":\"Mick Jagger seeks hospital treatment, postponing Rolling Stones tour - Page Six\",\"description\":\"The legendary band postponed its North American tour Saturday after 75-year-old rock icon Mick Jagger was diagnosed with an undisclosed illness.\",\"url\":\"https://pagesix.com/2019/03/30/mick-jagger-seeks-hospital-treatment-postponing-rolling-stones-tour/\",\"urlToImage\":\"https://nyppagesix.files.wordpress.com/2019/03/190330-mick-jagger-hospital.jpg?quality=90&strip=all&w=1200\",\"publishedAt\":\"2019-03-30T22:02:00Z\",\"content\":\"Mick is sick and The Stones arent rolling. The legendary rockers postponed their North American tour Saturday after 75-year-old icon Mick Jagger was diagnosed with an undisclosed illness. Just three weeks before the group was supposed to kick off the tour, do… [+2937 chars]\"},{\"source\":{\"id\":null,\"name\":\"Rollingstone.com\"},\"author\":\"Daniel Kreps, Daniel Kreps\",\"title\":\"Elton John Aligns With George Clooney’s Boycott of Brunei-Owned Hotels - Rolling Stone\",\"description\":\"“I believe that love is love and being able to love as we choose is a basic human right,” singer writes after Asian nation passes “Sharia” law\",\"url\":\"https://www.rollingstone.com/music/music-news/elton-john-george-clooney-brunei-hotel-boycott-815733/\",\"urlToImage\":\"https://www.rollingstone.com/wp-content/uploads/2019/03/10119289am.jpg\",\"publishedAt\":\"2019-03-30T20:33:00Z\",\"content\":\"Elton John has aligned with George Clooney ’s boycott of Brunei-owned luxury hotels after the Asian nation passed strict “Sharia law” that would allow for the stoning death of LGBTQ people. As Clooney noted in his op-ed to Deadline, the Sultan of Brunei owns … [+2095 chars]\"},{\"source\":{\"id\":null,\"name\":\"Seekingalpha.com\"},\"author\":\"Rida Morwa\",\"title\":\"Deep Value And 18.5% Yield From Washington Prime - Seeking Alpha\",\"description\":\"WPG 2018 earnings in line with initial guidance. Fundamentals continue to improve despite recent bankruptcies. WPG’s redevelopment program hits the ground runni\",\"url\":\"https://seekingalpha.com/article/4251865-deep-value-18_5-percent-yield-washington-prime\",\"urlToImage\":\"https://static.seekingalpha.com/uploads/2019/3/1/16392-15514792320452054.png\",\"publishedAt\":\"2019-03-30T20:15:00Z\",\"content\":\"Co-produced with Beyond Saving for High Dividend Opportunities Washington Prime Group ( WPG ) is a mall REIT that is beaten down and trading at highly opportunistic valuations. Investors have been very negative on anything that is retail related, despite the … [+14804 chars]\"},{\"source\":{\"id\":\"fox-news\",\"name\":\"Fox News\"},\"author\":\"David Aaro\",\"title\":\"At least two killed in Gaza as thousands of Palestinians demonstrate near Israeli border - Fox News\",\"description\":\"Tens of thousands of Palestinians are protesting near the border of Israel to signify how they will not be leaving the area amidst clashes with the Israeli military.\",\"url\":\"https://www.foxnews.com/world/at-least-two-teenagers-killed-in-gaza-as-thousands-of-palestinians-demonstrate\",\"urlToImage\":\"https://media2.foxnews.com/BrightCove/694940094001/2019/03/30/694940094001_6020745990001_6020746755001-vs.jpg\",\"publishedAt\":\"2019-03-30T19:16:57Z\",\"content\":\"At least two teenagers have been killed in clashes with Israeli military as tens of thousands of Palestinians gathered at the Israeli border to mark a year of the protest movement. The latest demonstrations honored the \\\"Great March of Return\\\" that started on … [+2959 chars]\"}]}";

    @Rule
    public ActivityTestRule<HeadlinesActivity> activityTestRule = new ActivityTestRule<>(HeadlinesActivity.class, true, false);

    @Before
    public void setup() throws Exception {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        headlinesActivity = activityTestRule.launchActivity(null);
    }

    @Test
    public void uiTest() {
        // Error response
        Throwable throwable = new Throwable("No internet access");
        headlinesActivity.runOnUiThread(() -> {
            // Emulate no data loaded
            headlinesActivity.adapter.update(new ArrayList<>());
            headlinesActivity.showError(throwable);
        });
        // Check error screen
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
        onView(withId(R.id.error)).check(matches(isDisplayed()));
        onView(withId(R.id.error_text)).check(matches(withText("Error fetching news: No internet access")));

        Gson gson = new Gson();
        ApiResponse apiResponse = gson.fromJson(RESPONSE_SUCCESS, ApiResponse.class);

        // News fetch success
        headlinesActivity.runOnUiThread(() -> {
            headlinesActivity.setupList(apiResponse.getArticles());
        });
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
        onView(withId(R.id.error)).check(matches(not(isDisplayed())));
        onView(withId(R.id.headlines_list)).check(matches(isDisplayed()));

        // Check recycler view news items
        Matcher<View> recyclerItem0 = recyclerViewItemMatcher(R.id.headlines_list, 0);
        onView(allOf(withText("Zags' Perkins rues 'bonehead' tech late in loss - ESPN"), isDescendantOfA(recyclerItem0))).check(matches(isDisplayed()));
        onView(allOf(withText("Espn.com"), isDescendantOfA(recyclerItem0))).check(matches(isDisplayed()));
        Matcher<View> recyclerItem2 = recyclerViewItemMatcher(R.id.headlines_list, 2);
        onView(allOf(withText("UMBC Congratulates Virginia on Elite 8 Win With Troll-tastic Tweet - 12up"), isDescendantOfA(recyclerItem2))).check(matches(isDisplayed()));
        onView(allOf(withText("12up.com"), isDescendantOfA(recyclerItem2))).check(matches(isDisplayed()));

        headlinesActivity.runOnUiThread(() -> {
            // Throw error when data loaded
            headlinesActivity.showError(throwable);
        });
        // Check error snackbar
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText("Error fetching news: No internet access")));

    }

    @Test
    public void utilsTest() {
        // right now
        long justNow = System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(15);
        String justNowText = Utils.dateToTimeSinceText(context, new Date(justNow));
        Assert.assertEquals(justNowText, "Just now");
        // Hours
        long time5hrsAgo = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(5);
        String time5hrsAgoText = Utils.dateToTimeSinceText(context, new Date(time5hrsAgo));
        Assert.assertEquals(time5hrsAgoText, "5 hours ago");
        long time1hrAgo = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1);
        String time1hrAgoText = Utils.dateToTimeSinceText(context, new Date(time1hrAgo));
        Assert.assertEquals(time1hrAgoText, "1 hour ago");
        // minutes
        long time5minutesAgo = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5);
        String time5minutesAgoText = Utils.dateToTimeSinceText(context, new Date(time5minutesAgo));
        Assert.assertEquals(time5minutesAgoText, "5 minutes ago");
        long time1minuteAgo = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1);
        String time1minuteText = Utils.dateToTimeSinceText(context, new Date(time1minuteAgo));
        Assert.assertEquals(time1minuteText, "1 minute ago");
        // days
        long time5daysAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(5);
        String time5daysAgoText = Utils.dateToTimeSinceText(context, new Date(time5daysAgo));
        Assert.assertEquals(time5daysAgoText, "5 days ago");
        long time1dayAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1);
        String time1dayText = Utils.dateToTimeSinceText(context, new Date(time1dayAgo));
        Assert.assertEquals(time1dayText, "1 day ago");
    }

    private Matcher<View> recyclerViewItemMatcher(int recyclerViewId, int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                View childView;
                if (recyclerView != null) {
                    childView = recyclerView.getChildAt(position);
                    return view.equals(childView);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }


}

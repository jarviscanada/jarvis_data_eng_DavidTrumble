package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterDaoIntTest {

    private TwitterDao dao;

    @Before
    public void setUp() throws Exception {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");

        HttpHelper httpHelper = new TwitterHttpHelper(
                CONSUMER_KEY,
                CONSUMER_SECRET,
                ACCESS_TOKEN,
                TOKEN_SECRET);

        this.dao = new TwitterDao(httpHelper);
    }

    @Test
    public void create() {

        String hashtag = "#jarvis";
        String text = "TwitterDaoIntTest" + hashtag + " " + System.currentTimeMillis();
        Double lat = 1d;
        Double lon = -1d;
        Tweet tweet = TweetUtil.createTweet(text, lat, lon);

        Tweet postedTweet = dao.create(tweet);

        assertEquals(text, postedTweet.getText());
        assertNotNull(postedTweet.getCoordinates());
        assertEquals(2, postedTweet.getCoordinates().getCoordinates().size());
        assertEquals(lon, postedTweet.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, postedTweet.getCoordinates().getCoordinates().get(1));
        assertTrue(hashtag.contains(postedTweet.getEntities().getHashtags().get(0).getText()));
    }

    @Test
    public void findById() {
        String tweetId = "1458579880216563716";
        Tweet findId = dao.findById(tweetId);
    }

    @Test
    public void deleteById() {
        String tweetId = "1443790086643011585";
        Tweet deleteId = dao.deleteById(tweetId);
    }
}
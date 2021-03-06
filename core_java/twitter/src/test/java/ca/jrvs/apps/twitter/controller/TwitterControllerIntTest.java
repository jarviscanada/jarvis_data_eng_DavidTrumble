package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TwitterControllerIntTest {

    private Controller controller;
    private Service service;
    private CrdDao dao;

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

        dao = new TwitterDao(httpHelper);
        service = new TwitterService(dao);
        controller = new TwitterController(service);
    }

    @Test
    public void postTweet() {
        String[] args = {"post", "TwitterControllerIntTest", "50:-50"};
        Tweet tweet = controller.postTweet(args);
    }

    @Test
    public void showTweet() {
        String[] args = {"show", "1458579880216563716"};
        Tweet tweet = controller.showTweet(args);
    }

    @Test
    public void deleteTweet() {
        String[] args = {"delete", "1458579880216563716, 1443790086643011585"};
        List<Tweet> tweets = controller.deleteTweet(args);
    }
}
package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

public class TwitterHttpHelperTest {

    @Test
    public void httpPost() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey,
                consumerSecret,
                accessToken,
                tokenSecret);
        HttpResponse httpResponse = httpHelper
                .httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=httpHelperTweet"));
        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
    }

    @Test
    public void httpGet() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey,
                consumerSecret,
                accessToken,
                tokenSecret);
        HttpResponse httpResponse = httpHelper
                .httpGet(new URI("https://api.twitter.com/1.1/users/search.json?q=dttrumble"));
        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
    }
}
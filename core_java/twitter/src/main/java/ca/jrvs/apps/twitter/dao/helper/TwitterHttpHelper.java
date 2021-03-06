package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class TwitterHttpHelper implements HttpHelper {

    final Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);

    /**
     * Dependencies are specified as private member variables
     */
    private OAuthConsumer consumer;
    private HttpClient httpClient;

    /**
     * Constructor Setup dependencies using secrets
     *
     * @param consumerKey
     * @param consumerSecret
     * @param accessToken
     * @param tokenSecret
     */
    public TwitterHttpHelper(String consumerKey,
                             String consumerSecret,
                             String accessToken,
                             String tokenSecret) {
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);
        httpClient = new DefaultHttpClient();
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        try {
            HttpPost request = new HttpPost(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        } catch (IOException |
                OAuthExpectationFailedException |
                OAuthMessageSignerException |
                OAuthCommunicationException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        try {
            HttpGet request = new HttpGet(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        } catch (IOException |
                OAuthExpectationFailedException |
                OAuthMessageSignerException |
                OAuthCommunicationException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException();
        }
    }
}

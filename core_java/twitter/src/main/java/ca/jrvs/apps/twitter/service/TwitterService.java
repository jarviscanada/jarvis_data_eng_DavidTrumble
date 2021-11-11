package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitterService implements Service {

    final Logger logger = LoggerFactory.getLogger(TwitterService.class);

    private CrdDao dao;

    public TwitterService(CrdDao dao) { this.dao = dao; }

    @Override
    public Tweet postTweet(Tweet tweet) {
        validatePostTweet(tweet);
        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        validateId(id);
        return (Tweet) dao.findById(id);
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> existingTweets = new ArrayList<>();

        try {
            Arrays.stream(ids).forEach(this::validateId);
            Arrays.stream(ids).forEach(id -> existingTweets.add((Tweet) dao.deleteById(id)));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            logger.info("failed to delete list of tweets");
        }
        return existingTweets;
    }

    private void validatePostTweet(Tweet tweet) {

        final Integer maxTextBody = 140;
        final Integer minGeoAddress = -90;
        final Integer maxGeoAddress = 90;
        String textBody = tweet.getText();
        Double longitude = tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = tweet.getCoordinates().getCoordinates().get(1);

        if (textBody.length() > maxTextBody) {
            logger.info("text-body out of bounds");
            throw new IllegalArgumentException(
                    "max tweet range is 140 characters");
        }
        if (longitude > maxGeoAddress || longitude < minGeoAddress) {
            logger.info("longitude out of bounds");
            throw new IllegalArgumentException(
                    "geo range is -90 to 90");
        }
        if (latitude > maxGeoAddress || latitude < minGeoAddress) {
            logger.info("latitude out of bounds");
            throw new IllegalArgumentException(
                    "geo range is -90 to 90");
        }
    }

    private void validateId(String id) {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException ex) {
            logger.error(ex.getMessage());
            throw new IllegalArgumentException(
                    "id error: incorrect tweet id");
        }
    }
}

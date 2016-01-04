package rss.hyesoo.rss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    //Sigleton Pattern to provide a global point of access to user instance
    private static User ourInstance = new User();

    private ArrayList<Feed> feeds;

    public static User getInstance() {
        return ourInstance;
    }

    private User() {
        this.feeds = new ArrayList<>();
    }

    public ArrayList<Feed> getFeeds() {
        return this.feeds;
    }

    public Feed getFeedbyPos(int pos) {
        return this.feeds.get(pos);
    }

    public void addFeed(Feed feed) {
        if (!feeds.contains(feed)) {
            this.feeds.add(feed);
        }
    }
}
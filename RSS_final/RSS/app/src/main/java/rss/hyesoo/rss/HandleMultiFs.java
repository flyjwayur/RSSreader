package rss.hyesoo.rss;

import java.util.ArrayList;


public class HandleMultiFs implements Runnable, MyObserver {

    private ArrayList<Feed> feeds;
    private MyObserver observer;

    public HandleMultiFs(ArrayList<Feed> feeds, MyObserver o) {
        this.feeds = feeds;
        this.observer = o;
    }

    @Override
    public void run() {
        //populate Items by thread array
        //thread handles each feed
        int size = feeds.size();
        for (Feed f : feeds) {
            Thread[] readFeed = new Thread[size];
            for (int i = 0; i < readFeed.length; i++) {
                readFeed[i] = new Thread(new HandleXML(f.getUrl(), f, observer));
                readFeed[i].start();
            }
            observer.updateUI();
        }
    }

    public void updateUI() {
        //Later, after making 'add feed by user' feature, remember to implements 'UI update'
    }
}

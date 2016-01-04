package rss.hyesoo.rss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class FeedListAdapter extends ArrayAdapter<Feed> {
    private ArrayList<Feed> feeds;

    public FeedListAdapter(Context context, int resource, ArrayList<Feed> feeds) {
        super(context, resource, feeds);
        this.feeds = feeds;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Make sure we have a view to work with(may have been given null)
        View feedsView = convertView;
        if (feedsView == null) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            feedsView = li.inflate(R.layout.feed_row, parent, false);
        }
        //find the feed from feed array
        Feed thisfeeds = feeds.get(position);
        //fill the view
        if (thisfeeds != null) {
            TextView feedTitle = (TextView) feedsView.findViewById(R.id.feedTitle);
            feedTitle.setText(thisfeeds.getTitle());
        }
        return feedsView;
    }
}

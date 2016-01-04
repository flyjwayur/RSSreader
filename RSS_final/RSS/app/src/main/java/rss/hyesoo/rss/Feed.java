package rss.hyesoo.rss;


import java.net.URL;
import java.util.ArrayList;

//feed structure
public class Feed {
    private URL url;
    private String title;
    private ArrayList<Item> items;

    public Feed(URL url, String title) {
        this.url = url;
        this.title = title;
        this.items = new ArrayList<>();
    }

    public URL getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItemByPos(int pos) {
        return this.items.get(pos);
    }

    public void clear() {
        items.clear();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return this.title;
    }

    //check if the two feed objects are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Feed feed = (Feed) o;

        if (!url.equals(feed.url)) return false;
        return title.equals(feed.title);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}
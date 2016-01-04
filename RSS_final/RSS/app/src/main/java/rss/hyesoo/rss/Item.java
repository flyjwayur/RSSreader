package rss.hyesoo.rss;

//Item structure
public class Item {

    private String title;
    private String link;
    private String description;
    private String url;

    public Item() {
        this.title = null;
        this.link = null;
        this.url = null;
        this.description = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //check item has title, link, desciption
    public boolean itemReady(){
        return (this.title != null && this.link != null && this.description != null);
    }

    @Override
    public String toString() {
        return this.title;
    }
}

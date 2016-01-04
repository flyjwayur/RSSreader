package rss.hyesoo.rss;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class HandleXML implements Runnable {

    private URL URL;
    private Feed feed;
    private MyObserver observer; //It will be used later after making add feed button funtion
    private XmlPullParserFactory xmlFactoryObject;

    public HandleXML(URL URL, Feed feed, MyObserver observer) {
        this.URL = URL;
        this.feed = feed;
        this.observer = observer;
    }
      //parsing the XML
    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text = null;
        //Create item
        try {
            event = myParser.getEventType();
            Item item = null;
            boolean checkItem = true;
            boolean newItem = false;

            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                if (checkItem) {
                    item = new Item();
                    checkItem = false;
                }

                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (name.equals("item")) {
                            newItem = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "title":
                                if (newItem) {
                                    item.setTitle(text);
                                }
                                break;
                            case "description":
                                if (newItem) {
                                    item.setDescription(text);
                                }
                                break;
                            case "link":
                                if (newItem) {
                                    item.setLink(text);
                                }
                                break;
                        }
                        break;
                }
                // add to an array list
                if (item.itemReady()) {
                    feed.addItem(item);
                    newItem = false;
                    checkItem = true;
                }
                event = myParser.next();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override

    public void run() {
        try {
            System.out.println("You have URL connection: this.URL!" + this.URL);

            //make URL connection
            URLConnection myConn = this.URL.openConnection();
            myConn.connect();
            InputStream stream = myConn.getInputStream();

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myparser.setInput(stream, null);

            parseXMLAndStoreIt(myparser);
            stream.close();
            //observer.updateUI(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
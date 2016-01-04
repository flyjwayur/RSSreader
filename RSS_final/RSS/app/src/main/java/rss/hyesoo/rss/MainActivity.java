package rss.hyesoo.rss;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements MyObserver {

    final private MyObserver observer = this;
    private FeedListAdapter adapter;
    private ListView feedsListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add feeds to a user
        try {
            User.getInstance().addFeed(new Feed(new URL("http://feeds.bbci.co.uk/news/rss.xml"), "BBC"));
            User.getInstance().addFeed(new Feed(new URL("http://www.npr.org/rss/rss.php?id=1008"), "NPR"));
            User.getInstance().addFeed(new Feed(new URL("http://www.nasa.gov/rss/dyn/image_of_the_day.rss"), "Nasa News"));
            User.getInstance().addFeed(new Feed(new URL("http://www.techlearning.com/RSS"), "Tech Learning"));
        } catch (MalformedURLException e) {
            System.err.println(e);
        }

        //Start to run thread to parse the xml
        Thread feedReader = new Thread(new HandleMultiFs(User.getInstance().getFeeds(), observer));
        feedReader.start();

        //Create an FeedListAdapter object and set the adapter with feed ListView
        adapter = new FeedListAdapter(this, R.layout.activity_main, User.getInstance().getFeeds());
        feedsListV = (ListView) findViewById(R.id.feedsLv);
        feedsListV.setAdapter(adapter);

        //Set onItemClick to open 2nd activity by intent
        feedsListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int editedPosition = position + 1;
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Position", position); // Let 2nd activity know which poistion of feeds
                startActivity(intent);
                Toast.makeText(MainActivity.this, "You selected feed" + editedPosition, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    // Kebab menu selections
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                openSearch();
                break;
            case R.id.action_about:
                aboutMenuItem();
                break;
            case R.id.action_send:
                SendMessageVia();
                break;
            case R.id.action_setting:
                settingsMenuItem();
                break;
        }
        return true;
    }

    private void openSearch() {
        //need to implement
    }

    //Dialog for App About
    private void aboutMenuItem() {
        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("This is about this article")
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private void SendMessageVia() {
        //need to implement
    }

    //Dialog for Spp Setting
    private void settingsMenuItem() {
        new AlertDialog.Builder(this)
                .setTitle("Setting")
                .setMessage("This is the setting")
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


    }

    //Later, after making 'add feed by user' feature, remember to implements 'UI update'
    public void updateUI() {

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                //the method implemented by the Main Activity to deal with the model
//                adapter.clear();
//                //clears the adapter
//                adapter.addAll(User.getInstance().getFeeds());
//                //adds all entries from FeedList to the adapter
//                adapter.notifyDataSetChanged();
//                //notify adapter of Data Set change
//
//            }
//        });
    }


}


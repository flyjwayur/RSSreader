package rss.hyesoo.rss;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity implements MyObserver {

    private ItemListAdapter adapter;
    private int pos;
    private MyObserver observer = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //getting extra information from Main activity
        Bundle extra = getIntent().getExtras();
        // key is 'Position'
        pos = extra.getInt("Position");
        //create ItemListAdapter object and set it with item ListView
        adapter = new ItemListAdapter(this, R.layout.activity_second, User.getInstance().getFeedbyPos(pos).getItems());
        ListView itemListV = (ListView) findViewById(R.id.ItemLv);
        itemListV.setAdapter(adapter);



        itemListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Send extra data which is item link by intent to WebActivity(which is Third Activity)
                Intent intent = new Intent(SecondActivity.this, WebActivity.class);
                intent.putExtra("itemURL",User.getInstance().getFeedbyPos(pos).getItemByPos(pos).getLink());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //when users click a 'refresh' icon, item updated
        switch (item.getItemId()) {
            case R.id.action_refresh:
                updateUI();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    //get feed object again by running HandleXML thread when user click fresh button
                    User.getInstance().getFeedbyPos(pos).clear();
                    Thread readFeed = new Thread(new HandleXML(User.getInstance().getFeedbyPos(pos).getUrl(), User.getInstance().getFeedbyPos(pos), observer));
                    readFeed.start();

                    adapter.clear();
                    //clears the adapter (This was clearing the
                    adapter.addAll(User.getInstance().getFeedbyPos(pos).getItems());
                    //adds all entries from FeedList to the adapter
                    adapter.notifyDataSetChanged();
                    notifyUser("Item Updated");

            }
        });
    }

    private void notifyUser(String s) {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        toast.show();
    }
}

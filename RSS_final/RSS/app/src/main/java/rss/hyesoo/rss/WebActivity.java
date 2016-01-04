package rss.hyesoo.rss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private String itemURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_item);

        //getting extra information from Second activity
        Bundle extras = getIntent().getExtras();
        itemURL = extras.getString("itemURL");
        WebView webVu = (WebView) findViewById(R.id.webView);
        LoadWebView viewBrowser = new LoadWebView();
        viewBrowser.shouldOverrideUrlLoading(webVu,itemURL);
    }


    private class LoadWebView extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView webVu, String url) {

            // Settings for WebView
            webVu.getSettings().setJavaScriptEnabled(true);
            webVu.getSettings().setDomStorageEnabled(true);
            webVu.getSettings().setLoadWithOverviewMode(true);
            webVu.getSettings().setUseWideViewPort(true);
            webVu.getSettings();

            try {
                //Load Web page
                webVu.loadUrl(url);
            } catch (Exception e) {
                //Throws exception when it crashes
                e.printStackTrace();
            }
             return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

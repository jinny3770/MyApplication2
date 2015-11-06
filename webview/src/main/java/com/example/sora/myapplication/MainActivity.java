package com.example.sora.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText url;
    Button go, back;
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = (EditText)findViewById(R.id.url);

        go = (Button)findViewById(R.id.go);
        back = (Button)findViewById(R.id.back);

        web = (WebView)findViewById(R.id.webview);
        web.setWebViewClient(new myWebClient());

        WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);
        web.loadUrl("http://m.daum.net");
        url.setText(web.getUrl().toString());

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String http = "http://";
                if(!url.getText().toString().startsWith(http))
                    web.loadUrl(http + url.getText().toString());

                else web.loadUrl(url.getText().toString());

                url.setText(web.getUrl().toString());

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.goBack();
            }
        });
    }

    class myWebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

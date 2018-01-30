package com.ufrpe.bcc.guia_bcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewDropbox extends AppCompatActivity {
    public static final String EXTRA_URL_DROPBOX = "url_dropbox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_dropbox);

        WebView wv = (WebView) findViewById(R.id.wvDropbox);

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);


        String url_dropbox = getIntent().getStringExtra(EXTRA_URL_DROPBOX);

        wv.loadUrl(url_dropbox);
    }
}

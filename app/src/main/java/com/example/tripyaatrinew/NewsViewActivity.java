package com.example.tripyaatrinew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class NewsViewActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar pbar;
    String passkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        final Bundle bundle=getIntent().getExtras();
        passkey=bundle.getString("news");
        webView = findViewById(R.id.webView);
        pbar = findViewById(R.id.pB1);
        loadWeb(passkey);
    }

    private void loadWeb(String url){

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && pbar.getVisibility() == ProgressBar.GONE){
                    pbar.setVisibility(ProgressBar.VISIBLE);
                }

                pbar.setProgress(progress);
                if(progress == 100) {
                    pbar.setVisibility(ProgressBar.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
            }
        });

        webView.loadUrl(url);

    }

}

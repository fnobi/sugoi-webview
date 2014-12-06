package com.fnobi.sugoiWebView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
    
    private final static String INITIAL_URL = "http://fnobi.github.io/sugoi-webview/web/";
    
    private WebView mWebView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sugoi_activity_main);
        
        mWebView = (WebView) findViewById(R.id.sugoi_webview);
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                applyDefaultConfig(mWebView);

                WebViewClient client = new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        Uri uri = Uri.parse(url);
                        String scheme = uri.getScheme();
                        
                        if (scheme.equals("myscheme")) {
                            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                            long[] pattern = { 0, 1000 }; // 0秒後に、2秒の振動
                            vibrator.vibrate(pattern, -1);
                            return true;
                        }
                        
                        return false;
                    }
                };

                mWebView.setWebViewClient(client);
                mWebView.loadUrl(INITIAL_URL);
            }
        });
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode != KeyEvent.KEYCODE_BACK){
            return super.onKeyDown(keyCode, event);
        }else{
            onBackKey();
            return false;
        }
    }
    
    @Override
    public void onBackPressed() {
        onBackKey();
    }
    
    private void onBackKey() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private static final void applyDefaultConfig(WebView webView) {
        webView.getSettings().setUserAgentString(getDefaultUserAgent(webView.getContext()));
        webView.getSettings().setJavaScriptEnabled(true);
    }
    
    private static final String getDefaultUserAgent(Context context) {
        String platform = Build.MANUFACTURER + " " + Build.MODEL + " " + Build.PRODUCT;
        String os = "Android " + Build.VERSION.RELEASE;
        String webkitPostfix = "AppleWebKit/534.30 (KHTML, like Gecko)";
        
        String userAgent = String.format("%s %s %s", os, platform, webkitPostfix);
        
        return userAgent;
    }
}

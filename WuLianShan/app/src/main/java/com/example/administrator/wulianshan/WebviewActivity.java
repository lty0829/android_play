package com.example.administrator.wulianshan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/8/10.
 */
public class WebviewActivity extends Activity {
    private WebView webView;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewlayout);
        Init();
    }

    private void Init() {
        webView = (WebView) findViewById(R.id.webview);
        // webView.loadUrl("file:///android_asset/example.html");
        webView.loadUrl("http://v.youku.com/v_show/id_XMTY3NzkzNDQ0OA==.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings setting= webView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    closeDialog();
                }
                else{
                    openDialog(newProgress);
                }
            }

            private void openDialog(int newProgress) {

                if(dialog==null){
                    dialog=new ProgressDialog(WebviewActivity.this);
                    dialog.setTitle("正在加载");
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setProgress(newProgress);
                    dialog.show();
                }
                else{
                    dialog.setProgress(newProgress);
                }
            }

            private void closeDialog() {
                if(dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                    dialog=null;
                }
            }
        });
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode==KeyEvent.KEYCODE_BACK){
//
//            if(webView.canGoBack()){
//                webView.goBack();
//                return true;
//            }
//            else{
//                Intent intent =new Intent(WebviewActivity.this,MainPage.class);
//                startActivity(intent);
//                finish();
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
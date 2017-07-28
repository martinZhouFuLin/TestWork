package zltd.com.testwork.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import zltd.com.testwork.BaseScanActivity;
import zltd.com.testwork.R;
import zltd.com.testwork.utils.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends BaseScanActivity {
    private Handler mHandler = new Handler();
    private WebView webView;
    //js文本
    private String wholeJS = "";

    @Override
    public void getScanData(String barcode) {
        // TODO Auto-generated method stub
        mSoundUtils.getInstance().success();
        webView.loadUrl("javascript:jsText('" + barcode + "')");
//        webView.loadUrl("javascript: wave() ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        // 查找WebView
        webView = (WebView) findViewById(R.id.webview);
        // 获取WebView配置
        WebSettings ws = webView.getSettings();
        // 启用JavaScript
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        // 在载入assets目录下的一个页面
//        webView.loadUrl("file:///android_asset/scan.html");
//        webView.loadUrl("http://www.baidu.com");

//	        webView.addJavascriptInterface(new Object(){
//	        	 @JavascriptInterface
//	            public void clickOnAndroid(){
//	                mHandler.post(new Runnable(){
//	                    public void run(){
//	                    	LogUtils.i("debug", "js");
////	                    	webView.loadUrl("javascript:wave'"+text+"')");
//	                        webView.loadUrl("javascript:wave()");
//	                    }
//	                });
//	            }
//	        }, "demo");
        webView.addJavascriptInterface(new JSInterface(), "jsi");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://192.168.0.116:8080/Demo/loginnew.jsp");
        //获取js文本
        InputStream mIs = null;
        try {
            mIs = getResources().getAssets().open("scan.js");
            if(mIs != null){
                byte buff[] = new byte[1024];
                ByteArrayOutputStream fromFile = new ByteArrayOutputStream();
                FileOutputStream out = null;
                do {
                    int numread = 0;
                    numread = mIs.read(buff);
                    if (numread <= 0) {
                        break;
                    }
                    fromFile.write(buff, 0, numread);
                } while (true);
                wholeJS = fromFile.toString();
            }else{
                Toast.makeText(WebActivity.this, "js加载失败", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //webview添加读取的js
//        new Thread(){
//            @Override
//            public void run()
//            {
//                                String html=getHtmlFromInternet("http://192.168.0.116:8080/Demo");
////                LogUtils.Companion.i(""+html);
//                webView.loadDataWithBaseURL("",html ,"text/html", "utf-8", null);
//            }
//        }.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.loadUrl("javascript:" + wholeJS);
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//            if (url.contains("scan.js")) {//加载指定.js时 引导服务端加载本地Assets/www文件夹下的scan.js
            try {
                return new WebResourceResponse("application/x-javascript", "utf-8", getBaseContext().getAssets().open("scan.js"));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            }
            return super.shouldInterceptRequest(view, url);
        }

//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                if (request.getUrl().toString().contains("scan.js")) {//加载指定.js时 引导服务端加载本地Assets/www文件夹下的scan.js
//                    try {
//                        return new WebResourceResponse("application/x-javascript", "utf-8", getBaseContext().getAssets().open("jsr/scan.js"));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            return super.shouldInterceptRequest(view, request);
//        }
    }
    private final class JSInterface {
        /**
         * 注意这里的@JavascriptInterface注解， target是4.2以上都需要添加这个注解，否则无法调用
         *
         * @param text
         */
        @JavascriptInterface
        public void showToast(String text) {
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void showJsText(String text) {
            webView.loadUrl("javascript:jsText('" + text + "')");
        }

        @JavascriptInterface
        public void loadScanJs() {
            webView.loadUrl("javascript:" + wholeJS);
        }
        @JavascriptInterface
        public void clickOnAndroid() {
            mHandler.post(new Runnable() {
                public void run() {
                    LogUtils.Companion.i("debug", "jsr");
                    webView.loadUrl("javascript:wave()");
                }
            });
        }
    }

    /**
     * 根据给定的url访问网络, 抓去html代码
     * @param url
     * @return
     */
    protected String getHtmlFromInternet(String url) {

        try {
            URL mURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) mURL.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(5000);

          conn.connect();

            int responseCode = conn.getResponseCode();
            if(responseCode == 200) {
                InputStream is = conn.getInputStream();
                String html = getStringFromInputStream(is);//把流转换成字符串
                return html;
            } else {
                Log.i("debug", "访问失败: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据流返回一个字符串信息
     * 也就是把流转换成字符串
     * @param is
     * @return
     * @throws IOException
     */
    private String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//字节数组输出流
        byte[] buffer = new byte[1024];
        int len = -1;

        while((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        is.close();

        String html = baos.toString();  // 把流中的数据转换成字符串, 采用的编码是: utf-8

        String charset = "utf-8";
        if(html.contains("gbk") || html.contains("gb2312")
                || html.contains("GBK") || html.contains("GB2312")) {       // 如果包含gbk, gb2312编码, 就采用gbk编码进行对字符串编码
            charset = "gbk";
        }

        html = new String(baos.toByteArray(), charset); // 对原有的字节数组进行使用处理后的编码名称进行编码（打回原形再设置编码）
        baos.close();
        return html;
    }
}
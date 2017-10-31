package io.huyhoang.myfancydooropener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import java.net.URL;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.webkit.WebView;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import android.graphics.Color;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import android.graphics.BitmapFactory;


import java.lang.Boolean.*;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    private ImageView mImageView;
    private ImageView mImageViewInternal;
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.loadUrl("http://huyhoang.io:8000/video_feed");

        final Button GetInfoData = (Button) findViewById(R.id.getinfo);
        GetInfoData.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Create Object and call AsyncTask execute Method
                new RetrieveDistance().execute();
                new RetrieveName().execute();

            }
        });

        final Button Authorization = (Button) findViewById(R.id.authorize);
        Authorization.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Create Object and call AsyncTask execute Method
                new SetAuth().execute();

            }
        });
    }

    private class SetAuth  extends AsyncTask<String, Void, Void> {
        String result;
        String inputLine;

        protected void onPreExecute() {

        }

        protected Void doInBackground(String... urls) {

            try {
                //Create a URL object holding our url
                URL url = new URL("http://huyhoang.io:8000/setauth");
                //Create a connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //Set methods and timeouts
                urlConnection.setRequestMethod(REQUEST_METHOD);
                urlConnection.setReadTimeout(READ_TIMEOUT);
                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);

                urlConnection.connect();

                //Create a new InputStreamReader
                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }

                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute() {

        }
    }


    private class RetrieveName  extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

        }

        protected String doInBackground(String... urls) {
            String result;
            String inputLine;

            try {
                //Create a URL object holding our url
                URL url = new URL("http://huyhoang.io:8000/getname");
                //Create a connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //Set methods and timeouts
                urlConnection.setRequestMethod(REQUEST_METHOD);
                urlConnection.setReadTimeout(READ_TIMEOUT);
                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);

                urlConnection.connect();

                //Create a new InputStreamReader
                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }

                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }

            return result;
        }

        protected void onPostExecute(String returnedVal) {
            TextView txtView = (TextView) findViewById(R.id.person);
            txtView.setTextColor(Color.WHITE);
            txtView.setText(returnedVal);
        }
    }

    private class RetrieveDistance  extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

        }

        protected String doInBackground(String... urls) {
            String result;
            String inputLine;

            try {
                //Create a URL object holding our url
                URL url = new URL("http://huyhoang.io:8000/getdistance");
                //Create a connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //Set methods and timeouts
                urlConnection.setRequestMethod(REQUEST_METHOD);
                urlConnection.setReadTimeout(READ_TIMEOUT);
                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);

                urlConnection.connect();

                //Create a new InputStreamReader
                InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }

                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }

            return result;
        }

        protected void onPostExecute(String returnedVal) {
            TextView txtView = (TextView) findViewById(R.id.distance);
            txtView.setTextColor(Color.WHITE);
            txtView.setText(returnedVal);
        }
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

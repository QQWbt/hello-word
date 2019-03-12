package com.bwie.wu.week.bean;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Auther: 武丙涛
 * @Date: 2019/1/9 20:34:08
 * @Description:
 */
public class HttpUtils  {
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {

            ConnectivityManager mConnectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    public static void httpAsynTask(String strUrl, final CallBackString backString) {
        new AsyncTask<String, Integer, String> () {
            @Override
            protected String doInBackground(String... strings) {
                return httpGet(strings[0]);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                backString.getData(s);
            }
        }.execute(strUrl);
    }

    public interface CallBackString {
        void getData(String s);
    }

    public static String httpGet(String strUrl) {

        try {
            URL url = new URL(strUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader (stream));

            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
            connection.disconnect();

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

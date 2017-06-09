package com.example.bqt.myapp.Parse;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by BQT on 5/31/2017.
 */

public class JSONParser extends AsyncTask<String, Void, String> {
    String duongdan;
    List<HashMap<String, String>> attrs;
    StringBuilder dulieu;
    String jsonData;
    boolean method = true;
    OkHttpClient client = new OkHttpClient();

    public JSONParser(String duongdan) {
        this.duongdan = duongdan;
        method = true;
    }

    public JSONParser(String duongdan, List<HashMap<String, String>> attrs) {
        this.duongdan = duongdan;
        this.attrs = attrs;
        method = false;
    }

    @Override
    protected String doInBackground(String... params) {
        String data = "";
        try {
            URL url = new URL(duongdan);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (!method) {
                data = methodPost(httpURLConnection);
            } else {
                data = methodGet(httpURLConnection);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("dulieu", data);
        return data;
    }

    private String methodGet(HttpURLConnection httpURLConnection) {
        String data = "";
        InputStream inputStream = null;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            dulieu = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                dulieu.append(line);
            }

            data = dulieu.toString();
            bufferedReader.close();
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private String methodPost(HttpURLConnection httpURLConnection) {
        String data = "";
        String key = "";
        String value = "";

        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();

            int count = attrs.size();
            for (int i = 0; i < count; i++) {

                for (Map.Entry<String, String> values : attrs.get(i).entrySet()) {
                    key = values.getKey();
                    value = values.getValue();
                }

                builder.appendQueryParameter(key, value);
            }
            String query = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);

            writer.write(query);
            writer.flush();
            writer.close();
            streamWriter.close();
            outputStream.close();

            data = methodGet(httpURLConnection);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


}

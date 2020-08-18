package com.example.httpconnectiongetapi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPConnection extends AsyncTask<String, Void, String> {


    private String str, receiveMsg;
    String player = "";

    public HTTPConnection(String player) {
        this.player = player;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" +
                    player +
                    "?api_key=RGAPI-3b1f8b63-1512-4404-b9e9-6b147f588914");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // 통신이 성공했을 경우
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}

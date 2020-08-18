package com.example.httpconnectiongetapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private String resultText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void HTTPRequest(final String playerID, final Context context) {
        try {
            resultText="";
            //string 값을 저장.
            resultText = new HTTPConnection(playerID).execute().get();
            // JSON 으로 변형
                getJson(context);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void getJson(Context context){
        try {
            JSONObject object = new JSONObject(resultText);
            String id;
            String profileIconID;
            String playerID;
            id = object.getString("id");
            profileIconID = object.getString("profileIconId");
            playerID=object.getString("name");

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
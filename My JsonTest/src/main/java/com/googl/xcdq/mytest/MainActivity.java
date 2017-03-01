package com.googl.xcdq.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private String jsonData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
private List<NewsInfo> jsonParse(String jsonData){
    List<NewsInfo> data=new ArrayList<>();
    try {
        JSONObject object1=new JSONObject(jsonData);
        if (object1.getString("reason").equals("成功的返回")) {
            JSONObject object2 = object1.getJSONObject("result");
            JSONArray array = object2.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.title = o.getString("title");
                data.add(newsInfo);
            }
            return data;
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return null;
}
}

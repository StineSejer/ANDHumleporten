package com.example.humleporten.Schedule;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataHelp {

    public static ArrayList<Days> loadDays(Context context) {
        ArrayList<Days> days = new ArrayList<>();
        String json = "BrewSchedule.json";

        try {
            InputStream is =  context.getAssets().open("BrewSchedule.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("BrewSchedule");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Days day = new Days();
                day.setTitle(jsonObject.getString("title"));
                day.setHumleporten(jsonObject.getString("humleporten"));

                days.add(day);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return days;


    }
}

package com.example.tacademy.samplemelon1;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Tacademy on 2016-02-04.
 */
public class Artist implements JSONParseHandler{

    int artistId;
    String artistName;

    @Override
    public void setData(JSONObject jsonObject) {
        artistId = jsonObject.optInt("artistId");
        artistName = jsonObject.optString("artistName");
    }
}

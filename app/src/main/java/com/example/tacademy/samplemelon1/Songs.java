package com.example.tacademy.samplemelon1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-02-04.
 */
public class Songs implements JSONParseHandler{

    ArrayList<Song> song;

    @Override
    public void setData(JSONObject jsonObject) {
        JSONArray jsong = jsonObject.optJSONArray("song");
        song = new ArrayList<Song>();

        for(int i =0 ; i<jsong.length(); i++){
            JSONObject js = jsong.optJSONObject(i);
            Song s = new Song();
            s.setData(js);
            song.add(s);
        }
    }
}

package com.example.tacademy.samplemelon1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-02-04.
 */
public class Artists implements JSONParseHandler{

    ArrayList<Artist> artist;

    @Override
    public void setData(JSONObject jsonObject) {
        JSONArray jartist = jsonObject.optJSONArray("artist");
        artist = new ArrayList<Artist>();
        for (int i =0; i<jartist.length(); i++){
            JSONObject jitem = jartist.optJSONObject(i);
            Artist art = new Artist();
            art.setData(jitem);
            artist.add(art);
        }
    }
}

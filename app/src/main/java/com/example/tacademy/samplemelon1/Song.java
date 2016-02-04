package com.example.tacademy.samplemelon1;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-02-04.
 */
public class Song implements JSONParseHandler{
    int songId;
    String songName;
    Artists artists;
    String albumName;
    int currentRank;

    @Override
    public void setData(JSONObject jsonObject) {
        songId = jsonObject.optInt("songId");
        songName = jsonObject.optString("songName");
        albumName = jsonObject.optString("albumName");
        currentRank = jsonObject.optInt("currentRank");
        JSONObject jartists = jsonObject.optJSONObject("artists");
        artists = new Artists();
        artists.setData(jartists);
    }

    @Override
    public String toString() {
        return "[" + currentRank + "]" + songName + "\n(" + albumName + ")";

    }
}

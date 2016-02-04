package com.example.tacademy.samplemelon1;

import org.json.JSONObject;

/**
 * Created by Tacademy on 2016-02-04.
 */
public class Melon implements JSONParseHandler{

    int menuId;
    int count;
    int page;
    int totalPages;
    Songs songs;

    @Override
    public void setData(JSONObject jsonObject) {
        menuId = jsonObject.optInt("menuId");
        count = jsonObject.optInt("count");
        page = jsonObject.optInt("page");
        totalPages = jsonObject.optInt("totalPages");
        JSONObject jsongs = jsonObject.optJSONObject("songs"); // json으로 songs 를 가지고 오고
        songs = new Songs();
        songs.setData(jsongs);
    }
}

package com.example.tacademy.samplemelon1;

import org.json.JSONObject;

/**
 * Created by Tacademy on 2016-02-04.
 */
public class MelonData implements JSONParseHandler{


    Melon melon;

    @Override
    public void setData(JSONObject jsonObject) {
        JSONObject jmelon = jsonObject.optJSONObject("melon");
        melon = new Melon();
        melon.setData(jmelon);
    }
}

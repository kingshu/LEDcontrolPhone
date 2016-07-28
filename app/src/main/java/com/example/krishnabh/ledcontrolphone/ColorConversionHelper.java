package com.example.krishnabh.ledcontrolphone;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

/**
 * Created by Krishnabh on 6/25/2016.
 */
public class ColorConversionHelper {
    public static int getIntFromRGB(JSONObject rgb){
        int color = 0;
        try {
            color = Color.rgb(rgb.getInt("red"),rgb.getInt("green"),rgb.getInt("blue"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return color;
    }


}

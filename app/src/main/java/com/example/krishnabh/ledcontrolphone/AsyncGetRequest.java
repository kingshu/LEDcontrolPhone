package com.example.krishnabh.ledcontrolphone;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Krishnabh on 6/25/2016.
 */
public class AsyncGetRequest extends AsyncTask<Object, Integer, JSONObject> {

    private final CONSTANTS.RequestType requestType;
    private final String url;
    MainActivity mainActivity;

    public AsyncGetRequest(CONSTANTS.RequestType mRequestType) {
        requestType = mRequestType;
        switch (mRequestType) {
            case GET_STATE:
                url =  CONSTANTS.PI_URL + CONSTANTS.EP_GET;
                break;
            case TURN_ON:
                url = CONSTANTS.PI_URL + CONSTANTS.EP_ON;
                break;
            case TURN_OFF:
                url = CONSTANTS.PI_URL + CONSTANTS.EP_OFF;
                break;
            case FLICKER:
                url = CONSTANTS.PI_URL + CONSTANTS.EP_FLICKER;
                break;
            default:
                url = "";
        }
    }
    public AsyncGetRequest(CONSTANTS.RequestType mRequestType, int red, int green, int blue) {
        requestType = mRequestType;
        url = CONSTANTS.PI_URL + CONSTANTS.EP_SET + red +"/"+ green +"/"+ blue;
    }

    @Override
    protected JSONObject doInBackground(Object... x) {
        OkHttpClient client = new OkHttpClient();
        Response response = null;
        try {
            System.out.println("~~~~~~ "+url);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            response = client.newCall(request).execute();
            JSONObject respJSON = new JSONObject(response.body().string());
            return respJSON;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            response.body().close();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject respJSON) {
        switch (requestType) {
            case TURN_ON:
                mainActivity.changeColorBtn.setVisibility(View.VISIBLE);
            case GET_STATE:
                mainActivity.colorRect.setColorFilter(ColorConversionHelper.getIntFromRGB(respJSON));
                try {
                    mainActivity.powerSwitch.setChecked(
                            respJSON.getString("power").equals("on")
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case TURN_OFF:
                mainActivity.changeColorBtn.setVisibility(View.INVISIBLE);
                mainActivity.colorRect.setColorFilter(0xff000000);
                break;
        }
    }


}
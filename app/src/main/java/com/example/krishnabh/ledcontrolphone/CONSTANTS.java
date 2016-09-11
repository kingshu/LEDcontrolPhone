package com.example.krishnabh.ledcontrolphone;

/**
 * Created by Krishnabh on 6/20/2016.
 */
public class CONSTANTS {
    public static final String PI_URL = "http://f0c4a9b1.ngrok.io/";
    public static final String EP_SET = "setstate/";
    public static final String EP_GET = "getstate/";
    public static final String EP_OFF = "turnoff/";
    public static final String EP_ON = "turnon/";
    public static final String EP_FLICKER = "flicker/";

    public enum RequestType {
        GET_STATE, SET_STATE, TURN_ON, TURN_OFF, FLICKER, PULSE
    }
}

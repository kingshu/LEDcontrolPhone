package com.example.krishnabh.ledcontrolphone;

import android.content.Context;

import java.util.Date;

/**
 * Created by Krishnabh on 8/1/2016.
 */
public class CallListener extends PhoneCallListener {

    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start)
    {
        new AsyncGetRequest(CONSTANTS.RequestType.FLICKER).execute();
    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start)
    {
        //
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {
        //
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start)
    {
        //
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end)
    {
        //
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start)
    {
        //
    }

}
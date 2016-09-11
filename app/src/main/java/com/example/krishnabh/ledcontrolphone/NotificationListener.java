package com.example.krishnabh.ledcontrolphone;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/**
 * Created by Krishnabh on 8/1/2016.
 */
public class NotificationListener extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        System.out.println("!!!!!!!!!NOTIFICATIUON!!!!!!");
        System.out.println(sbn.getPackageName());
        System.out.println(sbn.isClearable());
    }

}

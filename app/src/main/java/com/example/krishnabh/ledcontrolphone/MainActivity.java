package com.example.krishnabh.ledcontrolphone;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.service.notification.StatusBarNotification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import yuku.ambilwarna.AmbilWarnaDialog;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

/*
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.location.LocationServices;
*/

public class MainActivity extends AppCompatActivity implements Switch.OnCheckedChangeListener {

    public static int color = 0xff000000;
    public static Button changeColorBtn;
    public static Switch powerSwitch;
    public static ImageView colorRect;
    public static Button settingsBtn;

    private NotificationListener nl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorRect = (ImageView) findViewById(R.id.fadedbg);

        CallListener cl = new CallListener();
        nl = new NotificationListener() {
            @Override
            public void onListenerConnected() {
                StatusBarNotification sbns[] = this.getActiveNotifications();
                for (StatusBarNotification i: sbns) {
                    System.out.println(i.getPackageName());
                }
            }
        };


        powerSwitch = (Switch) findViewById(R.id.switch1);
        powerSwitch.setOnCheckedChangeListener(this);

        changeColorBtn = (Button) findViewById(R.id.button);
        changeColorBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openColorPickerDialog(false);
            }
        });

        new AsyncGetRequest(CONSTANTS.RequestType.GET_STATE).execute();

        settingsBtn = (Button) findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openSettingsView();
            }
        });
    }

    public void openSettingsView() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            new AsyncGetRequest(CONSTANTS.RequestType.TURN_ON).execute();
        }
        else {
            new AsyncGetRequest(CONSTANTS.RequestType.TURN_OFF).execute();
        }
    }

    public void openColorPickerDialog(boolean supportsAlpha) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(MainActivity.this, color, supportsAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int newColor) {
                color = newColor;
                colorRect.setColorFilter(color);
                new AsyncGetRequest(CONSTANTS.RequestType.SET_STATE, Color.red(color), Color.green(color), Color.blue(color)).execute();
            }
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                System.out.println("Cancelled");
            }
        });
        dialog.show();
    }

}

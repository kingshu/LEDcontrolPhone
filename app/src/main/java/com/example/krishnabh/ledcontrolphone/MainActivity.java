package com.example.krishnabh.ledcontrolphone;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yuku.ambilwarna.AmbilWarnaDialog;
import android.os.AsyncTask;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener, Switch.OnCheckedChangeListener {

    private static int color = 0xff000000;
    private static Button changeColorBtn;
    private static Switch powerSwitch;
    private static ImageView colorRect;
    private static final String PI_URL = "http://10.0.0.174:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color = 0xff000000;

        colorRect = (ImageView) findViewById(R.id.fadedbg);

        powerSwitch = (Switch) findViewById(R.id.switch1);
        powerSwitch.setOnCheckedChangeListener(this);

        changeColorBtn = (Button) findViewById(R.id.button);
        changeColorBtn.setOnClickListener(this);
    }

    void openColorPickerDialog(boolean supportsAlpha) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(MainActivity.this, MainActivity.this.color, supportsAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                MainActivity.this.color = color;
                System.out.println(String.format("COLLLLLLOR: : 0x%08x", color));
                colorRect.setColorFilter(color);
                //new RequestTask(color).execute();
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                System.out.println("Cancelled");
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        openColorPickerDialog(false);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            //new RequestTask(MainActivity.this.color).execute();
            colorRect.setColorFilter(MainActivity.this.color);
            changeColorBtn.setVisibility(View.VISIBLE);

        }
        else {
            //new RequestTask(0xff000000).execute();
            colorRect.setColorFilter(0xff000000);
            changeColorBtn.setVisibility(View.INVISIBLE);
        }
    }

    private class RequestTask extends AsyncTask {

        private final int color2change;

        public RequestTask(int mColor) {
            color2change = mColor;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            OkHttpClient client = new OkHttpClient();
            System.out.println(PI_URL + Color.red(color2change) + "/" + Color.green(color2change) + "/" + Color.blue(color2change));
            try {
                Request request = new Request.Builder()
                        .url(PI_URL + Color.red(color2change) + "/" + Color.green(color2change) + "/" + Color.blue(color2change))
                        .build();
                Response response = client.newCall(request).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

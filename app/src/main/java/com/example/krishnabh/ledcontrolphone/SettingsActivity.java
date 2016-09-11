package com.example.krishnabh.ledcontrolphone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    public static Button saveBtn;
    public static EditText serverUrlInput;
    public final String PREFS_NAME = getString(R.string.settings_file_name);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        serverUrlInput = (EditText) findViewById(R.id.serverUrlInput);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                //SharedPreferences.Editor editor = settings.edit();
                String serverUrl = serverUrlInput.getText().toString();
                System.out.println(serverUrl);
                //editor.putString("serverUrl", serverUrl);
                //editor.commit();
                //startActivity(getIntent());
            }
        });
    }
}

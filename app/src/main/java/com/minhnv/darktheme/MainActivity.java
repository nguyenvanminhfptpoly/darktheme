package com.minhnv.darktheme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Switch mSwitch1;
    /**
     * Button
     */
    private Button mButton;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String sw = "sahre";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        mSwitch1 = findViewById(R.id.switch1);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mSwitch1.setChecked(sharedPreferences.getBoolean(sw,false));
        if(mSwitch1.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        mSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean(sw,true);
                    restart();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restart();
                    editor.putBoolean(sw,false);
                }
                editor.apply();
            }
        });

    }

    private void restart() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }
    }
}

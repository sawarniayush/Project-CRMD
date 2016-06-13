package com.example.android.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = (TextView) findViewById(R.id.welcome_txt);
        String message = getIntent().getStringExtra("message");
        textView.setText(message);



    }

    public void clicked(View v)
    {
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
    }
}

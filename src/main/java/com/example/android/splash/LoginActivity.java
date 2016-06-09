package com.example.android.splash;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    TextView signup_text;
    EditText Username, Pass;
    Button login_button;
    AlertDialog.Builder builder;
    String imei="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup_text = (TextView) findViewById(R.id.sign_up);
        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        Username = (EditText) findViewById(R.id.username_login);
        Pass = (EditText) findViewById(R.id.password_login);
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Username.getText().toString().equals("") || Pass.getText().toString().equals("")) {
                    builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Something went wrong");
                    builder.setMessage("Please fill both the fields");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else
                {
                    BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this);
                    TelephonyManager tm=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    imei=tm.getDeviceId();
                    /*
                     * AsyncTask has the form AsyncTask<Param, Progress, Return> with execute using the Param type.
                     * so here we are sending the @Params to the Background task with all the parameters as strings
                     */

                    backgroundTask.execute("login", Username.getText().toString(), Pass.getText().toString(),imei);
                    /*
                     * here login is arg[0], Username.getText().toString() is arg[1] and so on.
                     */

                }

            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}



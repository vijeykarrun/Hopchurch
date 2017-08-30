package com.hopchurch.godsgift;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.hopchurch.utilfunctions.UtilClass;



public class MainActivity extends Activity {

    boolean backexit = false;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        getApplicationContext();
        if (!UtilClass.isEmpty(getSharedPreferences("god'gift", 0).getString("sessionusername", ""))) {
            startActivity(new Intent(this, ScrollingActivity.class));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn(View view) {
        startActivity(new Intent(this, SignIn.class));
    }

    public void register(View v) {
        startActivity(new Intent(this, SignUp.class));
    }
}

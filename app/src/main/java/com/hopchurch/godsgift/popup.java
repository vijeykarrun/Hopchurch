package com.hopchurch.godsgift;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.hopchurch.utilfunctions.UtilClass;

public class popup extends Activity {
    String Week;
    Button check;
    int finalweek = 0;
    int fromweek = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getIntent() == null || getIntent().getExtras() == null)) {
            this.fromweek = getIntent().getExtras().getInt("fromweek");
        }
        setContentView(com.hopchurch.godsgift.R.layout.activity_popup);
        for (int i = 1; i <= 40; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            if (i > this.fromweek && this.fromweek > 0) {
                Log.d("iiii", i + "");
                Log.d("id", id + "");
                ((Button) findViewById(id)).setEnabled(false);
            }
        }
    }

    public void weekclick(View v) {
        Resources res = getResources();
        this.Week = ((Button) v).getText().toString();
        if (!UtilClass.isEmpty(this.Week)) {
            this.finalweek = Integer.parseInt(this.Week);
        }
        Intent intent = new Intent(this, ScrollingActivity.class);
        intent.putExtra("popupweek", this.finalweek);
        startActivity(intent);
    }

    public void goBack(View V) {
        startActivity(new Intent(this, ScrollingActivity.class));
    }
}

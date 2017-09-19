package com.hopchurch.godsgift;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.hopchurch.Application.MyApplicaionClass;
import com.hopchurch.staticstring.StaticValues;
import com.hopchurch.utilfunctions.UtilClass;
import java.util.Date;

public class ScrollingActivity extends AppCompatActivity {
    String Maincontent = null;
    Date Registerdate = new Date();
    int Registerweek = 0;
    boolean backexit = false;
    TextView content;
    int finalWeek = 40;
    int finalweek = 0;
    Button getWeek;
    int iniWeek = 1;
    String parent = null;
    String prayerRequest = StaticValues.prayerrequest;
    int week = 1;

    class C01921 implements Runnable {
        C01921() {
        }

        public void run() {
            ScrollingActivity.this.backexit = false;
        }
    }

    class C01932 extends ClickableSpan {
        C01932() {
        }

        public void onClick(View textView) {
            ScrollingActivity.this.createPopUp();
        }

        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

    class C01954 implements OnClickListener {
        C01954() {
        }

        public void onClick(View view) {
            String shareBody = UtilClass.getWeekValuePrayerNew(ScrollingActivity.this.week);
            Intent sharingIntent = new Intent("android.intent.action.SEND");
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra("android.intent.extra.SUBJECT", "Message from Child");
            sharingIntent.putExtra("android.intent.extra.TEXT", shareBody);
            ScrollingActivity.this.startActivity(Intent.createChooser(sharingIntent, "send"));
        }
    }

    public void onBackPressed() {
        if (this.backexit) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            startActivity(intent);
        }
        this.backexit = true;
        Toast.makeText(this, "Please click back again to exit ", 0).show();
        new Handler().postDelayed(new C01921(), 2000);
    }

    public void logout(View V) {
        getApplicationContext();
        Editor editor = getSharedPreferences("god'gift", 0).edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(67108864);
        startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) com.hopchurch.godsgift.R.layout.activity_scrolling);
        int popupweek = 0;
        getApplicationContext();
        SharedPreferences sharedPreferences = getSharedPreferences("god'gift", 0);
        Editor editor = sharedPreferences.edit();
        this.Registerweek = sharedPreferences.getInt("sessionweek", this.Registerweek);
        this.parent = sharedPreferences.getString("sessionparent", null);
        if (this.Registerweek != 0) {
            this.week = this.Registerweek;
        }
        this.Registerdate = UtilClass.getDatefromString(sharedPreferences.getString("sessionregister", ""));
        this.week = UtilClass.noOfSundays(this.Registerdate) + 1;
        this.finalweek = this.week;
        if (!(getIntent() == null || getIntent().getExtras() == null || getIntent().getExtras().getInt("popupweek") == 0)) {
            popupweek = getIntent().getExtras().getInt("popupweek");
        }
        if (null != null) {
            this.week = 0;
        } else if (popupweek != 0) {
            this.week = popupweek;
        }
        this.getWeek = (Button) findViewById(com.hopchurch.godsgift.R.id.getweek);
        this.getWeek.setText(StaticValues.week + this.week);
        this.content = (TextView) findViewById(com.hopchurch.godsgift.R.id.maincontent);
        this.Maincontent = UtilClass.getWeekValue(this.week, this.parent);
        setMainContent(this.Maincontent);
    }

    public void getWeek(View v) {
        Intent intent = new Intent(this, popup.class);
        intent.putExtra("fromweek", this.finalweek);
        startActivity(intent);
    }

    public void next(View v) {
        if (this.week == this.finalWeek || this.week == this.finalweek) {
            Toast.makeText(this, "Not too fast. Wait for your time", 1).show();
            return;
        }
        int next = this.week + 1;
        this.Maincontent = UtilClass.getWeekValue(next, this.parent);
        setMainContent(this.Maincontent);
        this.week = next;
        this.getWeek.setText("week " + this.week);
    }

    public void previous(View v) {
        if (this.week == this.iniWeek) {
            Toast.makeText(this, "The story is yet to begin", 1).show();
            return;
        }
        int previous = this.week - 1;
        this.Maincontent = UtilClass.getWeekValue(previous, this.parent);
        setMainContent(this.Maincontent);
        this.week = previous;
        this.getWeek.setText("week " + this.week);
    }

    private void setMainContent(String main) {
        SpannableString ss = new SpannableString(UtilClass.fromHtml(main));
        ClickableSpan clickableSpan = new C01932();
        if (ss.toString().lastIndexOf("click here for prayer for the week") > -1) {
            ss.setSpan(clickableSpan, ss.toString().lastIndexOf("click here for prayer for the week"), ss.length(), 33);
        }
        this.content.setText(ss);
        this.content.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void createPopUp() {
        View customView = ((LayoutInflater) MyApplicaionClass.getContext().getSystemService("layout_inflater")).inflate(com.hopchurch.godsgift.R.layout.activity_prayer__popup, null);
        CoordinatorLayout mRelativeLayout = (CoordinatorLayout) findViewById(com.hopchurch.godsgift.R.id.cr);
        final PopupWindow mPopupWindow = new PopupWindow(customView, -2, -2);
        TextView prayer = (TextView) customView.findViewById(com.hopchurch.godsgift.R.id.tv);
        mPopupWindow.showAtLocation(mRelativeLayout, 17, 0, 0);
        String prayerContent = UtilClass.getWeekValuePrayer(this.week);
        if (!UtilClass.isEmpty(prayerContent)) {
            prayer.setText(UtilClass.fromHtml(prayerContent + ""));
        }
        Button shareButton = (Button) customView.findViewById(com.hopchurch.godsgift.R.id.Share);
        ((Button) customView.findViewById(com.hopchurch.godsgift.R.id.ib_close)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        shareButton.setOnClickListener(new C01954());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.hopchurch.godsgift.R.menu.menu_scrolling, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == com.hopchurch.godsgift.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //chat module

    public void sendMessage(View v) {

        String toNumber = "+91 73586 20382";
        toNumber = toNumber.replace("+", "").replace(" ", "");

        Intent sendIntent = new Intent();
        sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");

        sendIntent.setPackage("com.whatsapp");

        startActivity(sendIntent);

    }

}

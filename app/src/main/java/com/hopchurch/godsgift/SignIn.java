package com.hopchurch.godsgift;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hopchurch.DAO.Register;
import com.hopchurch.Pojo.Person;
import com.hopchurch.utilfunctions.UtilClass;

public class SignIn extends Activity {
    Button Login;
    EditText password;
    EditText username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.hopchurch.godsgift.R.layout.activity_sign_in);
        this.username = (EditText) findViewById(com.hopchurch.godsgift.R.id.username);
        this.password = (EditText) findViewById(com.hopchurch.godsgift.R.id.passwordlogin);
        this.username.getBackground().setColorFilter(-7829368, Mode.SRC_ATOP);
        this.password.getBackground().setColorFilter(-7829368, Mode.SRC_ATOP);
        this.Login = (Button) findViewById(com.hopchurch.godsgift.R.id.login);
        final Intent intent = new Intent(this, ScrollingActivity.class);
        this.Login.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String userText = SignIn.this.username.getText().toString();
                String confirmPassword = SignIn.this.password.getText().toString();
                if (userText.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(SignIn.this.getApplicationContext(), "Please Enter all the fields", 1).show();
                    return;
                }
                Person loginValidate = new Register(SignIn.this.getApplicationContext()).loginValidate(userText, confirmPassword);
                if (loginValidate != null) {
                    intent.putExtra("name", loginValidate.getName());
                    intent.putExtra("parent", loginValidate.getParent());
                    intent.putExtra("weekregister", loginValidate.getWeek());
                    SignIn.this.getApplicationContext();
                    Editor editor = SignIn.this.getSharedPreferences("god'gift", 0).edit();
                    editor.putString("sessionusername", loginValidate.getUserName());
                    editor.putInt("sessionweek", loginValidate.getWeek());
                    editor.putString("sessionparent", loginValidate.getParent());
                    editor.putString("sessionregister", UtilClass.getStrinGDate(loginValidate.getRegisterdate()));
                    editor.commit();
                    SignIn.this.startActivity(intent);
                    return;
                }
                Toast.makeText(SignIn.this.getApplicationContext(), "login failed", 1).show();
            }
        });
    }
}

package com.hopchurch.godsgift;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.hopchurch.Application.MyApplicaionClass;
import com.hopchurch.DAO.Register;
import com.hopchurch.DBfunctions.LoginDataBaseAdapter;
import com.hopchurch.Pojo.Person;
import com.hopchurch.staticstring.StaticValues;
import com.hopchurch.utilfunctions.UtilClass;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class SignUp extends Activity {
    TextView LMP;
    EditText Name;
    Button Register;
    OnDateSetListener date = new C01993();
    String fomartdate = null;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Calendar myCalendar = Calendar.getInstance();
    EditText password;
    RadioGroup radioGroup;
    RadioButton radioParent;
    TextView spinner;
    EditText username;

    class C01971 implements OnClickListener {
        C01971() {
        }

        public void onClick(View v) {
            DatePickerDialog mDatePicker = new DatePickerDialog(SignUp.this, SignUp.this.date, SignUp.this.myCalendar.get(1), SignUp.this.myCalendar.get(2), SignUp.this.myCalendar.get(5));
            mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
            mDatePicker.show();
        }
    }

    class C01982 implements OnClickListener {
        C01982() {
        }

        public void onClick(View v) {
            String nameText = SignUp.this.Name.getText().toString();
            String userText = SignUp.this.username.getText().toString();
            String confirmPassword = SignUp.this.password.getText().toString();
            boolean emailValidation = Patterns.EMAIL_ADDRESS.matcher(userText).matches();
            SignUp.this.spinner = (TextView) SignUp.this.findViewById(com.hopchurch.godsgift.R.id.spinnermonth);
            String spinnerMonth = SignUp.this.spinner.getText().toString();
            String lmpdate = SignUp.this.LMP.getText().toString();
            SignUp.this.radioGroup = (RadioGroup) SignUp.this.findViewById(com.hopchurch.godsgift.R.id.parent);
            int selectedId = SignUp.this.radioGroup.getCheckedRadioButtonId();
            SignUp.this.radioParent = (RadioButton) SignUp.this.findViewById(selectedId);
            String radioselect = SignUp.this.radioParent.getText().toString();
            if (!nameText.equalsIgnoreCase("")) {
                if (!(userText.equalsIgnoreCase("") || confirmPassword.equalsIgnoreCase(""))) {
                    if (!spinnerMonth.equalsIgnoreCase("")) {
                        if (!emailValidation) {
                            SignUp.this.username.setError("Field should be a valid Email address.");
                            Toast.makeText(SignUp.this.getApplicationContext(), "Please Enter the username as valid Email address ", 1).show();
                            return;
                        } else if ((UtilClass.isEmpty(spinnerMonth) || !spinnerMonth.trim().equalsIgnoreCase(StaticValues.defaultweek)) && (UtilClass.isEmpty(lmpdate) || !lmpdate.trim().equalsIgnoreCase(StaticValues.defaultlmpdate))) {
                            Register register = new Register(SignUp.this.getApplicationContext());
                            Person person = new Person();
                            person.setName(nameText);
                            person.setUserName(userText);
                            person.setPassword(confirmPassword);
                            person.setWeek(Integer.parseInt(spinnerMonth));
                            person.setParent(radioselect);
                            person.setRegisterdate(UtilClass.getDatefromString(SignUp.this.fomartdate));
                            String check = register.insertEntry(person);
                            if (check.equalsIgnoreCase("success")) {
                                Intent intsuccess = new Intent(MyApplicaionClass.getContext(), MainActivity.class);
                                Toast.makeText(SignUp.this.getApplicationContext(), "Account Successfully Created ", 1).show();
                                Intent notificationIntent = new Intent(MyApplicaionClass.getContext(), NotificationPublisher.class);
                                Log.d("notify1", check);
                                PendingIntent pendingIntent = PendingIntent.getBroadcast(MyApplicaionClass.getContext(), 0, notificationIntent, 134217728);
                                AlarmManager alarmManager = (AlarmManager) SignUp.this.getSystemService("alarm");
                                Calendar notify = new GregorianCalendar();
                                notify.setTime(UtilClass.getLnitialDate(UtilClass.getDatefromString(SignUp.this.fomartdate)));
                                notify.set(11, 0);
                                notify.set(12, 0);
                                notify.set(13, 0);
                                Log.d("notify2", notify.getTime() + "");
                                if (notify.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
                                    notify.add(5, 7);
                                    notify.set(11, 0);
                                    notify.set(12, 0);
                                    notify.set(13, 0);
                                }
                                alarmManager.setRepeating(0, notify.getTimeInMillis(), 86400000 * 7, pendingIntent);
                                SignUp.this.startActivity(intsuccess);
                                return;
                            } else if (check.equalsIgnoreCase("Existed")) {
                                Toast.makeText(SignUp.this.getApplicationContext(), "User already exists ", 1).show();
                                return;
                            } else {
                                return;
                            }
                        } else {
                            Toast.makeText(SignUp.this.getApplicationContext(), "Kindly fill LMP to proceed", 1).show();
                            return;
                        }
                    }
                }
            }
            Toast.makeText(SignUp.this.getApplicationContext(), "Please Enter all the fields", 1).show();
        }
    }

    class C01993 implements OnDateSetListener {
        C01993() {
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            SignUp.this.myCalendar.set(1, year);
            SignUp.this.myCalendar.set(2, monthOfYear);
            SignUp.this.myCalendar.set(5, dayOfMonth);
            SignUp.this.updateLabel();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.hopchurch.godsgift.R.layout.activity_sign_up);
        this.Name = (EditText) findViewById(com.hopchurch.godsgift.R.id.namesignup);
        this.username = (EditText) findViewById(com.hopchurch.godsgift.R.id.username);
        this.password = (EditText) findViewById(com.hopchurch.godsgift.R.id.password);
        this.username.getBackground().setColorFilter(-7829368, Mode.SRC_ATOP);
        this.password.getBackground().setColorFilter(-7829368, Mode.SRC_ATOP);
        this.Name.getBackground().setColorFilter(-7829368, Mode.SRC_ATOP);
        this.LMP = (TextView) findViewById(com.hopchurch.godsgift.R.id.LMPdate);
        this.LMP.setOnClickListener(new C01971());
        this.spinner = (TextView) findViewById(com.hopchurch.godsgift.R.id.spinnermonth);
        String spinnerMonth1 = this.spinner.getText().toString();
        String lmpdate = this.LMP.getText().toString();
        this.Register = (Button) findViewById(com.hopchurch.godsgift.R.id.register);
        this.Register.setOnClickListener(new C01982());
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void updateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        this.LMP.setText(sdf.format(this.myCalendar.getTime()));
        int Week = 1 + UtilClass.noOfSundays(this.myCalendar.getTime());
        if (Week > 40) {
            Toast.makeText(getApplicationContext(), "week should be less or equal to 40 weeks", 1).show();
            this.spinner.setText(StaticValues.defaultweek);
            this.LMP.setText(StaticValues.defaultlmpdate);
        }
        this.fomartdate = sdf1.format(this.myCalendar.getTime());
        this.spinner.setText(Week + "");
    }
}

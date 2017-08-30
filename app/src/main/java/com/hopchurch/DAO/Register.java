package com.hopchurch.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hopchurch.DBfunctions.LoginDataBaseAdapter;
import com.hopchurch.Pojo.Person;
import com.hopchurch.utilfunctions.UtilClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register {
    public static final String Table_CREATE = "create table USERROLE( ID integer primary key autoincrement,Name text, USERNAME  text,PASSWORD text,Parent text,Month integer,Createddate Date,BabyName text,partnerName text,familyName text ); ";
    private final Context context;
    SQLiteDatabase db = null;
    LoginDataBaseAdapter loginDataBaseAdapter = null;

    public Register(Context _context) {
        this.context = _context;
        this.loginDataBaseAdapter = new LoginDataBaseAdapter(this.context, Table_CREATE);
        this.db = this.loginDataBaseAdapter.open();
    }

    public String insertEntry(Person person) {
        String isUser = checkUser(person.getUserName());
        if (isUser.equalsIgnoreCase("new")) {
            ContentValues newValues = new ContentValues();
            newValues.put("NAME", person.getName());
            newValues.put("USERNAME", person.getUserName());
            newValues.put("PASSWORD", person.getPassword());
            newValues.put("Parent", person.getParent());
            newValues.put("Month", Integer.valueOf(person.getWeek()));
            Log.d("dattttt", UtilClass.getStrinGDate(person.getRegisterdate()));
            newValues.put("Createddate", UtilClass.getStrinGDate(person.getRegisterdate()));
            newValues.put("BabyName", person.getBabyName());
            newValues.put("partnerName", person.getPartnerName());
            newValues.put("familyName", person.getFamilyName());
            this.db.insert("USERROLE", null, newValues);
            return "success";
        } else if (isUser.equalsIgnoreCase("Existed")) {
            return "Existed";
        } else {
            return null;
        }
    }

    public String checkUser(String userName) {
        Cursor cursor = this.db.query("USERROLE", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() >= 1) {
            return "Existed";
        }
        cursor.close();
        return "new";
    }

    public Person loginValidate(String userName, String Password) {
        Cursor cursor = this.db.query("USERROLE", null, " USERNAME=?", new String[]{userName}, null, null, null);
        Person loginValidate = null;
        Log.d("coming first", "checking");
        if (cursor.getCount() < 1) {
            cursor.close();
            Log.d("coming fsecondt", "second");
        } else {
            cursor.moveToFirst();
            String DBUserName = cursor.getString(cursor.getColumnIndex("USERNAME"));
            String DBPassword = cursor.getString(cursor.getColumnIndex("PASSWORD"));
            if (userName.equalsIgnoreCase(DBUserName) && DBPassword.equalsIgnoreCase(Password)) {
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                String Parent = cursor.getString(cursor.getColumnIndex("Parent"));
                int week = cursor.getInt(cursor.getColumnIndex("Month"));
                String username = cursor.getString(cursor.getColumnIndex("USERNAME"));
                String register = cursor.getString(cursor.getColumnIndexOrThrow("Createddate"));
                Log.d("register", register);
                Date temp = null;
                try {
                    temp = new SimpleDateFormat("yyyy-MM-dd").parse(register);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                loginValidate = new Person();
                loginValidate.setName(name);
                loginValidate.setParent(Parent);
                loginValidate.setWeek(week);
                loginValidate.setUserName(username);
                loginValidate.setRegisterdate(temp);
            }
            cursor.close();
        }
        return loginValidate;
    }
}

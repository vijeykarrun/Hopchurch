package com.hopchurch.DBfunctions;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "GodGift.db";
    static final int DATABASE_VERSION = 4;
    public static final int NAME_COLUMN = 1;
    private final Context context;
    public SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context, String table_name) {
        this.context = _context;
        this.dbHelper = new DataBaseHelper(this.context, DATABASE_NAME, null, 4, table_name);
    }

    public SQLiteDatabase open() throws SQLException {
        this.db = this.dbHelper.getWritableDatabase();
        return this.db;
    }

    public void close() {
        this.db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return this.db;
    }
}

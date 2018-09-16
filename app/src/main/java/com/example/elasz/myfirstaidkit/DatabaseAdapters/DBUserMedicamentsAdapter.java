package com.example.elasz.myfirstaidkit.DatabaseAdapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.elasz.myfirstaidkit.DatabaseImplement.DatabaseConstantInformation;
import com.example.elasz.myfirstaidkit.DatabaseImplement.DatabaseHelper;

import java.util.Date;

/**
 * Created by elasz on 12.09.2018.
 */

public class DBUserMedicamentsAdapter {

    Context context;
    SQLiteDatabase database;
    DatabaseHelper dbHelper;

    public DBUserMedicamentsAdapter(Context context){
        super();
        this.context=context;
        dbHelper = new DatabaseHelper(context);
    }

    public void OpenDB() throws SQLException {
        database=dbHelper.getWritableDatabase();
    }

    public void CloseDB() throws SQLException{
        dbHelper.close();
    }

    public long AddUserMedicamentData(String name, int id_medicament, String exp_date, String open_date, String form, String purpose, double amount, String amount_form, String person, String note) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(DatabaseConstantInformation.NAME, name);
            cv.put(DatabaseConstantInformation.ID_MEDICAMENT, id_medicament);
            cv.put(DatabaseConstantInformation.EXPDATE, exp_date);              //check format
            cv.put(DatabaseConstantInformation.OPENDATE, open_date);            //check format
            cv.put(DatabaseConstantInformation.FORM, form);
            cv.put(DatabaseConstantInformation.PURPOSE, purpose);
            cv.put(DatabaseConstantInformation.AMOUNT, amount);
            cv.put(DatabaseConstantInformation.AMOUNT_FORM, amount_form);       //check format
            cv.put(DatabaseConstantInformation.PERSON_NAME, person);            //check format
            cv.put(DatabaseConstantInformation.NOTE, note);
            return database.insert(DatabaseConstantInformation.USERMEDICAMENTSTABLE, null, cv);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

}

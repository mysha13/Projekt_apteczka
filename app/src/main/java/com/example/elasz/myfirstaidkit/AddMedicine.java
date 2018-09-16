package com.example.elasz.myfirstaidkit;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elasz.myfirstaidkit.DatabaseAdapters.DBMedicamentInfoAdapter;
import com.example.elasz.myfirstaidkit.DatabaseAdapters.DBUserMedicamentsAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddMedicine extends AppCompatActivity {

    private static final String TAG = "AddMedicine";
    private DBMedicamentInfoAdapter dbMedInfo;
    private DBUserMedicamentsAdapter dbUserMed;
   // private DatabaseFormAdapter dAForm;
    @BindView(R.id.et_name)
    EditText name;

    @BindView(R.id.tv_expdate)
    TextView expdate;

    @BindView(R.id.tv_opendate)
    TextView opendate;

    @BindView(R.id.spin_form)
    Spinner form;

    @BindView(R.id.spin_purpose)
    Spinner purpose;

    @BindView(R.id.et_amount)
    EditText amount;

    @BindView(R.id.spin_amountform)
    Spinner amountForm;

    @BindView(R.id.spin_person)
    Spinner person;

    @BindView(R.id.et_power)
    EditText power;

    @BindView(R.id.et_subsActive)
    EditText subsActive;
    @BindView(R.id.et_code)
    EditText code;

    @BindView(R.id.et_producer)
    EditText producer;
    @BindView(R.id.et_note)
    EditText note;
    @BindView(R.id.btn_addPhoto)
    FloatingActionButton addPhoto;

    @BindView(R.id.btn_removePhoto)
    FloatingActionButton removePhoto;


//butterknife nie działa!!!
    @BindView(R.id.btn_scanBarcode)
    Button btnScanBarcode;


private Button saveMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        ButterKnife.bind(this);
        name=(EditText) findViewById(R.id.et_name);
        amount=(EditText) findViewById(R.id.et_amount);
        saveMedicine=(Button)findViewById(R.id.btn_saveAddedMedicine);
        saveMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMedicineClick();
            }
        });





    }

    @OnClick(R.id.btn_saveAddedMedicine)
    void AddMedicineClick(){
        if(name.getText().toString().matches("")){
            Toast.makeText(AddMedicine.this, "Nazwa jest pusta", Toast.LENGTH_LONG).show();
        }else if(amount.getText().toString().matches("")){
            Toast.makeText(AddMedicine.this, "Ilość jest puste", Toast.LENGTH_LONG).show();
        }else{
            SetDatabaseAdapters();
            CheckResult();
        }
    }


    private void CheckResult() {
        long work = TryToAdd();
        if (work > 0) {
            Toast.makeText(AddMedicine.this, "Sukces", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(AddMedicine.this,"Porażka", Toast.LENGTH_LONG).show();
        }
    }
    private long TryToAdd() {
        //int place = getPlaceID(dAPlace, spinnerCorrection(spPlaceAdd));
        //int form = getFormID(dAForm, spinnerCorrection(spFormAdd));
        dbUserMed.OpenDB();
        long work = addMedToDB();
        dbUserMed.CloseDB();
        return work;
    }

    private long addMedToDB() {
        return dbUserMed.AddUserMedicamentData(name.getText().toString(),
                0,
                null,
                null,
                null,
                null,
                Double.parseDouble(amount.getText().toString().replaceAll(",",".")),
                null,
                null,
                null);
                //place);
    }

    private void SetDatabaseAdapters() {
        dbMedInfo = new DBMedicamentInfoAdapter(this);
        dbUserMed = new DBUserMedicamentsAdapter(this);
        //dAForm = new DatabaseFormAdapter(this);
    }


}

package com.example.csqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.csqlite.Database;
import com.example.csqlite.MainActivity;
import com.example.csqlite.R;

public class createActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnSimpan;
    EditText nama, sekolah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        database = new Database(this);
        nama = findViewById(R.id.txtNama);
        sekolah = findViewById(R.id.sekolah);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getReadableDatabase();
                db.execSQL("INSERT INTO siswa (nama, sekolah) VALUES ('" +
                        ""+nama.getText().toString()+"','"+
                        ""+sekolah.getText().toString()+"')");
                Toast.makeText(createActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivity.ma.Refresh();
                finish();
            }
        });
    }}


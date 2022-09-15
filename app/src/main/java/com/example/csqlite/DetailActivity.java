package com.example.csqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

class detailActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnSimpan;
    TextView nama, sekolah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        database = new Database(this);
        nama = findViewById(R.id.lbNama);
        sekolah = findViewById(R.id.lbSekolah);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM siswa WHERE nama='"+
                getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            sekolah.setText(cursor.getString(1).toString());
        }
    }
}
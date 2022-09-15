package com.example.csqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

class updateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnSimpan;
    EditText nama, sekolah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        nama =findViewById(R.id.txtNama);
        sekolah =findViewById(R.id.txtSekolah);
        btnSimpan =findViewById(R.id.btnSimpan);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM siswa WHERE nama='"+
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            sekolah.setText(cursor.getString(1).toString());
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =database.getReadableDatabase();
                db.execSQL("UPDATE siswa SET nama='"+
                        nama.getText().toString()+"',sekolah='" +
                        sekolah.getText().toString()+"'WHERE nama='"+
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(updateActivity.this, "Data Berhasil Di Update", Toast.LENGTH_SHORT).show();
                MainActivity.ma.Refresh();
                finish();
            }
        });
    }
}
package com.example.csqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.csqlite.Database;
import com.example.csqlite.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    String[] daftar;
    ListView listView1;
    Menu menu;
    protected Cursor cursor;
    Database database;
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fBtn = findViewById(R.id.floaBt);
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent form = new Intent(MainActivity.this, createActivity.class);
                startActivity(form);
            }
        });

        ma = this;
        database = new Database(this);
        Refresh();
    }

    public void Refresh() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM siswa",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i]=cursor.getString(0).toString();
        }
        listView1 = (ListView) findViewById(R.id.listViewForm);
        listView1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        listView1.setSelected(true);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            class updateActivity {
            }

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat","Edit","Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    class detailActivity {
                    }

                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), detailActivity.class);
                                i.putExtra("nama",selection);
                                startActivity(i);
                                break;

                            case 1:
                                Intent in = new Intent(getApplicationContext(),updateActivity.class);
                                in.putExtra("nama",selection);
                                startActivity(in);
                                break;

                            case 2:
                                SQLiteDatabase db = database.getReadableDatabase();
                                db.execSQL("DELETE FROM siswa WHERE nama='"+selection+"'");
                                Refresh();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listView1.getAdapter()).notifyDataSetInvalidated();
    }
}
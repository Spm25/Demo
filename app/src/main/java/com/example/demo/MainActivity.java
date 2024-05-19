package com.example.demo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd;
    private ListView listView;
    private SearchView searchView;
    private ArrayList<Taxi_MaDe> taxiList = new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        btnAdd = findViewById(R.id.floatingActionButton);

        taxiList = readAllTaxiFromDB();
        adapter = new Adapter(this, taxiList);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Taxi_MaDe taxi : taxiList) {
                    Toast.makeText(MainActivity.this, taxi.getSoXe(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<Taxi_MaDe> readAllTaxiFromDB() {
        ArrayList<Taxi_MaDe> taxiList = new ArrayList<>();
        SQLiteDB db = new SQLiteDB(this);
        Cursor cursor = db.readAllProducts();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String soXe = cursor.getString(1);
            double quangDuong = cursor.getDouble(2);
            double donGia = cursor.getDouble(3);
            int khuyenMai = cursor.getInt(4);
            taxiList.add(new Taxi_MaDe(id, soXe, quangDuong, donGia, khuyenMai));
        }
        taxiList.sort(Comparator.comparing(Taxi_MaDe::getSoXe));
        cursor.close();
        return taxiList;
    }
}

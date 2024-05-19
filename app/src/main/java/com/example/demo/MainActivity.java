package com.example.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

        registerForContextMenu(listView);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDB db = new SQLiteDB(MainActivity.this);
                db.addProduct(taxiList.get(0));
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Taxi_MaDe selectedTaxi = taxiList.get(info.position);

        if (item.getItemId() == R.id.edit) {
            // Mở màn hình chỉnh sửa với thông tin chi tiết của Taxi_MaDe
            Intent intent = new Intent(MainActivity.this, EditTaxiActivity.class);
            intent.putExtra("Id", selectedTaxi.getId());
            intent.putExtra("SoXe", selectedTaxi.getSoXe());
            intent.putExtra("QuangDuong", selectedTaxi.getQuangDuong());
            intent.putExtra("DonGia", selectedTaxi.getDonGia());
            intent.putExtra("KhuyenMai", selectedTaxi.getKhuyenMai());
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.delete) {
            // Xóa Taxi_MaDe
            showDeleteConfirmationDialog(selectedTaxi);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void showDeleteConfirmationDialog(final Taxi_MaDe taxi) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage(taxi.getSoXe() + " wants to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDB db = new SQLiteDB(MainActivity.this);
                db.deleteProduct(taxi);
                taxiList.remove(taxi);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        taxiList = readAllTaxiFromDB();
        adapter = new Adapter(this, taxiList);
        listView.setAdapter(adapter);
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

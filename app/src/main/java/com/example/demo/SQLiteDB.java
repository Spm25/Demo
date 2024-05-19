package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteDB extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "SqliteDB_NgaySinh";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TAXI = "Taxi_MaDe";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_TAXI +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "soXe TEXT, " +
            "quangDuong REAL, " +
            "donGia REAL, " +
            "khuyenMai INTEGER)";

    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAXI);
        onCreate(db);
    }

    public void addProduct(Taxi_MaDe taxi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soXe", taxi.getSoXe());
        values.put("quangDuong", taxi.getQuangDuong());
        values.put("donGia", taxi.getDonGia());
        values.put("khuyenMai", taxi.getKhuyenMai());

        long result = db.insert(TABLE_TAXI, null, values);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public Cursor readAllProducts() {
        String query = "SELECT * FROM " + TABLE_TAXI;
        SQLiteDatabase db = this.getReadableDatabase();
        return db != null ? db.rawQuery(query, null) : null;
    }

    public void updateProduct(Taxi_MaDe taxi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soXe", taxi.getSoXe());
        values.put("quangDuong", taxi.getQuangDuong());
        values.put("donGia", taxi.getDonGia());
        values.put("khuyenMai", taxi.getKhuyenMai());

        long result = db.update(TABLE_TAXI, values, "id=?", new String[]{String.valueOf(taxi.getId())});
        if(result == -1) {
            Toast.makeText(context, "Failed update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success update", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void deleteProduct(Taxi_MaDe taxi) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TAXI, "id=?", new String[]{String.valueOf(taxi.getId())});
        db.close();
    }

    public void insertSampleData(SQLiteDatabase db) {
        ArrayList<Taxi_MaDe> sampleData = new ArrayList<>();
        sampleData.add(new Taxi_MaDe(0, "ABC123", 15, 10000, 10));
        sampleData.add(new Taxi_MaDe(0, "XYZ789", 25, 15000, 15));
        sampleData.add(new Taxi_MaDe(0, "DEF456", 10, 12000, 5));
        sampleData.add(new Taxi_MaDe(0, "GHI012", 30, 18000, 20));
        sampleData.add(new Taxi_MaDe(0, "JKL345", 50, 25000, 25));
        sampleData.add(new Taxi_MaDe(0, "MNO678", 20, 20000, 10));

        for (Taxi_MaDe taxi : sampleData) {
            ContentValues values = new ContentValues();
            values.put("soXe", taxi.getSoXe());
            values.put("quangDuong", taxi.getQuangDuong());
            values.put("donGia", taxi.getDonGia());
            values.put("khuyenMai", taxi.getKhuyenMai());
            db.insert(TABLE_TAXI, null, values);
        }
    }
}

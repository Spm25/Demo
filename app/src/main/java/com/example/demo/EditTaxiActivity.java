package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditTaxiActivity extends AppCompatActivity {
    private EditText edtSoXe, edtQuangDuong, edtDonGia, edtKhuyenMai;
    private int id;
    private Button btnSave, btnBack;
    private Taxi_MaDe taxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        edtSoXe = findViewById(R.id.editTextSoXe);
        edtQuangDuong = findViewById(R.id.editTextQuangDuong);
        edtDonGia = findViewById(R.id.editTextDonGia);
        edtKhuyenMai = findViewById(R.id.editTextKhuyenMai);
        btnSave = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnBack);

        getIntentFromMain();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soXe = edtSoXe.getText().toString();
                double quangDuong = Double.parseDouble(edtQuangDuong.getText().toString());
                double donGia = Double.parseDouble(edtDonGia.getText().toString());
                int khuyenMai = Integer.parseInt(edtKhuyenMai.getText().toString());
                Taxi_MaDe updateTaxi = new Taxi_MaDe(id, soXe, quangDuong, donGia,khuyenMai);

                SQLiteDB db = new SQLiteDB(EditTaxiActivity.this);
                db.updateProduct(updateTaxi);

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void getIntentFromMain(){
        Intent intent = getIntent();

        id = intent.getIntExtra("Id", -1);
        edtSoXe.setText(intent.getStringExtra("SoXe"));
        edtQuangDuong.setText(String.valueOf(intent.getDoubleExtra("QuangDuong",0)));
        edtDonGia.setText(String.valueOf(intent.getDoubleExtra("DonGia",0)));
        edtKhuyenMai.setText(String.valueOf(intent.getIntExtra("KhuyenMai",0)));
    }
}

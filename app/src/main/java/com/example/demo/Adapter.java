package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Taxi_MaDe> data;
    private LayoutInflater inflater;

    public Adapter(Activity activity, ArrayList<Taxi_MaDe> data) {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.list_view_item, null);
        }
        TextView qd = v.findViewById(R.id.textViewQuangDuong);
        TextView sx = v.findViewById(R.id.textViewSoXe);
        TextView tt = v.findViewById(R.id.textViewTongTien);

        Taxi_MaDe taxi = data.get(position);
        qd.setText("Quang duong: " + String.valueOf(taxi.getQuangDuong()));
        sx.setText(String.valueOf(taxi.getSoXe()));
        tt.setText(String.valueOf(taxi.getQuangDuong()*taxi.getDonGia()*((float) (100 - taxi.getKhuyenMai()) /100)));

        return v;
    }
}

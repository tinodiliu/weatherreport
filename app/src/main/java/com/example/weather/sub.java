package com.example.weather;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class sub extends AppCompatActivity {

    Button mainpg;
    ArrayAdapter simpleAdapter;
    ListView MyConcernList;
    private final List<String> city_nameList = new ArrayList<>();
    private final List<String> city_codeList = new ArrayList<>();

    private void InitConcern() {       //进行数据填装
        MyDataBaseHelper dbHelper = new MyDataBaseHelper(this,"Concern.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor  = db.rawQuery("select * from Concern",null);
        while(cursor.moveToNext()){
            String adcode = cursor.getString(cursor.getColumnIndex("adcode"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            city_codeList.add(adcode);
            city_nameList.add(city);

        }
    }

    public void RefreshList(){
        city_nameList.removeAll(city_nameList);
        city_codeList.removeAll(city_codeList);
        simpleAdapter.notifyDataSetChanged();
        MyDataBaseHelper dbHelper = new MyDataBaseHelper(this,"Concern.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor  = db.rawQuery("select * from Concern",null);
        while(cursor.moveToNext()){
            String adcode = cursor.getString(cursor.getColumnIndex("adcode"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            city_codeList.add(adcode);
            city_nameList.add(city);
        }
    }


    @Override
    protected void onStart(){
        super.onStart();
        RefreshList();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscribe);
        MyConcernList = findViewById(R.id.subscribe);
        mainpg = findViewById(R.id.mainpg);
        mainpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sub.this,MainActivity.class);
                startActivity(intent);

            }
        });

        InitConcern();
        simpleAdapter = new ArrayAdapter(sub.this,android.R.layout.simple_list_item_1,city_nameList);
        MyConcernList.setAdapter(simpleAdapter);
        MyConcernList.setOnItemClickListener(new AdapterView.OnItemClickListener(){      //配置ArrayList点击按钮
            @Override
            public void  onItemClick(AdapterView<?> parent, View view , int position , long id){
                String tran = city_codeList.get(position);
                Intent intent = new Intent(sub.this, com.example.weather.Weather.class);
                intent.putExtra("trancitycode",tran);
                startActivity(intent);
            }
        });
    }
}

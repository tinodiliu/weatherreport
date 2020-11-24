package com.example.weather;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Weather extends AppCompatActivity  {

    TextView Textshow,text1;
    String researchcitycode;
    Button sub,refresh,cancel,list;
    int sign = 0;
    private String status;
    private String count;
    private String info;
    private String infocode;
    private String city;
    private String province;
    private String adcode;
    private String reporttime;
    private String weather;
    private String temperature;
    private String winddirection;
    private String windpower;
    private String humidity;
    int databaseid;
    String databasedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        Textshow = findViewById(R.id.TextView);
        text1 = findViewById(R.id.text1);

        sub = findViewById(R.id.sub);
        cancel = findViewById(R.id.cancel);
        list = findViewById(R.id.list);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Weather.this,sub.class);
                startActivity(intent);

            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("citycode", researchcitycode);
                editor.apply();
                MyDataBaseHelper dbHelper = new MyDataBaseHelper(getApplicationContext(), "Concern.db", null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String sub=province+city;
                values.put("adcode", researchcitycode);
                values.put("city", sub);
                db.insert("Concern", null, values);
                Toast.makeText(getApplicationContext(), "关注成功！", Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("citycode", researchcitycode);
                editor.apply();
                MyDataBaseHelper dbHelper = new MyDataBaseHelper(getApplicationContext(), "Concern.db", null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Concern","adcode = " + researchcitycode,null);
                Toast.makeText(getApplicationContext(), "取消关注成功！", Toast.LENGTH_LONG).show();
            }
        });

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(view -> {
            sign = 3;
            sendRequestWithOkHttp();
            Toast.makeText(this, "数据库刷新成功", Toast.LENGTH_LONG).show();
            Log.d("MainActivity","数据库刷新成功");

        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        researchcitycode = extras.getString("trancitycode");

        MyDataBaseHelper dbHelper = new MyDataBaseHelper(this,"Weather.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();     //同上，获得可写文件
        Cursor cursor  = db.query("Weather",new String[]{"id","data"},"id=?",new String[]{researchcitycode+""},null,null,null);

        if(cursor.moveToNext()) {       //逐行查找，得到匹配信息
            do {
                databaseid = cursor.getInt(cursor.getColumnIndex("id"));
                databasedata = cursor.getString(cursor.getColumnIndex("data"));
            } while (cursor.moveToNext());
        }
        int tranformat = 0;
        tranformat = Integer.parseInt(researchcitycode);

        if(databaseid ==  tranformat ){
            sign = 1;
            showResponse(databasedata);
        }else {
            sign = 0;
            sendRequestWithOkHttp();
        }

    }

    private void sendRequestWithOkHttp () {
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采⽤建造者模式，链式调⽤指明进⾏Get请求,传⼊Get的请求地址
        Request request = new Request.Builder().get()
                .url("https://restapi.amap.com/v3/weather/weatherInfo?key=8486c9c4560570914af1b347180f5e27&city=" + researchcitycode)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败处理
                Toast.makeText(getApplicationContext(),"Get请求失败",Toast.LENGTH_LONG);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //返回结果处理
                final String responseStr = response.body().string();
                Log.d("data is", responseStr);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"获取成功",Toast.LENGTH_LONG);
                        showResponse(responseStr);
                    }
                });
            }
        });
    }


    private void parseJSONWithFastJSON (String jsonData){
        if (jsonData.length() < 100) {
            Log.d("M", "城市ID不存在");
            Toast.makeText(this, "城市ID不存在，请重新输入", Toast.LENGTH_LONG).show();
            Weather.this.setResult(RESULT_OK, getIntent());
            Weather.this.finish();
        } else {
            tianqi tianqi=JSONObject.parseObject(jsonData, tianqi.class);
            status=tianqi.getstatus();
            count=tianqi.getcount();
            info=tianqi.getinfo();
            infocode=tianqi.getinfocode();
            for(Live live : tianqi.getlives()){
                province=live.getprovince();
                city= live.getcity();
                adcode= live.getadcode();
                weather= live.getweather();
                temperature= live.gettemperature();
                winddirection= live.getwinddirection();
                windpower= live.getwindpower();
                humidity= live.gethumidity();
                reporttime= live.getreporttime();
            }

            if (sign == 0) {
                MyDataBaseHelper dbHelper = new MyDataBaseHelper(this, "Weather.db", null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id", researchcitycode);
                values.put("data", jsonData);
                db.insert("Weather", null, values);
                Log.d("MainActivity", "数据库写入成功");
            } else if (sign == 1) {
                Log.d("数据库写入失败：", "数据已存在");

            } else {
                MyDataBaseHelper dbHelper = new MyDataBaseHelper(this, "Weather.db", null, 1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("id", researchcitycode);
                values.put("data", jsonData);
                db.update("Weather", values, "id=?", new String[]{researchcitycode + ""});
                Log.d("MainActivity", "数据库更新成功");

            }

        }
    }

    private void showResponse(final String response){
        runOnUiThread(() -> {
            parseJSONWithFastJSON(response);
            String CityshowString,temp;
            CityshowString = "状态:"+status+"\n"+"数量:"+count+"\n"+"info:"+info+"\n"+"infocode"+infocode+"\n"+"城市ID:"+adcode+"\n";
            temp = "所在省:"+province+"\n"+"当前城市:"+city+"\n"+"温度:"+temperature+"°C"+"\n"+"天气:"+weather+"\n"+"风向:"+winddirection+"\n"+"风力:"+windpower+"\n"+"湿度:"+humidity+"%"+"\n"+"更新时间"+reporttime+"\n";
            Textshow.setText(CityshowString);
            text1.setText(temp);

        });
    }


}

package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ListView provinceList;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText research = (EditText)findViewById(R.id.research);
        Button search = (Button)findViewById(R.id.search);
        Button subscribe = (Button)findViewById(R.id.subscribe);
        provinceList = findViewById(R.id.provincelist);
        search.setOnClickListener(view -> {
            String researchcitycode = String.valueOf(research.getText());
            if(researchcitycode.length()>6){
                Toast.makeText(getApplicationContext(),"数字长度不能大于六位！",Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent(MainActivity.this, Weather.class);
                intent.putExtra("trancitycode", researchcitycode);
                startActivity(intent);
            }
        });
        subscribe.setOnClickListener(view -> {
            SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
            String citycode = pref.getString("citycode", "");
            Intent intent = new Intent(MainActivity.this, sub.class);
            intent.putExtra("trancitycode", citycode);
            startActivity(intent);
        });

    }


}
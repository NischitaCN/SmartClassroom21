package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class classes extends AppCompatActivity {
    TextView light, class_name,on,off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        light=findViewById(R.id.light);
        class_name = findViewById(R.id.class_name);
        on=findViewById(R.id.on);
        off = findViewById(R.id.light);
        Intent intent = getIntent();
        String classname = intent.getStringExtra("class name");
        class_name.setText(classname);

        Random rand = new Random();
        int a=rand.nextInt(2);
        Log.i("info","random number is"+a);


        HashMap<String,Object> map = new HashMap<>();
        map.put("light_status ",a);
        FirebaseDatabase.getInstance().getReference().child("Ise department").child(classname).updateChildren(map);
        if(a==1){
            light.setText("LightStatus");
            on.setText("ON!!");
        }else{
            light.setText("LightStatus");
            on.setText("OFF!!");
        }



    }
}
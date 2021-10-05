package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class isedept extends AppCompatActivity implements View.OnClickListener {
    private CardView class101, class102, class103, class104;

    int[] ids = new int[]{R.id.c101, R.id.c102, R.id.c103, R.id.c104};
    CardView classes[] = new CardView[ids.length];
    String classnames[] = {"Class 101", "Class 102", "Class 103", "Class 104"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isedept);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        for (int i =0; i<ids.length;i++)
        {
            classes[i] = findViewById(ids[i]);
        }


        for (int i =0; i<ids.length;i++)
        {
            classes[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(isedept.this, classes.class);

        for (int i=0; i<ids.length; i++){
            if(view.getId()==ids[i]){
                intent.putExtra("class name", classnames[i] );
                startActivity(intent);
            }
        }
    }
}
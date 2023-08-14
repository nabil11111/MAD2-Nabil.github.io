package com.example.mathsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button button002;
    private Button button003;
    private Button button004;

    private Button button0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        button002 = findViewById(R.id.button002);
        button003=findViewById(R.id.button003);
        button004=findViewById(R.id.button004);
        button0=findViewById(R.id.button0);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
        button003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });
        button004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openActivity5();}
        });
        button002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openActivity1();}
        });

    }

    private void openActivity3() {
        Intent intent = new Intent(this, Level0.class);
        startActivity(intent);
    }
    private void openActivity4(){
        Intent intent=new Intent(this, Level1.class);
        startActivity(intent);
    }
    private void openActivity5(){
        Intent intent=new Intent(this, Level2.class);
        startActivity(intent);
    }
    private void openActivity1(){
        Intent intent=new Intent(this, welcome.class);
        startActivity(intent);
    }
}

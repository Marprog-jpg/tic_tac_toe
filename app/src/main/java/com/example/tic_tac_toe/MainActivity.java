package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("ciao");
        System.out.println("ciao sono alle");

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity2();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, humanVsHuman.class);
        startActivity(intent);
    }
}
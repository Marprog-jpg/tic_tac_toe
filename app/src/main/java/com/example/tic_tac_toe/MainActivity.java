package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);




        button = (Button) findViewById(R.id.human_vs_human_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        button = (Button) findViewById(R.id.human_vs_computer_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
        button = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, insertName.class); //humanvshuman
        intent.putExtra("scelta","0");
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, insertNameBotMode.class); //humanvsbot
        intent.putExtra("scelta","1");
        startActivity(intent);
    }

    public void openActivity4(){
        Intent intent = new Intent(this, darkThemeToggler.class); //humanvsbot
        startActivity(intent);
    }
}
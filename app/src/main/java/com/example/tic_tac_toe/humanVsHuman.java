package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class humanVsHuman extends AppCompatActivity implements View.OnClickListener{
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private int turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[][] gameMatrix = new int[3][3];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_vs_human);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);

        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);

        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);

        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String characterToPutIntoButton;
        if(turn % 2 == 0){
            characterToPutIntoButton = "X";
        }else{
            characterToPutIntoButton = "O";
        }

        if(view.getId() == R.id.button1) {
            button1.setText(characterToPutIntoButton);
        }else if(view.getId() == R.id.button2){
            button2.setText(characterToPutIntoButton);
        }else if(view.getId() == R.id.button3){
            button3.setText(characterToPutIntoButton);
        }else if(view.getId() == R.id.button4){
            button4.setText(characterToPutIntoButton);
        }else if(view.getId() == R.id.button5){
            button5.setText(characterToPutIntoButton);
        }else if(view.getId() == R.id.button6){
            button6.setText(characterToPutIntoButton);
        }else if(view.getId() == R.id.button7){
            button7.setText(characterToPutIntoButton);
        }else if(view.getId() == R.id.button8){
            button8.setText(characterToPutIntoButton);
        }else if(view.getId() == R.id.button9){
            button9.setText(characterToPutIntoButton);
        }
        turn++;

    }
}
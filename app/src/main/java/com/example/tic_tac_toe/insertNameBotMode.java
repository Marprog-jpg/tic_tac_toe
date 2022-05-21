package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class insertNameBotMode extends AppCompatActivity {



    private Button easyBotBtn;
    private Button hardBotBtn;

    private EditText nome1;
    private EditText nome2;

    String matchType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_name_bot_mode);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            matchType = extras.getString("scelta");
        }
            nome1 = (EditText) findViewById(R.id.nome_player1);

            nome1 = (EditText) findViewById(R.id.nome_player1);



        easyBotBtn = (Button) findViewById(R.id.easyBotBtn);
        easyBotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityEasyBot();

            }
        });


        hardBotBtn = (Button) findViewById(R.id.hardBotBtn);
        hardBotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityHardBot();
            }
        });
    }


    public void openActivityEasyBot(){
        Intent intent = new Intent(this, humanVsHuman.class);
        if(nome1.getText().toString().equals("")){
            nome1.setText("Human");
        }


        intent.putExtra("nome1", nome1.getText().toString());
        intent.putExtra("nome2", "Bot");
        intent.putExtra("scelta","1");
        intent.putExtra("botDifficulty","0");
        startActivity(intent);
    }

    public void openActivityHardBot(){
        Intent intent = new Intent(this, humanVsHuman.class);
        if(nome1.getText().toString().equals("")){
            nome1.setText("Human");
        }


        intent.putExtra("nome1", nome1.getText().toString());
        intent.putExtra("nome2", "Bot");
        intent.putExtra("scelta","1");
        intent.putExtra("botDifficulty","1");
        startActivity(intent);
    }



}
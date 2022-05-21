package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class insertName extends AppCompatActivity {

    private Button bottone;

    private Button easyBotBtn;
    private Button hardBotBtn;

    private EditText nome1;
    private EditText nome2;

    String matchType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_nomi);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            matchType = extras.getString("scelta");
        }
            if(matchType.equals("0")){
                nome1 = (EditText) findViewById(R.id.nome_player1);
                nome2 = (EditText) findViewById(R.id.nome_player2);
            }else{
                nome1 = (EditText) findViewById(R.id.nome_player1);
                nome2 = (EditText) findViewById(R.id.nome_player2);
                nome2.setEnabled(false);
            }

        bottone = (Button) findViewById(R.id.button_inserimento);
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchType.equals("0")){
                    openActivity2();
                }else{
                    openActivity3();
                }
            }
        });



        easyBotBtn = (Button) findViewById(R.id.easyBotBtn);
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchType.equals("0")){
                    openActivity2();
                }else{
                    openActivity3();
                }
            }
        });


        hardBotBtn = (Button) findViewById(R.id.hardBotBtn);
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matchType.equals("0")){
                    openActivity2();
                }else{
                    openActivity3();
                }
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, humanVsHuman.class);
        if(nome1.getText().toString().equals("")){
            nome1.setText("Player 1");
        }
        if(nome2.getText().toString().equals("")){
            nome2.setText("Player 2");
        }

        intent.putExtra("nome1", nome1.getText().toString());
        intent.putExtra("nome2", nome2.getText().toString());
        intent.putExtra("scelta","0");
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent = new Intent(this, humanVsHuman.class);

        if(nome1.getText().toString().equals("")){
            nome1.setText("Player 1");
        }

        intent.putExtra("nome1", nome1.getText().toString());
        intent.putExtra("nome2","Bot");
        intent.putExtra("scelta","1");
        startActivity(intent);
    }
}
package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class humanVsHuman extends AppCompatActivity implements View.OnClickListener{

    private List<Button> game_btns;

    private static final int[] button_ids = {
            R.id.game_btn_0,
            R.id.game_btn_1,
            R.id.game_btn_2,
            R.id.game_btn_3,
            R.id.game_btn_4,
            R.id.game_btn_5,
            R.id.game_btn_6,
            R.id.game_btn_7,
            R.id.game_btn_8,
    };



    private TextView textViewScorePlayer1;
    private TextView textViewScorePlayer2;
    private TextView textViewTurnNumber;
    private TextView textViewTurnPlayer;

    String namePlayer1;
    String namePlayer2;

    int scorePlayer1 = 0;
    int scorePlayer2 = 0;

    int boardSize = 9;

    char board[] = new char[]{' ', ' ', ' ',
            ' ', ' ', ' ',
            ' ', ' ', ' '};
    int[] magicSquare = new int[]{4, 9, 2, 3, 5, 7, 8, 1, 6};

    private int turn = 0;

    private Button screenShot_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[][] gameMatrix = new int[3][3];
        super.onCreate(savedInstanceState);
//inizio parte aggiunta

        //errore nel lissener
 //fine parte aggiunta

        setContentView(R.layout.activity_human_vs_human);

        initializeGameButtons();

        textViewScorePlayer1 = (TextView) findViewById(R.id.scorePlayer1);
        textViewScorePlayer2 = (TextView) findViewById(R.id.scorePlayer2);

        textViewTurnNumber = (TextView) findViewById(R.id.turnNumber);
        textViewTurnPlayer = (TextView) findViewById(R.id.turnPlayer);

        screenShot_btn = (Button)findViewById(R.id.button_screen);
        screenShot_btn.setOnClickListener(this);

        namePlayer1 = "Anna";
        namePlayer2 = "Bob";

        textViewScorePlayer1.setText(String.valueOf(scorePlayer1));
        textViewScorePlayer2.setText(String.valueOf(scorePlayer2));

        textViewTurnNumber.setText(String.valueOf(turn));
        textViewTurnPlayer.setText(namePlayer1);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() != R.id.button_screen) {
            System.out.println("WTFFFFFF");

            char characterToPutIntoButton;
            if (turn % 2 == 0) {
                characterToPutIntoButton = 'x';
                textViewTurnPlayer.setText(namePlayer2);
            } else {
                characterToPutIntoButton = 'o';
                textViewTurnPlayer.setText(namePlayer1);


            }

            for (int i = 0; i < boardSize; i++) {
                if (view.getId() == button_ids[i]) {
                    if (board[i] == ' ') {
                        game_btns.get(i).setText(String.valueOf(characterToPutIntoButton));
                        board[i] = characterToPutIntoButton;
                    }
                }
            }

            System.out.println(board);

            checkWinner();
            turn++;
            textViewTurnNumber.setText(String.valueOf(turn));
        }else{
            System.out.println("DENTRO");
            takeScreenshot();
        }
    }

    protected void initializeGameButtons(){
        int counter = 0;
        game_btns = new ArrayList<Button>();
        for(int id : button_ids){
            //System.out.println(id);

            Button game_btn = (Button)findViewById(id);
            game_btn.setOnClickListener(this);
            game_btns.add(game_btn);

        }


    }

    void checkWinner() {
        boolean boardIsFull = true;
        if (hasWon('x')){
            System.out.println("x win!");
            scorePlayer1++;
            textViewScorePlayer1.setText(String.valueOf(scorePlayer1));
            Toast.makeText(humanVsHuman.this, "Vittoria Reale di " + namePlayer1,Toast.LENGTH_LONG).show();
            generateNewGameBoardAfterEnd();
            return;
        }
        else if (hasWon('o')){
            System.out.println("o win!");
            scorePlayer2++;
            textViewScorePlayer2.setText(String.valueOf(scorePlayer2));
            Toast.makeText(humanVsHuman.this, "Vittoria Reale di " + namePlayer2,Toast.LENGTH_LONG).show();
            generateNewGameBoardAfterEnd();
            return;
        }
        else{
            System.out.println("No winner yet...");
        }
        for(int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                boardIsFull = false;
            }

        }
        if(boardIsFull == true){
            System.out.println("Draw!");
            Toast.makeText(humanVsHuman.this, "Pareggio",Toast.LENGTH_LONG).show();

            generateNewGameBoardAfterEnd();
            return;
        }
    }

    boolean hasWon(char player) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                for (int k = 0; k < 9; k++)
                    if (i != j && i != k && j != k)
                        if (board[i] == player && board[j] == player && board[k] == player)
                            if (magicSquare[i] + magicSquare[j] + magicSquare[k] == 15)
                                return true;
        return false;
    }

    void generateNewGameBoardAfterEnd(){
        board = new char[]{' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '};

        turn = 0;
        textViewTurnNumber.setText(String.valueOf(turn));
        textViewTurnPlayer.setText(namePlayer1);

        for(int i = 0; i < 8; i++){
            game_btns.get(i).setText("");
        }



    }
    //inizio parte aggiunta
    private void takeScreenshot() {

        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
            System.out.println(mPath);
            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            //View v1 = getWindow().getDecorView().findViewById(android.R.id.content);

            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            //openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(humanVsHuman.this, BuildConfig.APPLICATION_ID + ".provider",imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
//fine parte aggiunta
    void findBestMove(){
        int bestScore = -999;
        int bestMove;
        int score;
        for(int i = 0; i < 9; i++){
            if(board[i] == ' '){
                board[i] = 'o';
                score = minimax(board);
                if(score > bestScore){
                    score = bestScore;
                    bestMove = i;
                }
                board[i] = ' ';
            }
        }
    }
    int minimax(char[] board){
        return 1;
    }
}

// Queste righe di codice sono state prese da: https://fowlie.github.io/2018/08/24/winning-algorithm-for-tic-tac-toe-using-a-3x3-magic-square/

//fine righe di codice prese
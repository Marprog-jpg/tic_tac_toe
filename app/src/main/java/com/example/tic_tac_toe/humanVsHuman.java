package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView textViewScorePlayer1;
    private TextView textViewScorePlayer2;
    private TextView textViewTurnNumber;
    private TextView textViewTurnPlayer;

    String namePlayer1;
    String namePlayer2;

    int scorePlayer1 = 0;
    int scorePlayer2 = 0;


    char board[] = new char[]{' ', ' ', ' ',
                              ' ', ' ', ' ',
                              ' ', ' ', ' '};
    int[] magicSquare = new int[]{4, 9, 2, 3, 5, 7, 8, 1, 6};

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

        textViewScorePlayer1 = (TextView) findViewById(R.id.scorePlayer1);
        textViewScorePlayer2 = (TextView) findViewById(R.id.scorePlayer2);

        textViewTurnNumber = (TextView) findViewById(R.id.turnNumber);
        textViewTurnPlayer = (TextView) findViewById(R.id.turnPlayer);

        namePlayer1 = "Anna";
        namePlayer2 = "Bob";

        textViewScorePlayer1.setText(String.valueOf(scorePlayer1));
        textViewScorePlayer2.setText(String.valueOf(scorePlayer2));

        textViewTurnNumber.setText(String.valueOf(turn));
        textViewTurnPlayer.setText(namePlayer1);

    }

    @Override
    public void onClick(View view) {
        char characterToPutIntoButton;
        if(turn % 2 == 0){
            characterToPutIntoButton = 'x';
            textViewTurnPlayer.setText(namePlayer2);
        }else{
            characterToPutIntoButton = 'o';
            textViewTurnPlayer.setText(namePlayer1);
        }

        if(view.getId() == R.id.button1) {
            if(board[0] == ' ') {
                button1.setText(String.valueOf(characterToPutIntoButton));
                board[0] = characterToPutIntoButton;
            }
        }else if(view.getId() == R.id.button2){
            if(board[1] == ' '){
                button2.setText(String.valueOf(characterToPutIntoButton));
                board[1] = characterToPutIntoButton;
            }

        }else if(view.getId() == R.id.button3){
            if(board[2] == ' '){
                button3.setText(String.valueOf(characterToPutIntoButton));
                board[2] = characterToPutIntoButton;
            }
        }else if(view.getId() == R.id.button4){
            if(board[3] == ' '){
                button4.setText(String.valueOf(characterToPutIntoButton));
                board[3] = characterToPutIntoButton;
            }
        }else if(view.getId() == R.id.button5){
            if(board[4] == ' '){
                button5.setText(String.valueOf(characterToPutIntoButton));
                board[4] = characterToPutIntoButton;
            }
        }else if(view.getId() == R.id.button6){
            if(board[5] == ' '){
                button6.setText(String.valueOf(characterToPutIntoButton));
                board[5] = characterToPutIntoButton;
            }
        }else if(view.getId() == R.id.button7){
            if(board[6] == ' '){
                button7.setText(String.valueOf(characterToPutIntoButton));
                board[6] = characterToPutIntoButton;
            }
        }else if(view.getId() == R.id.button8){
            if(board[7] == ' '){
                button8.setText(String.valueOf(characterToPutIntoButton));
                board[7] = characterToPutIntoButton;
            }
        }else if(view.getId() == R.id.button9){
            if(board[8] == ' '){
                button9.setText(String.valueOf(characterToPutIntoButton));
                board[8] = characterToPutIntoButton;
            }
        }
        String boardInString = new String(board);
        System.out.println(board);

        checkWinner();
        turn++;
        textViewTurnNumber.setText(String.valueOf(turn));

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

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");

    }
}

// Queste righe di codice sono state prese da: https://fowlie.github.io/2018/08/24/winning-algorithm-for-tic-tac-toe-using-a-3x3-magic-square/

//fine righe di codice prese
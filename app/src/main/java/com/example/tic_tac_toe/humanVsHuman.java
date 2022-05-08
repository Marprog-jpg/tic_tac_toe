package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
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

        initializeGameButtons();

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

        if(view.getId() == button_ids[0]) {
            if(board[0] == ' ') {
                game_btns.get(0).setText(String.valueOf(characterToPutIntoButton));
                board[0] = characterToPutIntoButton;
            }
        }else if(view.getId() == button_ids[1]) {
            if(board[1] == ' '){
                game_btns.get(1).setText(String.valueOf(characterToPutIntoButton));
                board[1] = characterToPutIntoButton;
            }

        }else if(view.getId() == button_ids[2]) {
            if(board[2] == ' '){
                game_btns.get(2).setText(String.valueOf(characterToPutIntoButton));
                board[2] = characterToPutIntoButton;
            }
        }else if(view.getId() == button_ids[3]) {
            if(board[3] == ' '){
                game_btns.get(3).setText(String.valueOf(characterToPutIntoButton));
                board[3] = characterToPutIntoButton;
            }
        }else if(view.getId() == button_ids[4]) {
            if(board[4] == ' '){
                game_btns.get(4).setText(String.valueOf(characterToPutIntoButton));
                board[4] = characterToPutIntoButton;
            }
        }else if(view.getId() == button_ids[5]) {
            if(board[5] == ' '){
                game_btns.get(5).setText(String.valueOf(characterToPutIntoButton));
                board[5] = characterToPutIntoButton;
            }
        }else if(view.getId() == button_ids[6]) {
            if(board[6] == ' '){
                game_btns.get(6).setText(String.valueOf(characterToPutIntoButton));
                board[6] = characterToPutIntoButton;
            }
        }else if(view.getId() == button_ids[7]) {
            if(board[7] == ' '){
                game_btns.get(7).setText(String.valueOf(characterToPutIntoButton));
                board[7] = characterToPutIntoButton;
            }
        }else if(view.getId() == button_ids[8]) {
            if(board[8] == ' '){
                game_btns.get(8).setText(String.valueOf(characterToPutIntoButton));
                board[8] = characterToPutIntoButton;
            }
        }

        System.out.println(board);

        checkWinner();
        turn++;
        textViewTurnNumber.setText(String.valueOf(turn));

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

        game_btns.get(0).setText("");
        game_btns.get(1).setText("");
        game_btns.get(2).setText("");
        game_btns.get(3).setText("");
        game_btns.get(4).setText("");
        game_btns.get(5).setText("");
        game_btns.get(6).setText("");
        game_btns.get(7).setText("");
        game_btns.get(8).setText("");


    }
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
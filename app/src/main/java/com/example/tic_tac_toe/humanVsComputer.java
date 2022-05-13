package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class humanVsComputer extends AppCompatActivity implements View.OnClickListener {

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

    private TextView bestMoveForComputer;

    String namePlayer1;
    String namePlayer2;

    int scorePlayer1 = 0;
    int scorePlayer2 = 0;

    int boardSize = 9;
    char board[] = new char[]{' ', ' ', ' ',
            ' ', ' ', ' ',
            ' ', ' ', ' '};

    char[] fakeBoard;
    int[] magicSquare = new int[]{4, 9, 2, 3, 5, 7, 8, 1, 6};

    private int turn = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[][] gameMatrix = new int[3][3];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_vs_computer);

        initializeGameButtons();

        textViewScorePlayer1 = (TextView) findViewById(R.id.scorePlayer1);
        textViewScorePlayer2 = (TextView) findViewById(R.id.scorePlayer2);

        textViewTurnNumber = (TextView) findViewById(R.id.turnNumber);
        textViewTurnPlayer = (TextView) findViewById(R.id.turnPlayer);

        bestMoveForComputer = (TextView) findViewById(R.id.bestMoveForComputer);

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
        int positionOnBoardToChange;
        int bestMove;

        characterToPutIntoButton = 'x';
        textViewTurnPlayer.setText(namePlayer2);
        for (int i = 0; i < boardSize; i++) {
            if (view.getId() == button_ids[i]) {
                if (board[i] == ' ') {
                    game_btns.get(i).setText(String.valueOf(characterToPutIntoButton));
                    board[i] = characterToPutIntoButton;
                }
            }
        }

        characterToPutIntoButton = 'o';
        textViewTurnPlayer.setText(namePlayer1);
        //(new Handler()).postDelayed(this::findBestMove, 1000);
        turn++;

        fakeBoard = board.clone();

        System.out.println();
        System.out.println();
        System.out.println("REAL TURN " + turn + "AFTER ANNA'S MOVE");
        printCurrentBoard(board);
        System.out.println();

        bestMove = findBestMove();

                //minimax(fakeBoard, 1, 'o', 0);
        bestMoveForComputer.setText(String.valueOf(bestMove));

        game_btns.get(bestMove).setText(String.valueOf(characterToPutIntoButton));
        board[bestMove] = characterToPutIntoButton;



        /*for(int i = 0; i < boardSize; i++){
            if(view.getId() == button_ids[i]){
                if(board[i] == ' '){
                    game_btns.get(i).setText(String.valueOf(characterToPutIntoButton));
                    board[i] = characterToPutIntoButton;
                }
            }
        }*/



        System.out.println(board);

    checkWinner();


        textViewTurnNumber.setText(String.valueOf(turn));

}

    protected void printCurrentBoard(char[] board){
        for(int i = 0; i < 9; i=i+3){
            System.out.print(board[i] + " ");
            System.out.print(board[i+1] + " ");
            System.out.print(board[i+2] + " ");

            System.out.println();

        }
    }

    protected void initializeGameButtons() {
        int counter = 0;
        game_btns = new ArrayList<Button>();
        for (int id : button_ids) {
            //System.out.println(id);

            Button game_btn = (Button) findViewById(id);
            game_btn.setOnClickListener(this);
            game_btns.add(game_btn);

        }


    }

    void checkWinner() {
        boolean boardIsFull = true;
        if (hasWon('x', board)) {
            System.out.println("x win!");
            scorePlayer1++;
            textViewScorePlayer1.setText(String.valueOf(scorePlayer1));
            Toast.makeText(humanVsComputer.this, "Vittoria Reale di " + namePlayer1, Toast.LENGTH_LONG).show();
            //generateNewGameBoardAfterEnd();
            return;
        } else if (hasWon('o', board)) {
            System.out.println("o win!");
            scorePlayer2++;
            textViewScorePlayer2.setText(String.valueOf(scorePlayer2));
            Toast.makeText(humanVsComputer.this, "Vittoria Reale di " + namePlayer2, Toast.LENGTH_LONG).show();
            //generateNewGameBoardAfterEnd();
            return;
        } else {
            System.out.println("No winner yet...");
        }
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                boardIsFull = false;
            }

        }
        if (boardIsFull == true) {
            System.out.println("Draw!");
            Toast.makeText(humanVsComputer.this, "Pareggio", Toast.LENGTH_LONG).show();

            generateNewGameBoardAfterEnd();
            return;
        }
    }

    boolean hasWon(char player, char[] board) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                for (int k = 0; k < 9; k++)
                    if (i != j && i != k && j != k)
                        if (board[i] == player && board[j] == player && board[k] == player)
                            if (magicSquare[i] + magicSquare[j] + magicSquare[k] == 15)
                                return true;
        return false;
    }

    void generateNewGameBoardAfterEnd() {
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

    int findBestMove() {
        char[] fakeBoard = board.clone();
        int bestScore = 99999;
        int worstScore = 99999;
        int bestMove = -1;
        int score = 0;

        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {

                board[i] = 'o';
                System.out.println("SIMULATED TURN 0");
                printCurrentBoard(board);

                fakeBoard = board.clone();
                score = minimax(fakeBoard, 0, 'o', 1);
                System.out.println("BEST SCORE AT THE MOMENT = " + score);
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = i;
                    System.out.println("BestScore = " + bestScore + " bestMove = " + bestMove);
                }
                board[i] = ' ';
            }
        }
        System.out.println("Best possible move:  "+ bestMove);
        return bestMove;
    }

    boolean checkIfBoardIsFull(char[] board){
        for(int i = 0; i < 9; i++){
            if(board[i] == ' '){
                return false;
            }
        }
        return true;
    }

    int endState= 99;


    ArrayList<Integer> arrListCaselleOccupate = new ArrayList<>();
    int lastPositionBeforeDescending = 0;

    int minimax(char[] fakeBoard, int turn, char player, int movesMadeToEnd) {
        char[] boardBeforeDescending;
        int movesMadeUntilNow = 0;
        //arrListCaselleOccupate = new ArrayList<>();
        int lastPositionBeforeDescending = 0;
        int bestMove = 0;
        char[] bestBoardConfig;
        int bestScoreForLevel = 0;


        movesMadeToEnd++;
        if(hasWon('x', fakeBoard)){
            return 1000;
        }else if(hasWon('o', fakeBoard)){
            return -1000;
        }else if(checkIfBoardIsFull(fakeBoard)){
            return 0;
        }

        for(int i = 0; i < 9; i++){
            if(fakeBoard[i] == ' '){

                boardBeforeDescending = fakeBoard.clone();


                if(turn % 2 == 0){
                    player = 'x';
                }else{
                    player = 'o';
                }
                //printCurrentBoard(board);
                fakeBoard[i] = player;

                //printCurrentBoard(board);


                bestMove = i;



                endState = minimax(fakeBoard, turn, player, movesMadeToEnd);


                if(endState == 0 || endState == -1000 || endState == 1000){

                    //return endState + movesMadeToEnd
                    if(turn % 2 == 0){
                        if((endState - movesMadeToEnd) > bestScoreForLevel){
                            bestScoreForLevel = endState - movesMadeToEnd;
                        }
                    }else{
                        if((endState + movesMadeToEnd) < bestScoreForLevel){
                            bestScoreForLevel = endState + movesMadeToEnd;
                        }
                    }
                    endState = 0;
                    fakeBoard[i] = ' ';

                    //return bestScore;
                }else{

                    fakeBoard = boardBeforeDescending.clone();

                    if(turn % 2 == 0){
                        if((endState - movesMadeToEnd) > bestScoreForLevel){
                            bestScoreForLevel = endState - movesMadeToEnd;
                        }
                    }else{
                        if((endState + movesMadeToEnd) < bestScoreForLevel){
                            bestScoreForLevel = endState + movesMadeToEnd;
                        }
                    }
                    endState = 0;

                    //printCurrentBoard(boardBeforeDescending);



                }
                turn++;

            }
        }
        System.out.println("Best score for level: " + bestScoreForLevel);
        return bestScoreForLevel;
    }

}

// Queste righe di codice sono state prese da: https://fowlie.github.io/2018/08/24/winning-algorithm-for-tic-tac-toe-using-a-3x3-magic-square/

//fine righe di codice prese
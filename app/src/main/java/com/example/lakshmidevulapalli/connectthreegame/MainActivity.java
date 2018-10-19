package com.example.lakshmidevulapalli.connectthreegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* 0 is yellow and 1 is red*/
    int activePlayer = 0;

    /* variable to keep track of the active game */
    boolean gameIsActive = true;

    /* 2 means unplayed, nothing present at the slot */
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    /* arrays inside array -> will store winning positions */
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},
                                {2,5,8}, {0,4,8}, {2,4,6}};

    /* method to tap the blank space */
    public void dropIn(View view){
        /**
         * When the user taps on the empy space, it will move 1000 px up and
         * it will set yellow image as view
         */
        ImageView counter = (ImageView) view; // we need element by tap (view)


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        /* condition to check whether a coin is tapped or not */
        if(gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f); //move up

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow); // to add yellow image
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            /* loop to check winning positions*/
            for(int [] winningPositions: winningPositions){
                if(gameState[winningPositions[0]] == gameState[winningPositions[1]]
                        && gameState[winningPositions[1]] == gameState[winningPositions[2]]
                        && gameState[winningPositions[0]] != 2){

                    gameIsActive = false;
                    String winner = "Red";
                    if(gameState[winningPositions[0]] == 0){
                        winner = "Yellow";
                    }

                    // Player has won
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner+" has won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }else{

                    boolean gameIsOver = true;
                    for(int counterState : gameState){
                        if(counterState == 2)
                            gameIsOver = false;
                    }
                    if(gameIsOver){
                        // Player has won
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a Draw!");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {

        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

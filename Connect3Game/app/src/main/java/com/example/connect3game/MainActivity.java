package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int turn = 0;
    int[] gameStates ={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void FallIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameStates[tappedCounter]==2 && gameActive)
        {
            gameStates[tappedCounter] = turn;
            counter.setTranslationY(-1500);
            if (turn == 0) {
                counter.setImageResource(R.drawable.yellow);
                turn = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                turn = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameStates[winningPosition[0]] == gameStates[winningPosition[1]] && gameStates[winningPosition[1]] == gameStates[winningPosition[2]] && gameStates[winningPosition[0]] != 2) {
                    TextView resultText = (TextView) findViewById(R.id.resultText);
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    gameActive = false;
                    if (turn == 1) {
                        resultText.setText("Yellow Wins!!!");
                    } else {
                        resultText.setText("Red Wins!!!");
                    }
                    resultText.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void PlayAgain(View view)
    {
        TextView resultText = (TextView) findViewById(R.id.resultText);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        resultText.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(gameStates, 2);
        turn =0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
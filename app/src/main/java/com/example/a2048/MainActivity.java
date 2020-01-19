package com.example.a2048;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.a2048.Utils.customText;
import static com.example.a2048.Utils.getScore;
import static com.example.a2048.Utils.goingDown;
import static com.example.a2048.Utils.goingLeft;
import static com.example.a2048.Utils.goingRight;
import static com.example.a2048.Utils.goingUp;
import static com.example.a2048.Utils.setColor;

public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[][] map = new int[4][4];
        initGame(map);

        Button RESET = (Button) findViewById(R.id.RESET);
        View view = findViewById(R.id.view);

        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                if (goingLeft(map)){
                    setView(map);pauseAndRefresh(map, 250);
                } else{
                    makeToast();
                }
            }

            @Override
            public void onSwipeRight() {
                if (goingRight(map)){
                    setView(map);pauseAndRefresh(map, 250);
                } else{
                    makeToast();
                }
            }

            @Override
            public void onSwipeUp() {
                if (goingUp(map)){
                    setView(map);pauseAndRefresh(map, 250);
                } else{
                    makeToast();
                }
            }

            @Override
            public void onSwipeDown() {
                if (goingDown(map)){
                    setView(map);pauseAndRefresh(map, 250);
                } else{
                    makeToast();
                }
            }
        });

        RESET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int curr = getScore(map);
                initGame(map);
            }
        });
    }

    public void initGame(int[][] map){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                map[i][j] = 0;
            }
        }
        setRandomNumber(map);
        setView(map);
    }

    public void pauseAndRefresh(final int[][] map, int time){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setRandomNumber(map);
                setView(map);
            }
        }, time);
    }

    public void setView(int[][] map){
        TextView A1 = (TextView) findViewById(R.id.A1);
        A1.setText(customText(map[0][0]));
        TextView A2 = (TextView) findViewById(R.id.A2);
        A2.setText(customText(map[0][1]));
        TextView A3 = (TextView) findViewById(R.id.A3);
        A3.setText(customText(map[0][2]));
        TextView A4 = (TextView) findViewById(R.id.A4);
        A4.setText(customText(map[0][3]));
        TextView B1 = (TextView) findViewById(R.id.B1);
        B1.setText(customText(map[1][0]));
        TextView B2 = (TextView) findViewById(R.id.B2);
        B2.setText(customText(map[1][1]));
        TextView B3 = (TextView) findViewById(R.id.B3);
        B3.setText(customText(map[1][2]));
        TextView B4 = (TextView) findViewById(R.id.B4);
        B4.setText(customText(map[1][3]));
        TextView C1 = (TextView) findViewById(R.id.C1);
        C1.setText(customText(map[2][0]));
        TextView C2 = (TextView) findViewById(R.id.C2);
        C2.setText(customText(map[2][1]));
        TextView C3 = (TextView) findViewById(R.id.C3);
        C3.setText(customText(map[2][2]));
        TextView C4 = (TextView) findViewById(R.id.C4);
        C4.setText(customText(map[2][3]));
        TextView D1 = (TextView) findViewById(R.id.D1);
        D1.setText(customText(map[3][0]));
        TextView D2 = (TextView) findViewById(R.id.D2);
        D2.setText(customText(map[3][1]));
        TextView D3 = (TextView) findViewById(R.id.D3);
        D3.setText(customText(map[3][2]));
        TextView D4 = (TextView) findViewById(R.id.D4);
        D4.setText(customText(map[3][3]));
        A1.setBackgroundColor(Color.parseColor(setColor(map[0][0])));
        A2.setBackgroundColor(Color.parseColor(setColor(map[0][1])));
        A3.setBackgroundColor(Color.parseColor(setColor(map[0][2])));
        A4.setBackgroundColor(Color.parseColor(setColor(map[0][3])));
        B1.setBackgroundColor(Color.parseColor(setColor(map[1][0])));
        B2.setBackgroundColor(Color.parseColor(setColor(map[1][1])));
        B3.setBackgroundColor(Color.parseColor(setColor(map[1][2])));
        B4.setBackgroundColor(Color.parseColor(setColor(map[1][3])));
        C1.setBackgroundColor(Color.parseColor(setColor(map[2][0])));
        C2.setBackgroundColor(Color.parseColor(setColor(map[2][1])));
        C3.setBackgroundColor(Color.parseColor(setColor(map[2][2])));
        C4.setBackgroundColor(Color.parseColor(setColor(map[2][3])));
        D1.setBackgroundColor(Color.parseColor(setColor(map[3][0])));
        D2.setBackgroundColor(Color.parseColor(setColor(map[3][1])));
        D3.setBackgroundColor(Color.parseColor(setColor(map[3][2])));
        D4.setBackgroundColor(Color.parseColor(setColor(map[3][3])));

        TextView scoreText = (TextView) findViewById(R.id.score);
        scoreText.setText("" + getScore(map));
        TextView maxScore = (TextView) findViewById(R.id.maxScore);
        maxScore.setText("" + getMaxScore(map));

    }

    public int getMaxScore(int[][] map){
        int curr = getScore(map);
        sharedPreferences = getSharedPreferences("SCORE", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int stored = sharedPreferences.getInt("SCORE", 0);
        if(curr > stored){
            editor.putInt("SCORE", curr);
            editor.commit();
            return curr;
        } else{
            return stored;
        }
    }

    public void setRandomNumber(int[][] map){
        int num = (int) (Math.random() * 10) + 1;
        Log.d("Random Number is", " " + num);
        boolean flag;
        if(map[0][0] != 0 && map[0][1] != 0 && map[0][2] != 0 && map[0][3] != 0 &&
                map[1][0] != 0 && map[1][1] != 0 && map[1][2] != 0 && map[1][3] != 0 &&
                map[2][0] != 0 && map[2][1] != 0 && map[2][2] != 0 && map[2][3] != 0 &&
                map[3][0] != 0 && map[3][1] != 0 && map[3][2] != 0 && map[3][3] != 0){
            flag = false;
            makeToast();
        } else{
            flag = true;
        }
        while(flag){
            for(int i = 0; i < 4 && flag; i++){
                for(int j = 0; j < 4 && flag; j++){
                    if(map[i][j] == 0 && num == 0){
                        map[i][j] = 2;
                        flag = false;
                    }
                    if(map[i][j] == 0 && num > 0){
                        num--;
                    }
                }

            }
        }
        Log.d("Map[0]", " " + map[0][0] + " " + map[0][1] + " " + map[0][2] + " " + map[0][3]);
        Log.d("Map[1]", " " + map[1][0] + " " + map[1][1] + " " + map[1][2] + " " + map[1][3]);
        Log.d("Map[2]", " " + map[2][0] + " " + map[2][1] + " " + map[2][2] + " " + map[2][3]);
        Log.d("Map[3]", " " + map[3][0] + " " + map[3][1] + " " + map[3][2] + " " + map[3][3]);
    }

    public void makeToast(){
        Toast.makeText(this, "Out of move in this direction!", Toast.LENGTH_SHORT).show();
    }

}

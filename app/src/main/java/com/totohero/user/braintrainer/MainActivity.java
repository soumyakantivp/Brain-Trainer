package com.totohero.user.braintrainer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView time;
    private TextView question;
    private TextView label;
    private TextView scoreField;
    private Button start;
    GridLayout options;



    static int correctAnswer;
    int score;
    int total;
    public void startTimer(){

        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                time.setText((l/1000)+"");
                if(l<5000){
                    time.setTextColor(Color.RED);
                }
            }

            @Override
            public void onFinish() {

                disableGame();

            }
        }.start();
    }
    public void updateScore(){
        scoreField = (TextView) findViewById(R.id.score);
        scoreField.setText(score+"/"+total);
    }
    public void generateQ(View view){
        int a = (int)(Math.random()*100);
        int b = (int)(Math.random()*100);
        question = (TextView) findViewById(R.id.question);
        question.setText(a+" + "+b);
        int answer[] = new int[4];
        int anskey = (int)Math.floor(Math.random()*4);

        for(int i=0; i<4; i++){
            if(i == anskey)
                answer[i] = a+b;
            else
                answer[i] = (int)(Math.random()*100);
        }
        GridLayout grid = (GridLayout)view;
        ((Button)grid.findViewWithTag("1")).setText(""+answer[0]);
        ((Button)grid.findViewWithTag("2")).setText(""+answer[1]);
        ((Button)grid.findViewWithTag("3")).setText(""+answer[2]);
        ((Button)grid.findViewWithTag("4")).setText(""+answer[3]);
        correctAnswer = a+b;
    }
    public void check(View view){
        Button answer = (Button) view;
        int ans = Integer.parseInt((String)answer.getText());
        //Log.i("test"," = "+ans+"|"+correctAnswer);
        if(ans == correctAnswer){
            label.setText("Correct Answer!");
            score++;
        }
        else {
            label.setText("Wrong Answer!");
        }
        total++;
        updateScore();
        options = (GridLayout) findViewById(R.id.gridLayout);
        generateQ(options);
    }
    public void disableGame(){
        options.setVisibility(View.INVISIBLE);
        label.setVisibility(View.INVISIBLE);
        start = (Button) findViewById(R.id.start);
        start.setVisibility(View.VISIBLE);
    }

    public void startGame(View view){
        time = (TextView) findViewById(R.id.timer);
        label = (TextView) findViewById(R.id.label);
        options = (GridLayout) findViewById(R.id.gridLayout);
        scoreField = (TextView) findViewById(R.id.score);
        label.setText("");
        scoreField.setText("");
        Button b = (Button) view;
        b.setVisibility(View.INVISIBLE);
        b.setText("Play Again!");
        time.setTextColor(Color.BLUE);
        options.setVisibility(View.VISIBLE);
        label.setVisibility(View.VISIBLE);
        startTimer();
        score = 0;
        total = 0;

        generateQ(options);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

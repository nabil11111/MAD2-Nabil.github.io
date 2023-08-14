package com.example.mathsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    TextView timerlvl2;
    TextView qnum;
    TextView scorenum;
    TextView lvl2quiz;


    Button choice2;
    Button choice3;
    Button choice1;

    int quiz = 0;
    int great2 = 0;
    int rightAnswer2 = 0;
    String realOperator = "";
    CountDownTimer countDownTimer2;
    private boolean isTimerRunning = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level2);

        timerlvl2=findViewById(R.id.timerlvl2);

        qnum = findViewById(R.id.qnum);
        scorenum = findViewById(R.id.scorenum);
        lvl2quiz = findViewById(R.id.lvl2quiz);





        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice1 = findViewById(R.id.choice1);

        qnum.setText("Question : " + quiz + " / 10");
        scorenum.setText("SCORE " + great2 + " / 10");




        if (quiz < 10) {
            getARandomQuestion();

        }
    }
    private int getARandomQuestion() {
        isTimerRunning = true;
        choice2.setBackgroundResource(R.drawable.buttons_option_bg);
        choice3.setBackgroundResource(R.drawable.buttons_option_bg);
        choice1.setBackgroundResource(R.drawable.buttons_option_bg);
        timerlvl2(10000);


        // get random number for question

        int firstNumber = new Random().nextInt(12)+1;
        int secondNumber = new Random().nextInt(12)+1;


        // get random operation (+ , - , *,/)

        int operator = new Random().nextInt(4) + 1;

        // get two options

        int optionA ;
        int optionB;

        do {
            optionA = new Random().nextInt(40)+1; // Generate random number
        } while (optionA == rightAnswer2);

        do {
            optionB = new Random().nextInt(75)+1; // Generate random number
        } while (optionB == rightAnswer2 || optionB == optionA);


        if (operator == 1) {
            realOperator = "+";
            rightAnswer2 = firstNumber + secondNumber;
            lvl2quiz.setText(firstNumber + " " + realOperator + " " + secondNumber + " = ?");
        } else {
            if (operator == 2) {
                realOperator = "*";
                rightAnswer2 = firstNumber * secondNumber;
                lvl2quiz.setText(firstNumber + " " + realOperator + " " + secondNumber + " = ?");
            } else {
                if (operator == 3) {
                    realOperator = "-";

                    if (firstNumber < secondNumber) {
                        rightAnswer2 = secondNumber - firstNumber;
                        lvl2quiz.setText(secondNumber + "  " + realOperator + "  " + firstNumber + " = ?");
                    } else {
                        rightAnswer2 = firstNumber - secondNumber;
                        lvl2quiz.setText(firstNumber + "  " + realOperator + "  " + secondNumber + " = ?");
                    }
                }else {
                    if (operator==4){
                        realOperator = "/";
                        // Ensure the second number is not zero to avoid division by zero
                        do {
                            secondNumber = new Random().nextInt(10) + 1;
                        } while (firstNumber % secondNumber != 0);

                        rightAnswer2 = firstNumber / secondNumber;
                        lvl2quiz.setText(firstNumber + "  " + realOperator + "  " + secondNumber + " = ?");
                    }
                }
            }
        }


        // get random position of right answer

        int position = new Random().nextInt(3) + 1; // 1 or 2 or 3 position

        if (position == 1) {
            choice2.setText("" + rightAnswer2);
            choice3.setText("" + optionA);
            choice1.setText("" + optionB);
        } else {
            choice2.setText("" + optionA);
            if (position == 2) {
                choice3.setText("" + rightAnswer2);
                choice1.setText("" + optionB);
            } else {
                choice3.setText("" + optionB);
                choice1.setText("" + rightAnswer2);

            }
        }

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer2.cancel();
                if (choice2.getText().equals("" + rightAnswer2)) {
                    choice2.setBackgroundResource(R.drawable.right_answer_bg);
                    great2 = great2 + 1; // Increment score only when the answer is correct
                } else {
                    choice2.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                quiz = quiz + 1;
                qnum.setText("Question : " + quiz + " / 10");
                scorenum.setText("SCORE : " + great2 + " / 10");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (quiz < 10) {
                            getARandomQuestion();
                        } else {
                            Intent intent = new Intent(Level2.this, Home.class);
                            intent.putExtra("RA", great2);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 1000); // 1 sec

            }

        });


// Similarly, update the onClick listeners for buttonOp14 and buttonOp15


        int finalSecondNumber = secondNumber;
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvl2quiz.setText(firstNumber + realOperator + finalSecondNumber + "=" + rightAnswer2);
                countDownTimer2.cancel();
                if (choice3.getText().equals("" + rightAnswer2)) {
                    choice3.setBackgroundResource(R.drawable.right_answer_bg);
                    great2 = great2 + 1;
                    quiz = quiz + 1;
                    qnum.setText("Question : " + quiz + " / 10");
                    scorenum.setText("SCORE : " + great2 + " / 10");
                } else {
                    quiz = quiz + 1;
                    qnum.setText("Question : " + quiz + " / 10");
                    choice3.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (quiz < 10) {
                            getARandomQuestion();
                        } else {
                            Intent intent = new Intent(Level2.this, Home.class);
                            intent.putExtra("RA", great2);
                            startActivity(intent);
                            finish();
                        }

                    }
                }, 1000); // 1 sec

            }
        });

        int finalSecondNumber1 = secondNumber;
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvl2quiz.setText(firstNumber + realOperator + finalSecondNumber1 + "=" + rightAnswer2);
                countDownTimer2.cancel();
                if (choice1.getText().equals("" + rightAnswer2)) {
                    choice1.setBackgroundResource(R.drawable.right_answer_bg);
                    great2 = great2 + 1;
                    quiz = quiz + 1;
                    qnum.setText("Question : " + quiz + " / 10");
                    scorenum.setText("SCORE : " + great2 + " / 10");
                } else {
                    quiz = quiz + 1;
                    qnum.setText("Question : " + quiz + " / 10");
                    choice1.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (quiz < 10) {
                            getARandomQuestion();
                        } else {
                            Intent intent = new Intent(Level2.this, Home.class);
                            intent.putExtra("RA", great2);
                            startActivity(intent);
                            finish();
                        }

                    }
                }, 1000); // 1 sec
            }
        });




        return firstNumber;
    }
    public void timerlvl2(long time) {
        countDownTimer2 = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerlvl2.setText(String.valueOf(millisUntilFinished / 1000) + "s");

                if (millisUntilFinished < 5000) {
                    timerlvl2.setTextColor(Color.YELLOW);
                } else {
                    timerlvl2.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onFinish() {
                timerlvl2.setText("Time over!");
                isTimerRunning = false;
                timeout();
            }
        }.start();


    }

    public void timeout() {
        if (isTimerRunning) {
            // The timer is still running.
        } else {
            // The timer has stopped.

            countDownTimer2.cancel();

            // Check if any of the buttons have the correct answer and highlight them
            if (choice2.getText().equals("" + rightAnswer2)) {
                choice2.setBackgroundResource(R.drawable.right_answer_bg);
            } else if (choice3.getText().equals("" + rightAnswer2)) {
                choice3.setBackgroundResource(R.drawable.right_answer_bg);
            } else if (choice1.getText().equals("" + rightAnswer2)) {
                choice1.setBackgroundResource(R.drawable.right_answer_bg);
            }

            quiz = quiz + 1;
            qnum.setText("Question : " + quiz + " / 10");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (quiz < 10) {
                        getARandomQuestion();
                    } else {
                        Intent intent = new Intent(Level2.this, Home.class);
                        intent.putExtra("RA", great2);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 1000); // 1 sec
        }
    }


}
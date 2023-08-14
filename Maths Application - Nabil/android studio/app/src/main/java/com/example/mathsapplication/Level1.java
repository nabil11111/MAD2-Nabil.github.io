package com.example.mathsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    TextView timertxt;
    TextView quiz_counter_1;
    TextView score_counter_1;
    TextView quiztxt;


    Button option1;

    Button option2;

    Button option3;

    int quiz = 0;
    int score = 0;
    int rightAnswer = 0;
    String realOperation = "";
    CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1);

        timertxt=findViewById(R.id.timerlvl2);
        quiz_counter_1 = findViewById(R.id.quiz_counter_1);
        score_counter_1 = findViewById(R.id.score_counter_1);
        quiztxt = findViewById(R.id.quiztxt);

        option1 = findViewById(R.id.option2);
        option2 = findViewById(R.id.option3);
        option3 = findViewById(R.id.option1);

        quiz_counter_1.setText("Question : " + quiz + " / 10");
        score_counter_1.setText("SCORE " + score + " / 10");


        if (quiz < 10) {
            getARandomQuestion();
    }
}
    private int getARandomQuestion() {
        isTimerRunning = true;
        option1.setBackgroundResource(R.drawable.buttons_option_bg);
        option2.setBackgroundResource(R.drawable.buttons_option_bg);
        option3.setBackgroundResource(R.drawable.buttons_option_bg);
        timer(20000);


        // get random number for question

        int firstnum= new Random().nextInt(12)+1;
        int secondnum = new Random().nextInt(12)+1;


        // get random operator (+ , - , *, /)

        int operator = new Random().nextInt(4) + 1;

        // get two options

        int optionA ; // max number 10 , 10 * 10 = 100 then we add 100 as a max number
        int optionB ;

        do {
            optionA = new Random().nextInt(40)+1; // Generate random number
        } while (optionA == rightAnswer);

        do {
            optionB = new Random().nextInt(75)+1; // Generate random number
        } while (optionB == rightAnswer || optionB == optionA);


        if (operator == 1) {
            realOperation = "+";
            rightAnswer = firstnum + secondnum;
            quiztxt.setText(firstnum + " " + realOperation + " " + secondnum + " = ?");
        } else {
            if (operator == 2) {
                realOperation = "*";
                rightAnswer = firstnum * secondnum;
                quiztxt.setText(firstnum + " " + realOperation + " " + secondnum + " = ?");
            } else {
                if (operator == 3) {
                    realOperation = "-";

                    if (firstnum < secondnum) {
                        rightAnswer = secondnum - firstnum;
                        quiztxt.setText(secondnum + "  " + realOperation + "  " + firstnum + " = ?");
                    } else {
                        rightAnswer = firstnum - secondnum;
                        quiztxt.setText(firstnum + "  " + realOperation + "  " + secondnum + " = ?");
                    }
                }else {
                    if (operator==4){
                        realOperation = "/";
                        // Ensure the second number is not zero to avoid division by zero
                        do {
                            secondnum = new Random().nextInt(10) + 1;
                        } while (firstnum % secondnum != 0);

                        rightAnswer = firstnum / secondnum;
                        quiztxt.setText(firstnum + "  " + realOperation + "  " + secondnum + " = ?");
                    }
                }
            }
        }


        // get random position of right answer

        int position = new Random().nextInt(3) + 1; // 1 or 2 or 3 position

        if (position == 1) {
            option1.setText("" + rightAnswer);
            option2.setText("" + optionA);
            option3.setText("" + optionB);
        } else {
            option1.setText("" + optionA);
            if (position == 2) {
                option2.setText("" + rightAnswer);
                option3.setText("" + optionB);
            } else {
                option2.setText("" + optionB);
                option3.setText("" + rightAnswer);

            }
        }

        int finalSecondnum2 = secondnum;
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quiztxt.setText(firstnum+ realOperation + finalSecondnum2 + "=" + rightAnswer);
                countDownTimer.cancel();
                if (option1.getText().equals("" + rightAnswer)) {
                    option1.setBackgroundResource(R.drawable.right_answer_bg);
                    score = score + 1; // Increment score only when the answer is correct
                } else {
                    option1.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                quiz = quiz + 1;
                quiz_counter_1.setText("Question : " + quiz + " / 10");
                score_counter_1.setText("SCORE : " + score + " / 10");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (quiz < 10) {
                            getARandomQuestion();
                        } else {
                            Intent intent = new Intent(Level1.this, Home.class);
                            intent.putExtra("RA", score);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 1000); // 1 sec
            }
        });

// Similarly, update the onClick listeners for buttonOp14 and buttonOp15


        int finalSecondnum = secondnum;
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiztxt.setText(firstnum+ realOperation + finalSecondnum + "=" + rightAnswer);
                countDownTimer.cancel();
                if (option2.getText().equals("" + rightAnswer)) {
                    option2.setBackgroundResource(R.drawable.right_answer_bg);
                    score = score + 1;
                    quiz = quiz + 1;
                    quiz_counter_1.setText("Question : " + quiz + " / 10");
                    score_counter_1.setText("SCORE : " + score + " / 10");
                } else {
                    quiz = quiz + 1;
                    quiz_counter_1.setText("Question : " + quiz + " / 10");
                    option2.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (quiz < 10) {
                            getARandomQuestion();
                        } else {
                            Intent intent = new Intent(Level1.this, Home.class);
                            intent.putExtra("RA", score);
                            startActivity(intent);
                            finish();
                        }

                    }
                }, 1000); // 1 sec
            }
        });

        int finalSecondnum1 = secondnum;
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiztxt.setText(firstnum+ realOperation + finalSecondnum1 + "=" + rightAnswer);
                countDownTimer.cancel();
                if (option3.getText().equals("" + rightAnswer)) {
                    option3.setBackgroundResource(R.drawable.right_answer_bg);
                    score = score + 1;
                    quiz = quiz + 1;
                    quiz_counter_1.setText("Question : " + quiz + " / 10");
                    score_counter_1.setText("SCORE : " + score + " / 10");
                } else {
                    quiz = quiz + 1;
                    quiz_counter_1.setText("Question : " + quiz + " / 10");
                    option3.setBackgroundResource(R.drawable.wrong_answer_bg);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (quiz < 10) {
                            getARandomQuestion();
                        } else {
                            Intent intent = new Intent(Level1.this, Home.class);
                            intent.putExtra("RA", score);
                            startActivity(intent);
                            finish();
                        }

                    }
                }, 1000); // 1 sec
            }
        });




        return firstnum;
    }
    public void timer(long time) {
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timertxt.setText(String.valueOf(millisUntilFinished / 1000) + "s");

                if (millisUntilFinished < 5000) {
                    timertxt.setTextColor(Color.YELLOW);
                } else {
                    timertxt.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onFinish() {
                timertxt.setText("Time over!");
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

            countDownTimer.cancel();

            // Check if any of the buttons have the correct answer and highlight them
            if (option1.getText().equals("" + rightAnswer)) {
                option1.setBackgroundResource(R.drawable.right_answer_bg);
            } else if (option2.getText().equals("" + rightAnswer)) {
                option2.setBackgroundResource(R.drawable.right_answer_bg);
            } else if (option3.getText().equals("" + rightAnswer)) {
                option3.setBackgroundResource(R.drawable.right_answer_bg);
            }

            quiz = quiz + 1;
            quiz_counter_1.setText("Question : " + quiz + " / 10");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (quiz < 10) {
                        getARandomQuestion();
                    } else {
                        Intent intent = new Intent(Level1.this, Home.class);
                        intent.putExtra("RA", score);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 1000); // 1 sec
        }
    }




}
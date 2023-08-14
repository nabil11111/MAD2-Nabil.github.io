package com.example.mathsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathsapplication.Home;
import com.example.mathsapplication.R;

import java.util.Random;

public class Level0 extends AppCompatActivity {

    TextView score_counter;
    TextView quiz_counter;
    TextView quiz_text;

    Button btn_opt1;
    Button btn_opt2;
    Button btn_opt3;
    Button btn_opt4;

    int quiz = 0;
    int score = 0;
    int rightAnswer = 0;
    String realOperation = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level0);

        score_counter = findViewById(R.id.score_counter);
        quiz_counter = findViewById(R.id.quiz_counter);
        quiz_text = findViewById(R.id.quiz_text);

        btn_opt1 = findViewById(R.id.opt1);
        btn_opt2 = findViewById(R.id.opt2);
        btn_opt3 = findViewById(R.id.opt3);
        btn_opt4 = findViewById(R.id.opt4);

        quiz_counter.setText("QUIZ : " + quiz + " / 10");
        score_counter.setText("SCORE " + score + " / 10");

        if (quiz < 10) {
            Randomquiz();
        }
    }
    private void Randomquiz(){

        btn_opt1.setBackgroundResource(R.drawable.buttons_option_bg);
        btn_opt2.setBackgroundResource(R.drawable.buttons_option_bg);
        btn_opt3.setBackgroundResource(R.drawable.buttons_option_bg);
        btn_opt4.setBackgroundResource(R.drawable.buttons_option_bg);

        //generates random numbers between 1 and 12
        int num1 = new Random().nextInt(12) +1;
        int num2 = new Random().nextInt(12) +1;

        //operator selector
        int operator = new Random().nextInt(4)+1;

        //generate random numbers for the other 3 options excluding the correct one
        // and making sure it doesn't accidently generate the correct answer too.
        int opt1, opt2 , opt3;

        do {
            opt1 = new Random().nextInt(40)+1; // Generate random number
        } while (opt1 == rightAnswer);

        do {
            opt2 = new Random().nextInt(75)+1; // Generate random number
        } while (opt2 == rightAnswer || opt2 == opt1);

        do {
            opt3 = new Random().nextInt(100)+1; // Generate random number
        } while (opt3 == rightAnswer || opt3 == opt2 || opt3==opt1 || opt2 == opt1);



        if (operator == 1) {
            realOperation = "+";
            rightAnswer = num1 + num2;
            quiz_text.setText(num1 + " " + realOperation + " " + num2 + " = ?");
        } else {
            if (operator == 2) {
                realOperation = "*";
                rightAnswer = num1 * num2;
                quiz_text.setText(num1 + " " + realOperation + " " + num2 + " = ?");
            } else {
                if (operator == 3) {
                    realOperation = "-";

                    if (num1 < num2) {
                        rightAnswer = num2 - num1;
                        quiz_text.setText(num2 + "  " + realOperation + "  " + num1 + " = ?");
                    } else {
                        rightAnswer = num1 - num2;
                        quiz_text.setText(num1 + "  " + realOperation + "  " + num2 + " = ?");
                    }
                }else {
                    if (operator==4){
                        realOperation = "/";
                        // Ensure the second number is not zero to avoid division by zero
                        do {
                            num2 = new Random().nextInt(10) + 1;
                        } while (num1 % num2 != 0);

                        rightAnswer = num1 / num2;
                        quiz_text.setText(num1 + "  " + realOperation + "  " + num2 + " = ?");
                    }
                }
            }
        }

        int position = new Random().nextInt(4) + 1; // 1 or 2 or 3 position

        if (position == 1) {
            btn_opt1.setText("" + rightAnswer);
            btn_opt2.setText("" + opt1);
            btn_opt3.setText("" + opt2);
            btn_opt4.setText("" + opt3);
        } else if (position == 2) {
            btn_opt1.setText("" + opt1);
            btn_opt2.setText("" + rightAnswer);
            btn_opt3.setText("" + opt2);
            btn_opt4.setText("" + opt3);
        } else if (position == 3) {
            btn_opt1.setText("" + opt1);
            btn_opt2.setText("" + opt2);
            btn_opt3.setText("" + rightAnswer);
            btn_opt4.setText("" + opt3);
        } else {
            btn_opt1.setText("" + opt1);
            btn_opt2.setText("" + opt2);
            btn_opt3.setText("" + opt3);
            btn_opt4.setText("" + rightAnswer);
        }

        btn_opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn_opt1.getText().equals("" + rightAnswer)) {
                    btn_opt1.setBackgroundResource(R.drawable.right_answer_bg);
                    score++; // Increment score only when the answer is correct
                } else {
                    btn_opt1.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                quiz++;
                quiz_counter.setText("QUIZ : " + quiz + " / 10");
                score_counter.setText("SCORE : " + score + " / 10");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (quiz < 10) {
                            Randomquiz();
                        } else {
                            Intent intent = new Intent(Level0.this, Home.class);
                            intent.putExtra("RA", score);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 1000); // 1 sec
            }
        });

        btn_opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn_opt2.getText().equals("" + rightAnswer)) {
                    btn_opt2.setBackgroundResource(R.drawable.right_answer_bg);
                    score++; // Increment score only when the answer is correct
                } else {
                    btn_opt2.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                quiz++;
                quiz_counter.setText("QUIZ : " + quiz + " / 10");
                score_counter.setText("SCORE : " + score + " / 10");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (quiz < 10) {
                            Randomquiz();
                        } else {
                            Intent intent = new Intent(Level0.this, Home.class);
                            intent.putExtra("RA", score);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 1000); // 1 sec
            }
        });

        btn_opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn_opt3.getText().equals("" + rightAnswer)) {
                    btn_opt3.setBackgroundResource(R.drawable.right_answer_bg);
                    score++; // Increment score only when the answer is correct
                } else {
                    btn_opt3.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                quiz++;
                quiz_counter.setText("QUIZ : " + quiz + " / 10");
                score_counter.setText("SCORE : " + score + " / 10");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (quiz < 10) {
                            Randomquiz();
                        } else {
                            Intent intent = new Intent(Level0.this, Home.class);
                            intent.putExtra("RA", score);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 1000); // 1 sec
            }
        });

        btn_opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn_opt4.getText().equals("" + rightAnswer)) {
                    btn_opt4.setBackgroundResource(R.drawable.right_answer_bg);
                    score++; // Increment score only when the answer is correct
                } else {
                    btn_opt4.setBackgroundResource(R.drawable.wrong_answer_bg);
                }
                quiz++;
                quiz_counter.setText("QUIZ : " + quiz + " / 10");
                score_counter.setText("SCORE : " + score + " / 10");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (quiz < 10) {
                            Randomquiz();
                        } else {
                            Intent intent = new Intent(Level0.this, Home.class);
                            intent.putExtra("RA", score);
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 1000); // 1 sec
            }
        });
    }
}
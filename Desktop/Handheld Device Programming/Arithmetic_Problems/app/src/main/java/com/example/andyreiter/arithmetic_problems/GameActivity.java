package com.example.andyreiter.arithmetic_problems;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity { // BEGINNING of GameActivity Class!

    boolean easyDifficulty = false;
    boolean mediumDifficulty = false;
    boolean hardDifficulty = false;
    TextView equationView;
    TextView timerCountdown;
    TextView correctCount;
    TextView resultText;
    Button answerOne;
    Button answerTwo;
    Button answerThree;
    Button answerFour;
    Button startButton;

    boolean isRunning = true;
    boolean buttonOneClicked = false;
    boolean buttonTwoClicked = false;
    boolean buttonThreeClicked = false;
    boolean buttonFourClicked = false;
    boolean done = false;
    int correctAnswer = 0;
    int correctCounter = 0;
    int totalTries = 0;

    Timer timer;

    ArrayList<Integer> buttonIndexer = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) { // BEGINNING of onCreate Method!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        equationView = (TextView) findViewById(R.id.equationView);
        timerCountdown = (TextView) findViewById(R.id.timerCountdown);
        correctCount = (TextView) findViewById(R.id.correctCount);
        resultText = (TextView) findViewById(R.id.resultText);
        answerOne = (Button) findViewById(R.id.answerOne);
        answerTwo = (Button) findViewById(R.id.answerTwo);
        answerThree = (Button) findViewById(R.id.answerThree);
        answerFour = (Button) findViewById(R.id.answerFour);
        startButton = (Button) findViewById(R.id.startButton);

        equationView.setVisibility(View.INVISIBLE);
        timerCountdown.setVisibility(View.INVISIBLE);
        correctCount.setVisibility(View.INVISIBLE);
        resultText.setVisibility(View.INVISIBLE);
        answerOne.setVisibility(View.INVISIBLE);
        answerTwo.setVisibility(View.INVISIBLE);
        answerThree.setVisibility(View.INVISIBLE);
        answerFour.setVisibility(View.INVISIBLE);


        buttonIndexer.add(0);
        buttonIndexer.add(1);
        buttonIndexer.add(2);
        buttonIndexer.add(3);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (done){
                    finish();
                }else {
                    handleStartButton();
                }
            }
        });
    } // END of onCreate method!

    public void handleStartButton(){ // BEGINNING of handleStartButton Method
        Intent intent = getIntent();
        String difficulty = intent.getExtras().getString("difficulty");
        String symbol = intent.getExtras().getString("symbol");
        timerCountdown.setVisibility(View.VISIBLE);
        correctCount.setVisibility(View.VISIBLE);
        switch (difficulty) {
            case "Easy":
                easyDifficulty = true;
                System.out.println("About to go to Easy Equations.");
                break;
            case "Medium":
                mediumDifficulty = true;
                mediumEquations(symbol);
                break;
            case "Hard":
                hardDifficulty = true;
                hardEquations(symbol);
                break;
        }
        System.out.println("YOYO DIFFICULTY: " + difficulty);

        new CountDownTimer(30000, 1000) {// countdown 10 secs every one sec

            public void onTick(long millisecondsUntilDone) {

                //done evey second
//                Log.i("Seconds Left", ""+millisecondsUntilDone);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisecondsUntilDone);
                String toDisplay = Long.toString(seconds);
                timerCountdown.setText(toDisplay);
                isRunning = true;
            }
            public void onFinish() {
                //done when done after 10 seconds

                equationView.setVisibility(View.INVISIBLE);
                timerCountdown.setVisibility(View.INVISIBLE);
                correctCount.setVisibility(View.INVISIBLE);
                resultText.setVisibility(View.INVISIBLE);
                answerOne.setVisibility(View.INVISIBLE);
                answerTwo.setVisibility(View.INVISIBLE);
                answerThree.setVisibility(View.INVISIBLE);
                answerFour.setVisibility(View.INVISIBLE);
                startButton.setText("Back to Home!");
                startButton.setVisibility(View.VISIBLE);
                isRunning = false;
                done = true;
            }

        }.start();

        if (easyDifficulty){
            easyEquations(symbol);
        }
        else if (mediumDifficulty){
            mediumEquations(symbol);
        }
        else if(hardDifficulty){
            hardEquations(symbol);
        }
    } // END of handleStartButton Method!

    public void easyEquations(final String symbol){ // BEGINNING of easyEquations Method!
        if (isRunning) {
            int maxNum = 15;

            buttonOneClicked = false;
            buttonTwoClicked = false;
            buttonThreeClicked = false;
            buttonFourClicked = false;

            System.out.println("In Easy Equations!");

            equationView.setVisibility(View.VISIBLE);

            resultText.setVisibility(View.VISIBLE);
            answerOne.setVisibility(View.VISIBLE);
            answerTwo.setVisibility(View.VISIBLE);
            answerThree.setVisibility(View.VISIBLE);
            answerFour.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            correctAnswer = 0;

            Random randomNum = new Random();
            int firstNum = randomNum.nextInt(maxNum);
            int secondNum = randomNum.nextInt(maxNum);
            int correctIndex = randomNum.nextInt(buttonIndexer.size());

            boolean plus = false;
            boolean minus = false;
            boolean times = false;
            boolean divide = false;
            switch (symbol) {
                case "Plus":
                    plus = true;
                    break;
                case "Minus":
                    minus = true;
                    break;
                case "Times":
                    times = true;
                    break;
                case "Divide":
                    divide = true;
                    break;
            }
            if (plus) {
                String equation = firstNum + " + " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum + secondNum;
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 5);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 5);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 5);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                String correct = Integer.toString(correctAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            } else if (minus) {
                String equation = firstNum + " - " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum - secondNum;
                String correct = Integer.toString(correctAnswer);
                int firstRandAnswer = randomNum.nextInt(maxNum);
                int secondRandAnswer = randomNum.nextInt(maxNum);
                int thirdRandAnswer = randomNum.nextInt(maxNum);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            } else if (times) {
                String equation = firstNum + " * " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum * secondNum;
                String correct = Integer.toString(correctAnswer);
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 20);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 20);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 20);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }

            } else if (divide) {
                String equation = firstNum + " / " + secondNum;
                equationView.setText(equation);
                do {
                    firstNum = randomNum.nextInt(maxNum);
                    secondNum = randomNum.nextInt(2) + 1;
                } while (firstNum % secondNum != 0);

                correctAnswer = firstNum / secondNum;
                String correct = Integer.toString(correctAnswer);
                System.out.println("CORRECT ANSWER: " + correct);
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 10);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 10);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 10);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            }

            answerOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerOne.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    easyEquations(symbol);
                }
            });
            answerTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerTwo.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    easyEquations(symbol);
                }
            });
            answerThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerThree.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    easyEquations(symbol);
                }
            });
            answerFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerFour.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    easyEquations(symbol);
                }
            });
        }

    } // END of easyEquations Method!


    public void checkAnswer(int correctAnswer, int userAnswer){ // BEGINNING of checkAnswer Method
            if (correctAnswer == userAnswer){
                correctCounter++;
                totalTries++;
                String toDisplay = correctCounter + "/" + totalTries;
                correctCount.setText(toDisplay);
                resultText.setText("Correct!");
            }
            else{
                totalTries++;
                String toDisplay = correctCounter + "/" + totalTries;
                correctCount.setText(toDisplay);
                resultText.setText("Incorrect!");
            }
    } // END of checkAnswer Method!

    public void mediumEquations(final String symbol){ // BEGINNING of mediumEquations Method!

        if (isRunning) {
            int maxNum = 25;

            equationView.setVisibility(View.VISIBLE);
            timerCountdown.setVisibility(View.VISIBLE);
            correctCount.setVisibility(View.VISIBLE);
            resultText.setVisibility(View.VISIBLE);
            answerOne.setVisibility(View.VISIBLE);
            answerTwo.setVisibility(View.VISIBLE);
            answerThree.setVisibility(View.VISIBLE);
            answerFour.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            correctAnswer = 0;

            Random randomNum = new Random();
            int firstNum = randomNum.nextInt(maxNum);
            int secondNum = randomNum.nextInt(maxNum);
            int correctIndex = randomNum.nextInt(buttonIndexer.size());

            boolean plus = false;
            boolean minus = false;
            boolean times = false;
            boolean divide = false;
            switch (symbol) {
                case "Plus":
                    plus = true;
                    break;
                case "Minus":
                    minus = true;
                    break;
                case "Times":
                    times = true;
                    break;
                case "Divide":
                    divide = true;
                    break;
            }
            if (plus) {
                String equation = firstNum + " + " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum + secondNum;
                String correct = Integer.toString(correctAnswer);
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 5);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 5);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 5);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            } else if (minus) {
                String equation = firstNum + " - " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum - secondNum;
                String correct = Integer.toString(correctAnswer);
                int firstRandAnswer = randomNum.nextInt(maxNum);
                int secondRandAnswer = randomNum.nextInt(maxNum);
                int thirdRandAnswer = randomNum.nextInt(maxNum);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            } else if (times) {
                String equation = firstNum + " * " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum * secondNum;
                String correct = Integer.toString(correctAnswer);
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 20);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 20);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 20);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }

            } else if (divide) {
                String equation = firstNum + " / " + secondNum;
                equationView.setText(equation);
                do {
                    firstNum = randomNum.nextInt(maxNum);
                    secondNum = randomNum.nextInt(10) + 1;
                } while (firstNum % secondNum != 0);

                correctAnswer = firstNum / secondNum;
                String correct = Integer.toString(correctAnswer);
                System.out.println("CORRECT ANSWER: " + correct);
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 10);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 10);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 10);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            }

            answerOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerOne.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    mediumEquations(symbol);
                }
            });
            answerTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerTwo.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    mediumEquations(symbol);
                }
            });
            answerThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerThree.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    mediumEquations(symbol);
                }
            });
            answerFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerFour.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    mediumEquations(symbol);
                }
            });
        }
    } // END of mediumEquations Method!

    public void hardEquations(final String symbol){ // BEGINNING of hardEquations Method!
        if (isRunning) {
            int maxNum = 100;

            equationView.setVisibility(View.VISIBLE);
            timerCountdown.setVisibility(View.VISIBLE);
            correctCount.setVisibility(View.VISIBLE);
            resultText.setVisibility(View.VISIBLE);
            answerOne.setVisibility(View.VISIBLE);
            answerTwo.setVisibility(View.VISIBLE);
            answerThree.setVisibility(View.VISIBLE);
            answerFour.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.INVISIBLE);
            correctAnswer = 0;

            Random randomNum = new Random();
            int firstNum = randomNum.nextInt(maxNum);
            int secondNum = randomNum.nextInt(maxNum);
            int correctIndex = randomNum.nextInt(buttonIndexer.size());

            boolean plus = false;
            boolean minus = false;
            boolean times = false;
            boolean divide = false;
            switch (symbol) {
                case "Plus":
                    plus = true;
                    break;
                case "Minus":
                    minus = true;
                    break;
                case "Times":
                    times = true;
                    break;
                case "Divide":
                    divide = true;
                    break;
            }
            if (plus) {
                String equation = firstNum + " + " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum + secondNum;
                String correct = Integer.toString(correctAnswer);
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 5);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 5);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 5);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            } else if (minus) {
                String equation = firstNum + " - " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum - secondNum;
                String correct = Integer.toString(correctAnswer);
                int firstRandAnswer = randomNum.nextInt(maxNum);
                int secondRandAnswer = randomNum.nextInt(maxNum);
                int thirdRandAnswer = randomNum.nextInt(maxNum);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            } else if (times) {
                String equation = firstNum + " * " + secondNum;
                equationView.setText(equation);
                correctAnswer = firstNum * secondNum;
                String correct = Integer.toString(correctAnswer);
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 20);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 20);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 20);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }

            } else if (divide) {
                String equation = firstNum + " / " + secondNum;
                equationView.setText(equation);
                do {
                    firstNum = randomNum.nextInt(maxNum);
                    secondNum = randomNum.nextInt(5) + 1;

                } while (firstNum % secondNum != 0);

                correctAnswer = firstNum / secondNum;
                String correct = Integer.toString(correctAnswer);
                System.out.println("CORRECT ANSWER: " + correct);
                int firstRandAnswer = randomNum.nextInt(correctAnswer + 10);
                int secondRandAnswer = randomNum.nextInt(correctAnswer + 10);
                int thirdRandAnswer = randomNum.nextInt(correctAnswer + 10);
                String randOne = Integer.toString(firstRandAnswer);
                String randTwo = Integer.toString(secondRandAnswer);
                String randThree = Integer.toString(thirdRandAnswer);
                switch (correctIndex) {
                    case 0:
                        answerOne.setText(correct);
                        answerTwo.setText(randOne);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 1:
                        answerOne.setText(randOne);
                        answerTwo.setText(correct);
                        answerThree.setText(randTwo);
                        answerFour.setText(randThree);
                        break;
                    case 2:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(correct);
                        answerFour.setText(randThree);
                        break;
                    case 3:
                        answerOne.setText(randOne);
                        answerTwo.setText(randTwo);
                        answerThree.setText(randThree);
                        answerFour.setText(correct);
                        break;
                }
            }
            answerOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerOne.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    hardEquations(symbol);
                }
            });
            answerTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerTwo.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    hardEquations(symbol);
                }
            });
            answerThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerThree.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    hardEquations(symbol);
                }
            });
            answerFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String answer = answerFour.getText().toString();
                    int userAnswer = Integer.parseInt(answer);
                    checkAnswer(correctAnswer, userAnswer);
                    hardEquations(symbol);
                }
            });
        }

    } // END of hardEquations Method!
} // END of GameActivity class!

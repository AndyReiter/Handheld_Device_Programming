package com.example.andyreiter.arithmetic_problems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { // BEGINNING of MainActivity Class!
    boolean plusChosen = false;
    boolean minusChosen = false;
    boolean timesChosen = false;
    boolean divideChosen = false;
    boolean easy = false;
    boolean medium = false;
    boolean hard = false;

    String symbol = "";
    String radioOption = "";

    boolean goodToStart = false;
    RadioButton radioEasy;
    RadioButton radioMedium;
    RadioButton radioHard;
    RadioGroup radioGroup;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // BEGINNING of onCreate Method!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioEasy = (RadioButton) findViewById(R.id.radioEasy);
        radioMedium = (RadioButton) findViewById(R.id.radioMedium);
        radioHard = (RadioButton) findViewById(R.id.radioHard);
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleStartButtonClicked();
            }
        });

    } // END OF onCreate method
    public void handleStartButtonClicked(){ // BEGINNING of handleStartButtonClicked Method
        if (plusChosen == false && minusChosen == false && timesChosen == false && divideChosen == false){
            // no option has been selected yet
            Toast.makeText(this, "Please Make Sure To Select An Arithmetic Option", Toast.LENGTH_SHORT).show();
            goodToStart = false;
        }
        else{
            // an option was indeed selected!
            goodToStart = true;
        }

        if (goodToStart == true){
            Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
            gameIntent.putExtra("difficulty", radioOption);
            gameIntent.putExtra("symbol", symbol);
            startActivity(gameIntent);
        }
    } // END of handleStartButtonClicked method

    @Override
    public boolean onCreateOptionsMenu(Menu menu){ // BEGINNING of onCreateOptionsMenu Method!
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.homepage_menu, menu);

        return true;
    } // END of onCreateOptionsMenu method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // BEGINNING of onOptionsItemSelected Method!
        switch (item.getItemId()){
            case R.id.plusOption:
                System.out.println("PLUS OPTION");
                plusChosen = true;
                minusChosen = false;
                timesChosen = false;
                divideChosen = false;
                symbol = "Plus";
                return true;
            case R.id.minusOption:
                plusChosen = false;
                minusChosen = true;
                timesChosen = false;
                divideChosen = false;
                System.out.println("MINUS OPTION");
                symbol = "Minus";
                return true;
            case R.id.timesOption:
                plusChosen = false;
                minusChosen = false;
                timesChosen = true;
                divideChosen = false;
                System.out.println("TIMES OPTION");
                symbol = "Times";
                return true;
            case R.id.divideOption:
                plusChosen = false;
                minusChosen = false;
                timesChosen = false;
                divideChosen = true;
                System.out.println("DIVIDE OPTION");
                symbol = "Divide";
                return true;
        }

        return super.onOptionsItemSelected(item);
    } // END of onOptionsItemSelected method


    public void onRadioButtonClicked(View view){ // BEGINNING of onRadioButtonClicked Method!
        boolean checked = ((RadioButton) view).isChecked();
        String str = "";
        switch (view.getId()){
            case R.id.radioEasy:
                if (checked){
                    radioOption = "Easy";
                    easy = true;
                    medium = false;
                    hard = false;
                }
                break;
            case R.id.radioMedium:
                if (checked){
                    radioOption = "Medium";
                    easy = false;
                    medium = true;
                    hard = false;
                }
                break;
            case R.id.radioHard:
                if (checked){
                    radioOption = "Hard";
                    easy = false;
                    medium = false;
                    hard = true;
                }
        }
    } // END of onRadioButtonClicked


} // END of MainActivity Class!

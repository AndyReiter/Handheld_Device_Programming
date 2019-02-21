package com.example.andyreiter.homework_3_rock;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import java.util.ArrayList;

import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ListView songGenres;
    ListView rapSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songGenres = (ListView) findViewById(R.id.musicGenres);
        ArrayList<String> ourGenres = new ArrayList<String>();
        ourGenres.add("Rap");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ourGenres){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#FFFFFF"));
                item.setTypeface(null, Typeface.BOLD);
                item.setTextSize(20);

                return item;
            }
        };
        songGenres.setAdapter(arrayAdapter);
        songGenres.setClickable(true);
        songGenres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    nowUsingRap();
                }
            }
        }
        );
    }

    public void nowUsingRap(){
        System.out.println("NOW IN USING RAP");
        Intent intent = new Intent(MainActivity.this, RapActivity.class);
        startActivity(intent);
    }
}


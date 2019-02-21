package com.example.andyreiter.homework_3_rock;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class RapActivity extends AppCompatActivity {

    ListView rapArtistList;
    SeekBar volumeBar;
    Button playButton;
    Button pauseButton;
    TextView volumeLabel;
    AudioManager audioManager;
    TextView directionView;
    TextView pageLabel;
    MediaPlayer mediaPlayer;
    Button newSong;
    VideoView videoView;
    WebView hipHopHeads;


    int maxVolume;
    int currentVolume;
    boolean changed = false;
    ArrayList<Integer> intArray = new ArrayList<Integer>();
    ArrayList<String> theRapArtists = new ArrayList<String>();
    ArrayList<String> thugTopSongs = new ArrayList<String>();
    ArrayList<String> cartiTopSongs = new ArrayList<String>();
    ArrayList<String> travTopSongs = new ArrayList<String>();
    ArrayList<String> topMusicVids = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rap);

        rapArtistList = (ListView) findViewById(R.id.rapArtistList);
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        playButton = (Button) findViewById(R.id.playButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        volumeLabel = (TextView) findViewById(R.id.volumeLabel);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        directionView = (TextView) findViewById(R.id.directionView);
        pageLabel = (TextView) findViewById(R.id.pageLabel);
        newSong = (Button) findViewById(R.id.newSong);
        videoView = (VideoView) findViewById(R.id.musicVideoView);

        videoView.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
        volumeBar.setVisibility(View.INVISIBLE);
        volumeLabel.setVisibility(View.INVISIBLE);
        newSong.setVisibility(View.INVISIBLE);

        ArrayList<String> theOptions = new ArrayList<String>();
        theOptions.add("Andy's Top 3 Favorite Artists To Listen To!");
        theOptions.add("Andy's Favorite Music Video of All Time");
        theOptions.add("How To Stay Up To Date With All Things Rap");

        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, theOptions){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#D3D3D3"));
                item.setTextSize(18);
                item.setTypeface(null, Typeface.BOLD);
                return item;
            }
        };

        rapArtistList.setAdapter(optionsAdapter);
        rapArtistList.setClickable(true);
        pageLabel.setText("Andy's Rap Home Page!");
        directionView.setText("Please Select An Option From The List Below!");
        directionView.setTextSize(18);

        rapArtistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    handleOption(position);
                    // this is the first item in the array.
                    // load up High by Young Thug.
            }
        });
    } // end of onCreate()

    public void handleOption(int optionPicked){
        if (optionPicked == 0){
            handleArtist(optionPicked);
        }
        else if (optionPicked == 1){
            // second option was picked. Time to Display my top 3 Favorite Music Videos.
            handleMusicVideoPicked();
        }
        else if (optionPicked == 2){
            displayHipHopHeads();
            // third option was picked
        }
    }
    public void displayHipHopHeads(){
        hipHopHeads = (WebView) findViewById(R.id.hipHopHeads);
        videoView.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
        volumeBar.setVisibility(View.INVISIBLE);
        volumeLabel.setVisibility(View.INVISIBLE);
        newSong.setVisibility(View.INVISIBLE);
        rapArtistList.setVisibility(View.INVISIBLE);
        hipHopHeads.setVisibility(View.VISIBLE);

        hipHopHeads.getSettings().setJavaScriptEnabled(true);
        hipHopHeads.loadUrl("https://reddit.com/r/hiphopheads");
    }
    public void handleMusicVideoPicked(){
        pageLabel.setText("");
        pageLabel.setText("Music Video!");
        directionView.setText("Please Select Otis Then Press Play To Watch!");
        topMusicVids.add("Otis - Kanye West, Jay-Z");
        ArrayAdapter<String> topMusicVidAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, topMusicVids){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#D3D3D3"));
                item.setTypeface(null, Typeface.BOLD);
                return item;
            }
        };
        rapArtistList.setAdapter(topMusicVidAdapter);
        rapArtistList.setClickable(true);
        rapArtistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleMusicVidPicked(position);
                // this is the first item in the array.
                // load up High by Young Thug.
            }
        });
    }
    public void handleMusicVidPicked(int pos){
        switch(pos){
            case 0:
                handleOtis();
                break;
        }
    }

    public void handleOtis(){
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
        volumeBar.setVisibility(View.VISIBLE);
        volumeLabel.setVisibility(View.VISIBLE);

        final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //mediaPlayer = MediaPlayer.create(this, R.raw.otis_music_video);

        videoView.setVideoPath("android.resource://"+getPackageName() + "/" + R.raw.otis_music_video);
        volumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVisibility(View.VISIBLE);
                directionView.setVisibility(View.INVISIBLE);
                videoView.start();
                //mediaPlayer.start();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
                //mediaPlayer.pause();
            }
        });


    }
    public void handleArtistPicked(int who){
        switch(who){
            case 0:
                handleThug();
                break;
            case 1:
                handleCarti();
                break;
            case 2:
                handleTrav();
                break;
        }
    }
    public void handleThugSongPicked(int song){

        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
        volumeBar.setVisibility(View.VISIBLE);
        volumeLabel.setVisibility(View.VISIBLE);

        if (song == 0) {
            mediaPlayer = MediaPlayer.create(this, R.raw.high);
        }
        else if (song == 1){
            mediaPlayer = MediaPlayer.create(this, R.raw.family_dont);
        }
        else if (song == 2){
            mediaPlayer = MediaPlayer.create(this, R.raw.floyd_mayweather);
        }
        else if (song == 3){
            mediaPlayer = MediaPlayer.create(this, R.raw.group_home);
        }
        else if (song == 4){
            mediaPlayer = MediaPlayer.create(this, R.raw.tomorrow_til);
        }

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        volumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                newSong.setVisibility(View.VISIBLE);
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        newSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }
    public void handleThug(){
        pageLabel.setText("");
        pageLabel.setText("Andy's Top 5 Favorite Songs By Young Thug!");
        directionView.setText("Please Now Select A Song To Listen To! ==>Warning<==: Songs do contain Explicit Lyrics!");
        thugTopSongs.add("High (feat. Elton John)");
        thugTopSongs.add("Family Don't Matter (feat. Millie Go Lightly)");
        thugTopSongs.add("Floyd Mayweather (feat. Travis Scott, Gucci Mane, and Gunna)");
        thugTopSongs.add("Group Home (feat. Future)");
        thugTopSongs.add("Tomorrow Til Infinity (feat. Millie Go Lightly)");
        ArrayAdapter<String> thugAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, thugTopSongs){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#D3D3D3"));
                item.setTypeface(null, Typeface.BOLD);
                return item;
            }
        };
        rapArtistList.setAdapter(thugAdapter);
        rapArtistList.setClickable(true);
        rapArtistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleThugSongPicked(position);
                // this is the first item in the array.
                // load up High by Young Thug.
            }
        });
    }
    public void handleCarti(){
        pageLabel.setText("");
        pageLabel.setText("Andy's Top 5 Favorite Songs By Playboicarti!");
        directionView.setText("Please Now Select A Song To Listen To! ==>Warning<==: Songs do contain Explicit Lyrics!");
        cartiTopSongs.add("Love Hurts (feat. Travis Scott)");
        cartiTopSongs.add("Shoota (feat. Lil Uzi Vert)");
        cartiTopSongs.add("Lookin (feat. Lil Uzi Vert)");
        cartiTopSongs.add("R.I.P Fredo (feat. Young Nudy) - Notice Me");
        cartiTopSongs.add("Fell In Luv (feat. Bryson Tiller)");
        ArrayAdapter<String> cartiAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cartiTopSongs){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#D3D3D3"));
                item.setTypeface(null, Typeface.BOLD);
                return item;
            }
        };
        rapArtistList.setAdapter(cartiAdapter);
        rapArtistList.setClickable(true);
        rapArtistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleCartiSongPicked(position);
                // this is the first item in the array.
                // load up High by Young Thug.
            }
        });
    }
    public void handleCartiSongPicked(int pos){
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
        volumeBar.setVisibility(View.VISIBLE);
        volumeLabel.setVisibility(View.VISIBLE);

        if (pos == 0) {
            mediaPlayer = MediaPlayer.create(this, R.raw.love_hurts);
        }
        else if (pos == 1){
            mediaPlayer = MediaPlayer.create(this, R.raw.shoota);
        }
        else if (pos == 2){
            mediaPlayer = MediaPlayer.create(this, R.raw.lookin);
        }
        else if (pos == 3){
            mediaPlayer = MediaPlayer.create(this, R.raw.rip_fredo);
        }
        else if (pos == 4){
            mediaPlayer = MediaPlayer.create(this, R.raw.fell_in_love);
        }

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        volumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                newSong.setVisibility(View.VISIBLE);
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        newSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }


    public void handleTrav(){
        pageLabel.setText("");
        pageLabel.setText("Andy's Top 5 Favorite Songs By Travis Scott!");
        directionView.setText("Please Now Select A Song To Listen To! ==>Warning<==: Songs do contain Explicit Lyrics!");
        travTopSongs.add("Modern Slavery (feat. Travis Scott, Quavo)");
        travTopSongs.add("Outside (feat. 21 Savage)");
        travTopSongs.add("3500 (feat. Future, 2 Chainz)");
        travTopSongs.add("YOSEMITE");
        travTopSongs.add("CAROUSEL (feat. Frank Ocean)");
        ArrayAdapter<String> travAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, travTopSongs){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#D3D3D3"));
                item.setTypeface(null, Typeface.BOLD);
                return item;
            }
        };
        rapArtistList.setAdapter(travAdapter);
        rapArtistList.setClickable(true);
        rapArtistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleTravSongPicked(position);
                // this is the first item in the array.
                // load up High by Young Thug.
            }
        });

    }
    public void handleTravSongPicked(int pos){
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
        volumeBar.setVisibility(View.VISIBLE);
        volumeLabel.setVisibility(View.VISIBLE);

        if (pos == 0) {
            mediaPlayer = MediaPlayer.create(this, R.raw.modern_slavery);
        }
        else if (pos == 1){
            mediaPlayer = MediaPlayer.create(this, R.raw.outside);
        }
        else if (pos == 2){
            mediaPlayer = MediaPlayer.create(this, R.raw.three_five_hunnid);
        }
        else if (pos == 3){
            mediaPlayer = MediaPlayer.create(this, R.raw.yosemite);
        }
        else if (pos == 4){
            mediaPlayer = MediaPlayer.create(this, R.raw.carousel);
        }

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        volumeBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                newSong.setVisibility(View.VISIBLE);
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        newSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
    }
    public void handleArtist(int location){
        pageLabel.setText("");
        pageLabel.setText("Select An Artist");
        directionView.setText("Please Select An Artist To See My Top 5 Songs By Them!");
        theRapArtists.add("Young Thug");
        theRapArtists.add("Playboycari!");
        theRapArtists.add("Travis Scott");
        ArrayAdapter<String> theArtistAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, theRapArtists){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#D3D3D3"));
                item.setTypeface(null, Typeface.BOLD);
                return item;
            }
        };
        rapArtistList.setAdapter(theArtistAdapter);
        rapArtistList.setClickable(true);
        rapArtistList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleArtistPicked(position);
                // this is the first item in the array.
                // load up High by Young Thug.
            }
        });

    }
}


/*


package com.example.andyreiter.homework_4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;

public class CollegeActivity extends AppCompatActivity {

    TextView collegePageDescription;
    ListView listOfHappyHours;
    MapView mapView;
    Button displayOnMap;
    Button menuButton;
    Button newRestaurant;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent= getIntent();
        String college = intent.getExtras().getString("college");
        System.out.println("YOYOYYOYOYOYOYOYYO ANDY --> " + college);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.college_layout);
        View view = this.getWindow().getDecorView();

        switch(college){
            case "Georgetown":
                view.setBackground(getDrawable(R.drawable.georgetown_background));
                break;
        }
        collegePageDescription = (TextView) findViewById(R.id.homeScreenDesc);
        listOfHappyHours = (ListView) findViewById(R.id.listView);
        mapView = (MapView) findViewById(R.id.mapView);
        displayOnMap = (Button) findViewById(R.id.displayOnMap);
        menuButton = (Button) findViewById(R.id.menuButton);
        newRestaurant = (Button) findViewById(R.id.newRestaurant);
        webView = (WebView) findViewById(R.id.webView);

        displayOnMap.getBackground().setAlpha(64);
        menuButton.getBackground().setAlpha(64);
        newRestaurant.getBackground().setAlpha(64);

        ArrayList<String> georgetownHappyHours = new ArrayList<String>();
        ArrayList<String> sdsuHappyHours = new ArrayList<String>();
        ArrayList<String> bamaHappyHours = new ArrayList<String>();
        ArrayList<String> ucsbHappyHours = new ArrayList<String>();
        ArrayList<String> uscHappyHours = new ArrayList<String>();

        ArrayAdapter<String> collegeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, georgetownHappyHours){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#FFFFFF"));
                item.setTextSize(16);
                item.setTypeface(null, Typeface.BOLD);
                return item;
            }
        };
        listOfHappyHours.setAdapter(collegeAdapter);
        listOfHappyHours.setClickable(true);

    }
}
*/

/*
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/colPageDesc"
        android:layout_width="347dp"
        android:layout_height="27dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="16dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ListView
        android:id="@+id/happyHours"
        android:layout_width="368dp"
        android:layout_height="91dp"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginBottom="8dp"
        android:footerDividersEnabled="false"
        android:headerDividersEnabled="false"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <com.google.android.gms.maps.MapView
        android:id="@+id/mapView"
        android:layout_width="327dp"
        android:layout_height="272dp"
        android:layout_marginStart="29dp"
        android:layout_marginTop="9dp"
        android:layout_marginEnd="29dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/colPageDesc"
        tools:visibility="invisible" />

    <Button
        android:id="@+id/displayOnMap"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="29dp"
        android:layout_marginTop="23dp"
        android:layout_marginBottom="17dp"
        android:text="Display On Map"
        android:textSize="10sp"
        app:layout_constraintBottom_toTopOf="@+id/happyHours"
        app:layout_constraintEnd_toStartOf="@+id/menuButton"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/mapView" />

    <Button
        android:id="@+id/menuButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="114dp"
        android:layout_marginTop="23dp"
        android:layout_marginEnd="28dp"
        android:layout_marginBottom="17dp"
        android:text="Display HH Menu"
        android:textSize="10sp"
        app:layout_constraintBottom_toTopOf="@+id/happyHours"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/displayOnMap"
        app:layout_constraintTop_toBottomOf="@+id/mapView" />

    <WebView
        android:id="@+id/webView"
        android:layout_width="327dp"
        android:layout_height="272dp"
        android:layout_marginStart="29dp"
        android:layout_marginTop="9dp"
        android:layout_marginEnd="29dp"
        android:layout_marginBottom="88dp"
        android:visibility="invisible"
        app:layout_constraintBottom_toTopOf="@+id/happyHours"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/colPageDesc"
        app:layout_constraintVertical_bias="1.0" />

    <Button
        android:id="@+id/newRestaurant"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="15dp"
        android:layout_marginTop="23dp"
        android:layout_marginEnd="12dp"
        android:layout_marginBottom="17dp"
        android:text="New Place!"
        android:textSize="10sp"
        app:layout_constraintBottom_toTopOf="@+id/happyHours"
        app:layout_constraintEnd_toStartOf="@+id/menuButton"
        app:layout_constraintStart_toEndOf="@+id/displayOnMap"
        app:layout_constraintTop_toBottomOf="@+id/mapView" />

</android.support.constraint.ConstraintLayout>

*/
package com.example.andyreiter.real_memorable_places;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mapArrayList = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    ListView mapListView;
    double thisLat;
    double thisLong;
    boolean delete = false;
    String locationToAdd = "";
    boolean fromMap = false;
    ArrayList<ArrayList<Double>> listOfLatAndLong = new ArrayList<ArrayList<Double>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mapListView = (ListView) findViewById(R.id.mapListView);
        mapArrayList.add("Add a new place...");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mapArrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#000000"));
                item.setTextSize(18);
                return item;
            }
        };

        mapListView.setAdapter(arrayAdapter);
        mapListView.setClickable(true);


        mapListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleOption(position);
                // this is the first item in the array.
                // load up High by Young Thug.
            }
        });
    } // END OF ON CREATE METHOD

    public void handleOption(int position){
        if (position == 0){
            ArrayList<Double> temp = new ArrayList<Double>();
            temp.add(0.0);
            temp.add(0.0);
            listOfLatAndLong.add(temp);
            // user selected to add a new location to the map.
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            startActivityForResult(mapIntent, 1);
        }
        if (position == 1){
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            double latToPass;
            double longToPass;
            ArrayList<Double> tempList = listOfLatAndLong.get(position);
            latToPass = tempList.get(0);
            longToPass = tempList.get(1);
            System.out.println("YOYOYOYOYOYOYO ANDY =======> " + latToPass + " yewwww " + longToPass);
            mapIntent.putExtra("Lat", latToPass);
            mapIntent.putExtra("Long", longToPass);
            startActivity(mapIntent);
        }
        if (position == 2){
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            double latToPass;
            double longToPass;
            ArrayList<Double> tempList = listOfLatAndLong.get(position);
            latToPass = tempList.get(0);
            longToPass = tempList.get(1);
            System.out.println("YOYOYOYOYOYOYO ANDY =======> " + latToPass + " yewwww " + longToPass);
            mapIntent.putExtra("Lat", latToPass);
            mapIntent.putExtra("Long", longToPass);
            startActivity(mapIntent);
        }
        if (position == 3){
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            double latToPass;
            double longToPass;
            ArrayList<Double> tempList = listOfLatAndLong.get(position);
            latToPass = tempList.get(0);
            longToPass = tempList.get(1);
            System.out.println("YOYOYOYOYOYOYO ANDY =======> " + latToPass + " yewwww " + longToPass);
            mapIntent.putExtra("Lat", latToPass);
            mapIntent.putExtra("Long", longToPass);
            startActivity(mapIntent);
        }
        if (position == 4){
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            double latToPass;
            double longToPass;
            ArrayList<Double> tempList = listOfLatAndLong.get(position);
            latToPass = tempList.get(0);
            longToPass = tempList.get(1);
            System.out.println("YOYOYOYOYOYOYO ANDY =======> " + latToPass + " yewwww " + longToPass);
            mapIntent.putExtra("Lat", latToPass);
            mapIntent.putExtra("Long", longToPass);
            startActivity(mapIntent);
        }
        if (position == 5){
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            double latToPass;
            double longToPass;
            ArrayList<Double> tempList = listOfLatAndLong.get(position);
            latToPass = tempList.get(0);
            longToPass = tempList.get(1);
            System.out.println("YOYOYOYOYOYOYO ANDY =======> " + latToPass + " yewwww " + longToPass);
            mapIntent.putExtra("Lat", latToPass);
            mapIntent.putExtra("Long", longToPass);
            startActivity(mapIntent);
        }
        if (position == 6){
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            double latToPass;
            double longToPass;
            ArrayList<Double> tempList = listOfLatAndLong.get(position);
            latToPass = tempList.get(0);
            longToPass = tempList.get(1);
            System.out.println("YOYOYOYOYOYOYO ANDY =======> " + latToPass + " yewwww " + longToPass);
            mapIntent.putExtra("Lat", latToPass);
            mapIntent.putExtra("Long", longToPass);
            startActivity(mapIntent);
        }
        if (position == 7){
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            double latToPass;
            double longToPass;
            ArrayList<Double> tempList = listOfLatAndLong.get(position);
            latToPass = tempList.get(0);
            longToPass = tempList.get(1);
            System.out.println("YOYOYOYOYOYOYO ANDY =======> " + latToPass + " yewwww " + longToPass);
            mapIntent.putExtra("Lat", latToPass);
            mapIntent.putExtra("Long", longToPass);
            startActivity(mapIntent);
        }
        if (position == 8){
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            double latToPass;
            double longToPass;
            ArrayList<Double> tempList = listOfLatAndLong.get(position);
            latToPass = tempList.get(0);
            longToPass = tempList.get(1);
            System.out.println("YOYOYOYOYOYOYO ANDY =======> " + latToPass + " yewwww " + longToPass);
            mapIntent.putExtra("Lat", latToPass);
            mapIntent.putExtra("Long", longToPass);
            startActivity(mapIntent);
        }

    } // END OF HANDLE OPTION METHOD


    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
            }
            else {
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    } // END OF GET COMPLETE ADDRESS STRING

    public void updateScreen(){
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mapArrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView item = (TextView) super.getView(position, convertView, parent);
                item.setTextColor(Color.parseColor("#000000"));
                item.setTextSize(18);
                return item;
            }
        };

        mapListView.setAdapter(arrayAdapter);
        mapListView.setClickable(true);


        mapListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleOption(position);
                // this is the first item in the array.
                // load up High by Young Thug.
            }
        });
        mapListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Do you wish to delete this address?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                handleDeletion(position);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog andyAlert = builder1.create();
                andyAlert.show();
                return true;
            }
        });
    } // END OF UPDATE SCREEN METHOD

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                fromMap = true;
                thisLat = data.getExtras().getDouble("lat");
                thisLong = data.getExtras().getDouble("long");
                String toAdd = getCompleteAddressString(thisLat, thisLong);
                ArrayList<Double> latLong = new ArrayList<Double>();
                latLong.add(thisLat);
                latLong.add(thisLong);
                listOfLatAndLong.add(latLong);
                mapArrayList.add(toAdd);
                updateScreen();
            }
        }

    } // END OF ON ACTIVITY RESULT METHOD

    public void handleDeletion(int location){
        System.out.println("DELETING --> " + location);
        mapArrayList.remove(location);
        listOfLatAndLong.remove(location);
        updateScreen();
    } // END OF HANDLE DELETION METHOD

}

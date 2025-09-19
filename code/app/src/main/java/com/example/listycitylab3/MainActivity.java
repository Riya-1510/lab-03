package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {"Edmonton", "Vancouver", "Moscow",};

        String[] provinces = {"AB", "BC", "ON"};

        dataList = new ArrayList<>();
        for (int i =0; i<cities.length; i++){
            dataList.add(new City(cities[i],provinces[i]));
        }
        
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(),
                    "Add City");
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City selectedCity = dataList.get(position);
            EditCityFragment editCityFragment = EditCityFragment.newInstance(selectedCity);
            editCityFragment.show(getSupportFragmentManager(), "Edit City");

        });
    }
    public void updateCity(City updatedCity) {
        // Find the city by name and update its details
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getName().equals(updatedCity.getName())) {
                dataList.set(i, updatedCity);  // Replace old city with the updated one
                break;
            }
        }
        cityAdapter.notifyDataSetChanged();  // Refresh the list to show the updated city
    }

}
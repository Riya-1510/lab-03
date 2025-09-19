package com.example.listycitylab3;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class EditCityFragment extends DialogFragment {

    public static EditCityFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);  // Pass the city object as an argument
        EditCityFragment fragment = new EditCityFragment();  // Create a new instance of EditCityFragment
        fragment.setArguments(args);  // Set the arguments (city data) for the fragment
        return fragment;  // Return the fragment instance
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);

        EditText cityNameEditText = view.findViewById(R.id.edit_text_city_name);
        EditText provinceNameEditText = view.findViewById(R.id.edit_text_province_name);
        Button saveButton = view.findViewById(R.id.button_save);

        City city = (City) getArguments().getSerializable("city");

        cityNameEditText.setText(city.getName());
        provinceNameEditText.setText(city.getProvince());


        saveButton.setOnClickListener(v -> {
            String updatedCityName = cityNameEditText.getText().toString();
            String updatedProvinceName = provinceNameEditText.getText().toString();

            city.setName(updatedCityName);
            city.setProvince(updatedProvinceName);

            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).updateCity(city);  // Update the city in the list
            }

            dismiss();
        });
        return view;

    }
}

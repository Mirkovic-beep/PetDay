package com.example.petday;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class infopet extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopet);
        Spinner options = findViewById(R.id.options);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(adapter);
        
        String optionText = options.getSelectedItem().toString();
        int spinnerPosition = adapter.getPosition(optionText);
        options.setSelection(spinnerPosition);


        Bundle receivedObject = getIntent().getExtras();
        Pet receivedPet = (Pet)receivedObject.getSerializable("pet");

        EditText nombre = findViewById(R.id.nombre);
        EditText raza = findViewById(R.id.raza);
        EditText peso = findViewById(R.id.peso);


        nombre.setText(receivedPet.getNombre().toString());
        raza.setText(receivedPet.getRaza().toString());
        peso.setText(Integer.toString(receivedPet.getPeso()));






    }
}
package com.example.petday;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petday.Data.PetContract.PetEntry;
import com.example.petday.Data.PetDbHelper;

import androidx.appcompat.app.AppCompatActivity;

public class alta extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta);

        Spinner options = findViewById(R.id.optionsAlta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(adapter);

        Button botonAlta;

        botonAlta = (Button) findViewById(R.id.botonAlta);

        botonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nombre = findViewById(R.id.nombreAlta);
                EditText raza = findViewById(R.id.razaAlta);
                Spinner options = findViewById(R.id.optionsAlta);
                String optionText = options.getSelectedItem().toString();
                EditText peso = findViewById(R.id.pesoAlta);

                if(nombre.getText().toString().isEmpty()){

                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            "El campo nombre no puede estar vacío", Toast.LENGTH_SHORT);
                    toast1.show();

                }else if(raza.getText().toString().isEmpty()){
                    Toast toast2 = Toast.makeText(getApplicationContext(),
                            "El campo raza no puede estar vacío", Toast.LENGTH_SHORT);
                    toast2.show();
                }else if(peso.getText().toString().isEmpty()){
                    Toast toast3 = Toast.makeText(getApplicationContext(),
                            "El campo peso no puede estar vacío", Toast.LENGTH_SHORT);
                    toast3.show();
                }
                else{
                    callInsertPet();
                }


            }
        });
    }

    public void callInsertPet(){
        EditText nombre = findViewById(R.id.nombreAlta);
        EditText raza = findViewById(R.id.razaAlta);
        Spinner options = findViewById(R.id.optionsAlta);
        String optionText = options.getSelectedItem().toString();
        EditText peso = findViewById(R.id.pesoAlta);

            Pet pet = new Pet (nombre.getText().toString(),raza.getText().toString(),optionText,Integer.parseInt(peso.getText().toString()));
            PetDbHelper petDbHelper = new PetDbHelper(this);
            double test = petDbHelper.insertPet(pet);
            Log.i("ID", "" + test);

        Toast toast4 = Toast.makeText(getApplicationContext(),
                "Mascota " +  test + " dada de alta", Toast.LENGTH_SHORT);
        toast4.show();



    }

}
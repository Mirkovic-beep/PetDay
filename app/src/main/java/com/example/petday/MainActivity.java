package com.example.petday;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petday.Data.PetDbHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PetDbHelper mDbHelper = new PetDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.listaPets);



        ArrayList<Pet> pets = new ArrayList<>();
        pets = mDbHelper.displayDataBaseInfo();



        PetListAdapter adapter = new PetListAdapter(this,R.layout.custom_view,pets);
        list.setAdapter(adapter);

        ArrayList<Pet> finalPets = pets;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

                ArrayList<Pet> pets = new ArrayList<>();
                pets = mDbHelper.displayDataBaseInfo();

               Serializable clickedpet =  pets.get(position);

                Intent info = new Intent(view.getContext(),infopet.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pet",clickedpet);
                info.putExtras(bundle);
                startActivity(info);

            }
        });

        ImageView img;

        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent alta = new Intent(view.getContext(),alta.class);
                startActivity(alta);
            }
        });

    }
}
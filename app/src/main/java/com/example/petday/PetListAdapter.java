package com.example.petday;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class PetListAdapter extends ArrayAdapter<Pet> {


    public PetListAdapter(@NonNull Context context, int resource,ArrayList<Pet> pets) {
        super(context, resource,pets);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_view, parent, false);
        }

        Pet pets = getItem(position);

        TextView nombre = (TextView) convertView.findViewById(R.id.Nombre);
        nombre.setText(pets.getNombre());

        TextView raza = (TextView) convertView.findViewById(R.id.Raza);
        raza.setText(pets.getRaza());

        return convertView;
    }
}

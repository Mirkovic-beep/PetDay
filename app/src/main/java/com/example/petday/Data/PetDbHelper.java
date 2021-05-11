package com.example.petday.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.petday.Data.PetContract.PetEntry;
import androidx.annotation.Nullable;

import com.example.petday.Pet;

import java.util.ArrayList;

public class PetDbHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="pets.db";
    private static final int DATABASE_VERSION = 1;

    public PetDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + PetEntry.TABLE_NAME + "("
                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + PetEntry.COLUMN_PET_BREED + " TEXT NOT NULL, "
                + PetEntry.COLUMN_PET_GENDER + " TEXT NOT NULL, "
                + PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL )";

    sqLiteDatabase.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Pet> displayDataBaseInfo(){

        ArrayList<Pet> mPetArray = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
                PetEntry.COLUMN_PET_GENDER,
                PetEntry.COLUMN_PET_WEIGHT
        };

        Cursor cursor = db.query(
                PetEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        int nameColum = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
        int breedColum = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
        int genderColum = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
        int weightColum = cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT);

        while (cursor.moveToNext()){
            String currentName = cursor.getString(nameColum);
            String currentBreed = cursor.getString(breedColum);
            String currentGender = cursor.getString(genderColum);
            int currentWeight = cursor.getInt(weightColum);

            if(currentName.isEmpty() || currentBreed.isEmpty() || Integer.toString(currentWeight).isEmpty()){
                mPetArray.add(null);
            }else{
                Pet currentPet = new Pet(currentName,currentBreed,currentGender,currentWeight);
                mPetArray.add(currentPet);
            }
        }

        return mPetArray;
    }

    public long insertPet(Pet pet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PetEntry.COLUMN_PET_NAME,pet.getNombre());
        contentValues.put(PetEntry.COLUMN_PET_BREED,pet.getRaza());
        contentValues.put(PetEntry.COLUMN_PET_GENDER,pet.getGenero());
        contentValues.put(PetEntry.COLUMN_PET_WEIGHT,pet.getPeso());

        long newRowId = db.insert(PetEntry.TABLE_NAME,null,contentValues);

        return newRowId;
    }


}

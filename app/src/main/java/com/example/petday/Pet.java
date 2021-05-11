package com.example.petday;

import java.io.Serializable;

public class Pet implements Serializable {

    String nombre;
    String raza;
    String genero;
    int peso;

    public Pet(String nombre, String raza,String genero, int peso){

        this.nombre = nombre;
        this.raza = raza;
        this.genero = genero;
        this.peso = peso;
    }

    public String getNombre (){return nombre;}
    public String getRaza (){return raza;}
    public String getGenero (){return genero;}
    public int getPeso (){return peso;}


}

package com.herprogramacion.geekyweb;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by Guaria on 5/20/2016.
 */
public class VariablesGlobales {
    private static VariablesGlobales instance = null;

   private int correctas;
    private int incorretas;
    private int cantidadPreguntas;

    protected VariablesGlobales() {
        this.correctas=0;
        this.incorretas=0;
        this.cantidadPreguntas=0;
    }
    public static VariablesGlobales getInstance() {
        if(instance == null) {
            instance = new VariablesGlobales();
        }
        return instance;
    }
    //Colocar en la clase Base


    public int getCorrectas() {
        return correctas;
    }

    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }

    public int getIncorretas() {
        return incorretas;
    }

    public void setIncorretas(int incorretas) {
        this.incorretas = incorretas;
    }

    public int getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    public void setCantidadPreguntas(int cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
    }
}

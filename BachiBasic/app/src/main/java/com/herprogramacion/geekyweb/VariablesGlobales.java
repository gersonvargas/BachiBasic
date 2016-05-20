package com.herprogramacion.geekyweb;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by Guaria on 5/20/2016.
 */
public class VariablesGlobales {
    private static VariablesGlobales instance = null;
    private static DBAdapter db;
    protected VariablesGlobales() {}
    public static VariablesGlobales getInstance() {
        if(instance == null) {
            instance = new VariablesGlobales();
        }
        return instance;
    }
    //Colocar en la clase Base




}

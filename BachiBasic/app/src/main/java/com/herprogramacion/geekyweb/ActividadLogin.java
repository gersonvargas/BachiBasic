package com.herprogramacion.geekyweb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ActividadLogin extends Base {
    private boolean valido=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_login);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        CambiarColorFondoActivity(Color.GRAY);


        Button miboton= (Button) findViewById(R.id.btniniciar);
        miboton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {
                if (valido) {
                    Intent intento = new Intent(getApplicationContext(), ActividadPreguntas.class);
                    startActivity(intento);
                }else
                    Mensaje("Debe elegir una Materia.");
            }
        });

        ImageView imgv = (ImageView) findViewById(R.id.imageViewbachi);
       Intent intento = new Intent(getApplicationContext(), Main.class);
        onclickImagenCambiarVista(imgv, intento);
        CargarSpinner();

    }
    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};
    private void CargarSpinner() {
        Spinner s1;
        final String[] presidents = {
                "Elija la materia",
                "Español",
                "Matemáticas",
                "Est. Sociales"
        };

        //---Spinner View---
        s1 = (Spinner) findViewById(R.id.spinnermateria);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, presidents);




        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(position!=0){
                    valido=true;
                }
                //Mensaje(position+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        s1.setAdapter(adapter);


    }
}
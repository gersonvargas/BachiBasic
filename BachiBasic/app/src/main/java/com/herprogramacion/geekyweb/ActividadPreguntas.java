package com.herprogramacion.geekyweb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadPreguntas extends Base  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_preguntas);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        CambiarColorFondoActivity(Color.GRAY);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        ImageView imgv = (ImageView) findViewById(R.id.imageViewbachi);
        Intent intento = new Intent(getApplicationContext(), Main.class);
        onclickImagenCambiarVista(imgv, intento);

        ajustarEventos();


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupRespuestas);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.radioButtonopc1);
                RadioButton rb2 = (RadioButton) findViewById(R.id.radioButtonopc2);
                RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3opc3);
                RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4opc4);
                if (rb1.isChecked()) {
                    Mensaje("uno");
                }
                if (rb2.isChecked()) {
                    Mensaje("dos");
                }

                if (rb3.isChecked()) {
                    Mensaje("tres");
                }
                if (rb4.isChecked()) {
                    Mensaje("Cuatro");
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        //  Toast.makeText(getApplicationContext(), "Te atrape", Toast.LENGTH_LONG).show();
        // super.onBackPressed(); habilite esto si desea que se devuelva con el boton back
        //Button MiBoton = (Button) findViewById(R.id.button2);
        // MiBoton.performClick();
        DialogoSiNo();
    }
    public void ajustarEventos(){
        TextView preguta = (TextView) findViewById(R.id.editTextpreguntas);
        //preguta.setEnabled(false);
        Button MiBoton = (Button) findViewById(R.id.btnSiguiente);
        MiBoton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {
                cambiarPreguntas("La siguiente imagen muestra la ubicación del mar.");
                ImageView midib = (ImageView)findViewById(R.id.imageView2);
                midib.setImageResource(R.drawable.planeta);
                cambiarOpciones("Caspio","Caribe","Del Norte","Mediterraneo");
            }

        });
    }
    public void cambiarPreguntas(String texto){
        TextView preguta = (TextView) findViewById(R.id.editTextpreguntas);
        preguta.setText(texto);
    }
    public void cambiarOpciones(String opc1,String opc2,String opc3,String opc4){
        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButtonopc1);
        rb1.setText(opc1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.radioButtonopc2);
        rb2.setText(opc2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3opc3);
        rb3.setText(opc3);
        RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4opc4);
        rb4.setText(opc4);
    }
    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};
    public void DialogoSiNo(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("¿Estas seguro de salir?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intento = new Intent(getApplicationContext(), Main.class);
                        startActivity(intento);
                    } });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Mensaje("negativo");
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    };
}


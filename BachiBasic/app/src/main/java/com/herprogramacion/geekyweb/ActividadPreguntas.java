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
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActividadPreguntas extends Base  {
    static int i=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_preguntas);
        CambiarColorFondoActivity(Color.GRAY);
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
        Chronometer Mi_chronometer = (Chronometer) findViewById(R.id.chronometer);

        Mi_chronometer.start();
        advertir();

    }
    public void advertir(){
        boolean continua=true;
        Thread threadCliente;
        Chronometer Mi_chronometer = (Chronometer) findViewById(R.id.chronometer);
       final String[]crono=Mi_chronometer.getText().toString().split(":");
       // while (continua) {
            threadCliente = new Thread() {
                @Override
                public void run() {

                       // Mensaje(crono.toString());
                        if ((Integer.parseInt(crono[1])) > 30) {

                            playAudio();
                        }

                }
            };
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
               // Logger.getLogger(Conexion_Erlang.class.getName()).log(Level.SEVERE, null, ex);
            }
            if((Integer.parseInt(crono[1]))>30) {
                continua = false;
            }
            threadCliente.start();
       // }

    }
    public void playAudio() {
        Intent objIntent = new Intent(this, Audio.class);
        startService(objIntent);
    }
    @Override
    public void onBackPressed(){
            DialogoSiNo();
    }
    public void ajustarEventos(){
        final TextView preguta = (TextView) findViewById(R.id.editTextpreguntas);
        //preguta.setEnabled(false);
        Button MiBoton = (Button) findViewById(R.id.btnSiguiente);
        MiBoton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {
                CrearBD();
                ArrayList<Pregunta> preguntas = ObtenerDatos();
                i++;
                if (i < preguntas.size()) {
                    Pregunta pre = preguntas.get(i);
                    cambiarPreguntas(pre.getDescripcion());
                    ImageView midib = (ImageView) findViewById(R.id.imageView2);
                    String nombre = "";

                    midib.setImageResource(pre.getImagen());
                    //String[] opcs = {"Caspio", "Caribe", "Del Norte", "Mediterraneo"};
                    cambiarOpciones(pre.getOpciones());
                } else {
                    Mensaje("Ya no hay preguntas");
                }

            }

        });
    }
    public void cambiarPreguntas(String texto){
        TextView preguta = (TextView) findViewById(R.id.editTextpreguntas);
        preguta.setText(texto);
    }
    public void cambiarOpciones(String []opcs){
        if(opcs.length>3) {
            RadioButton rb1 = (RadioButton) findViewById(R.id.radioButtonopc1);
            rb1.setText(opcs[0]);
            RadioButton rb2 = (RadioButton) findViewById(R.id.radioButtonopc2);
            rb2.setText(opcs[1]);
            RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3opc3);
            rb3.setText(opcs[2]);
            RadioButton rb4 = (RadioButton) findViewById(R.id.radioButton4opc4);
            rb4.setText(opcs[3]);
        }
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


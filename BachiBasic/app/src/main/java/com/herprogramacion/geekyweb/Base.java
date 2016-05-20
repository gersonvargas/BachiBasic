package com.herprogramacion.geekyweb;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class Base extends ActionBarActivity {
    public static int opcMateria=0;
    //Ejemplo de uso:
    public void CambiarColorFondoActivity(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
    public void onclickImagenCambiarVista(ImageView img,final Intent intent){
        img.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {


                startActivity(intent);
            }
        });

    }
    //Colocar en la clase Base
    DBAdapter db;

    public void CrearBD() {
        db = new DBAdapter(this);
        //db = new DBAdapter(this);
    }

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    };

    public void AgregarDato(Pregunta pregunta) {
        db.open();
        if (db.insertDato(pregunta) >= 0) {
            Mensaje("Pregunta agregada correctamente: "+pregunta.getDescripcion());
        }

        db.close();
    }
    public boolean BorrarDatos(){
        db.open();
        return db.BorrarDatos();
    }
    public ArrayList<Pregunta> ObtenerDatos() {
        //--cargamos todos los datos---
        ArrayList<Pregunta> listapreguntas=new ArrayList<>();
        db.open();
        Cursor c = db.CargarTodosLosDatos();
        if (c.moveToFirst())
        {
            do {
                listapreguntas.add(MostarDato(c));
            } while (c.moveToNext());
        }
        db.close();
        return listapreguntas;
    }
    public void ObtenerDato() {
        //---cargar un contacto ---
        db.open();
        Cursor c = db.ObtenerDato(2);
        if (c.moveToFirst())
            MostarDato(c);
        else
            Mensaje("No se encontró el dato");
        db.close();
    }
    public void ActualizarDato(Pregunta pregunta) {
        //---update a contact---
        db.open();
        if (db.ActualizarDato(pregunta))
            Mensaje("Se actualizó la pregunta: "+pregunta.getDescripcion());

        db.close();
    }
    public void BorrarDato() {
        db.open();
        if (db.BorrarDato(1))
            Mensaje("Borrado exitoso");
        else
            Mensaje("Fallo al intentar borrar");
        db.close();
    }
    public Pregunta MostarDato(Cursor c)
    {
        /*String descripcion,String []opciones, int numero, int respuesta
        numero integer primary key autoincrement, "
            + "descripcion text not null, opc1 text not null,opc2 text not null,opc3 text not null" +
            ",opc4 text not null,opccorrecta text not null*/
        String []opciones={c.getString(2),c.getString(3),c.getString(4),c.getString(5)};
        Pregunta pregunta=new Pregunta(c.getString(1),opciones,
                Integer.parseInt(c.getString(0)),Integer.parseInt(c.getString(6)),Integer.parseInt(c.getString(7)));

       // Mensaje("pregunta recuperada" );
        return pregunta;
    }

}
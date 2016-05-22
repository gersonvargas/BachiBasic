package com.herprogramacion.geekyweb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActividadRegistrar extends Base {
    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
         private File file = new File(ruta_fotos);
         private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_registrar);

        CambiarColorFondoActivity(Color.GRAY);

        Button miboton= (Button) findViewById(R.id.btnregistrar);
        miboton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {
                Intent intento = new Intent(getApplicationContext(), ActividadLogin.class);
                startActivity(intento);
            }
        });
        ImageView imgv = (ImageView) findViewById(R.id.imageViewbachi);
        Intent intento = new Intent(getApplicationContext(), Main.class);
        onclickImagenCambiarVista(imgv, intento);
        boton = (Button) findViewById(R.id.btnregistrar);
           //Si no existe crea la carpeta donde se guardaran las fotos
           file.mkdirs();
           //accion para el boton
           boton.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View v) {
                   String file = ruta_fotos + getCode() + ".jpg";

                   File mi_foto = new File(file);
                   try {
                       mi_foto.createNewFile();
                   } catch (IOException ex) {
                       Log.e("ERROR ", "Error:" + ex);
                   }
                   //
                   Uri uri = Uri.fromFile(mi_foto);
                   //Abre la camara para tomar la foto
                   Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                   //Guarda imagen
                   cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                   //Retorna a la actividad
                   startActivityForResult(cameraIntent, 0);
               }

           });
    }
    @SuppressLint("SimpleDateFormat")
      private String getCode()
      {
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
           String date = dateFormat.format(new Date() );
           String photoCode = "pic_" + date;
           return photoCode;
          }

              @Override
      public boolean onCreateOptionsMenu(Menu menu) {
           // Inflate the menu; this adds items to the action bar if it is present.
           getMenuInflater().inflate(R.menu.menu_main, menu);
           return true;
          }
}


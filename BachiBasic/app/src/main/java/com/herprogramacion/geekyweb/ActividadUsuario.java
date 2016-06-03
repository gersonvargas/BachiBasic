package com.herprogramacion.geekyweb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ActividadUsuario extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_usuario);

        CambiarColorFondoActivity(Color.GRAY);

        Button miboton= (Button) findViewById(R.id.btneditarperfil);
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
        if(!VariablesGlobales.getInstance().getSessionemail().equals("")) {
            ImageView imagen = (ImageView) findViewById(R.id.imageViewavatar2);
            getimage(VariablesGlobales.getInstance().getSessionemail(), imagen);
        }
    }

}

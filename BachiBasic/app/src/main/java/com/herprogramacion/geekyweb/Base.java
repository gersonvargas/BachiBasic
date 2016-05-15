package com.herprogramacion.geekyweb;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Guaria on 4/14/2016.
 */
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

}
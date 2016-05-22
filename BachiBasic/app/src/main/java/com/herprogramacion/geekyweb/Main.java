package com.herprogramacion.geekyweb;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class Main extends Base {

    /*
     DECLARACIONES
     */
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    private CharSequence activityTitle;
    private CharSequence itemTitle;
    private String[] tagTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemTitle = activityTitle = getTitle();
        tagTitles = getResources().getStringArray(R.array.Tags);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        // Setear una sombra sobre el contenido principal cuando el drawer se despliegue
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        //Crear elementos de la lista
        ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
        items.add(new DrawerItem(tagTitles[0], R.drawable.gracias));
        items.add(new DrawerItem(tagTitles[1], R.drawable.youtube));
        items.add(new DrawerItem(tagTitles[2], R.drawable.login));
        items.add(new DrawerItem(tagTitles[3], R.drawable.registrar));
        items.add(new DrawerItem(tagTitles[4], R.drawable.usuario));

        // Relacionar el adaptador y la escucha de la lista del drawer
        drawerList.setAdapter(new DrawerListAdapter(this, items));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Habilitar el icono de la app por si hay algún estilo que lo deshabilitó
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Crear ActionBarDrawerToggle para la apertura y cierre
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(itemTitle);
                /*Usa este método si vas a modificar la action bar
                con cada fragmento
                 */
               // invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(activityTitle);

                /*Usa este método si vas a modificar la action bar
                con cada fragmento
                 */
                //invalidateOptionsMenu();
            }
        };
        //Seteamos la escucha
        drawerLayout.setDrawerListener(drawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

       // playAudio();
        inicializarBase();
    }
    public void inicializarBase(){
        CrearBD();
    if(BorrarDatos()){
         Mensaje("Se ha limpiado la base de datos!");
    }
        //String descripcion,String []opciones, int numero, int respuesta
        String []opcs={"opc1","opc2","opc3","opc4"};
        for(int i=0;i<4;i++)
        AgregarDato(new Pregunta("Pregunta sobre no se que Pregunta sobre no se que " +
                "Pregunta sobre no se que Pregunta sobre no se que" +
                "Pregunta sobre no se que" +
                "Pregunta sobre no se que" +
                "Pregunta sobre no se que "+i, opcs, 1, 1,R.drawable.question));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            // Toma los eventos de selección del toggle aquí
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* La escucha del ListView en el Drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // Reemplazar el contenido del layout principal por un fragmento
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(ArticleFragment.ARG_ARTICLES_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        elegirVista(position);
        // Se actualiza el item seleccionado y el título, después de cerrar el drawer
      //  drawerList.setItemChecked(position, true);
        //setTitle(tagTitles[position]);
        //drawerLayout.closeDrawer(drawerList);
    }
public void elegirVista(int position){
    if(position==1) {
        Intent intento = new Intent(getApplicationContext(), ActividadTutorial.class);
        startActivity(intento);
    }
    if(position==2) {
        Intent intento = new Intent(getApplicationContext(), ActividadLogin.class);
        startActivity(intento);
    }
    if(position==3) {
        Intent intento = new Intent(getApplicationContext(), ActividadRegistrar.class);
        startActivity(intento);
    }
    if(position==4) {
        Intent intento = new Intent(getApplicationContext(), ActividadUsuario.class);
        startActivity(intento);
    }
}
    /* Método auxiliar para setear el titulo de la action bar */
    @Override
    public void setTitle(CharSequence title) {
        itemTitle = title;
        getSupportActionBar().setTitle(itemTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sincronizar el estado del drawer
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Cambiar las configuraciones del drawer si hubo modificaciones
        drawerToggle.onConfigurationChanged(newConfig);
    }

}
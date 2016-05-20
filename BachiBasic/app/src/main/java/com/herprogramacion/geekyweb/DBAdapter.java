package com.herprogramacion.geekyweb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBAdapter {
    static final String KEY_ROWID = "numero";
    static final String KEY_DESCRIPCION = "descripcion";
    static final String KEY_OPC1 = "opc1";
    static final String KEY_OPC2 = "opc2";
    static final String KEY_OPC3 = "opc3";
    static final String KEY_OPC4 = "opc4";
    static final String KEY_IMAGEN = "imagen";
    static final String KEY_CORRECTA = "opccorrecta";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "BaseBachiBasic1.db";
    static final String DATABASE_TABLE = "pregunta";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE =
            "create table pregunta (numero integer primary key autoincrement, "
                    + "descripcion text not null, opc1 text not null,opc2 text not null,opc3 text not null" +
                    ",opc4 text not null,opccorrecta text not null,imagen text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS pregunta");
            onCreate(db);
        }
    }
    //---Abrimos la base datos---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---Cerramos la base de datos ---
    public void close()
    {

        DBHelper.close();
    }
    //---Insertamos un dato en la BD---
    public long insertDato(Pregunta pregunta)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DESCRIPCION, pregunta.getDescripcion());
        initialValues.put(KEY_CORRECTA, pregunta.getRespuesta());
        initialValues.put(KEY_OPC1, pregunta.getOpciones()[0]);
        initialValues.put(KEY_OPC2, pregunta.getOpciones()[1]);
        initialValues.put(KEY_OPC3, pregunta.getOpciones()[2]);
        initialValues.put(KEY_OPC4, pregunta.getOpciones()[3]);
        initialValues.put(KEY_IMAGEN, pregunta.getImagen());
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    //---Borramos un dato particular---
    public boolean BorrarDato(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    public boolean BorrarDatos()
    {

        return db.delete(DATABASE_TABLE,"1=1", null) > 0;
    }
    //---Recuperamos todo los datos---
    public Cursor CargarTodosLosDatos()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_DESCRIPCION,
                KEY_OPC1,KEY_OPC2,KEY_OPC3,KEY_OPC4,KEY_CORRECTA,KEY_IMAGEN}, null, null, null, null, null);
    }
    //---recuperamos un dato particular---
    public Cursor ObtenerDato(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_DESCRIPCION,
                                KEY_OPC1,KEY_OPC2,KEY_OPC3,KEY_OPC4,KEY_CORRECTA,KEY_IMAGEN}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //---Actualizamos un dato---
    public boolean ActualizarDato(Pregunta pregunta)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_DESCRIPCION, pregunta.getDescripcion());
        args.put(KEY_CORRECTA, pregunta.getRespuesta());
        args.put(KEY_OPC1, pregunta.getOpciones()[0]);
        args.put(KEY_OPC2, pregunta.getOpciones()[1]);
        args.put(KEY_OPC3, pregunta.getOpciones()[2]);
        args.put(KEY_OPC4, pregunta.getOpciones()[3]);
        args.put(KEY_IMAGEN, pregunta.getImagen());
        return db.update(
                DATABASE_TABLE, args, KEY_ROWID + "=" + pregunta.getNumero(), null) > 0;
    }
}


// FIN DE LA CLASE DatabaseHelper
package com.example.b09s208est.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioSQLiteHelper extends SQLiteOpenHelper{
    //creacion de una variable que contrenda la sentencia sql
    String sql="CREATE TABLE Cliente (Identificacion INTEGER, Nombre TEXT, Apellidos TEXT)";
    public UsuarioSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //se ejecuta cuando la  bd no existe (primera vez que se llama la clase).
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //se ejecuta cunado detecta una nueva version de la app
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        db.execSQL(sql);
    }
}

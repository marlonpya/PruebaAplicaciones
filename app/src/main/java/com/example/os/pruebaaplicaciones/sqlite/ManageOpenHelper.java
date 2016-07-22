package com.example.os.pruebaaplicaciones.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OS on 16/07/2016.
 */
public class ManageOpenHelper extends SQLiteOpenHelper {
    //nombre de la tabla
    public static final String name_table = "tb_usuario";


    public ManageOpenHelper(Context context){


        super(context, "sistema_david_perez.db", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(

          "create table "+ name_table +"(id integer auto_increment primary key ,"+

                  "nombre text,"+

                  "apellido text,"+

                  "correo text,"+

                  "contraseña text,"+

                  "cantidad integer)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

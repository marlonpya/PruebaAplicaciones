package com.example.os.pruebaaplicaciones.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.os.pruebaaplicaciones.modelos.Usuarios;

import java.util.ArrayList;

/**
 * Created by OS on 16/07/2016.
 */
public class SentenciaSQL {

    public ManageOpenHelper conexion;

    public SentenciaSQL (Context context){

      conexion =  new ManageOpenHelper(context);
    }

    public boolean validarUsuario(String correo,String contraseña) {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from "+ManageOpenHelper.name_table+" where correo='" + correo + "' and contraseña='" + contraseña + "'",
                null
        );
        if(cursor.moveToFirst()){
            do{
                return  true;
            }while(cursor.moveToNext());
        }
        return false;
    }

    public boolean registrarUsuario(Usuarios usuarios) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("nombre", usuarios.getNombre());
            contentValues.put("apellido", usuarios.getApellido());
            contentValues.put("correo", usuarios.getCorreo());
            contentValues.put("contraseña", usuarios.getContraseña());
            db.insert(ManageOpenHelper.name_table, null, contentValues);
            return true;

        }catch (Exception e){

            return false;

        }

    }

    public boolean registrarUsuario(String nombre, String apellido, String correo,String contraseña) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("nombre",nombre);
            contentValues.put("apellido", apellido);
            contentValues.put("correo",correo);
            contentValues.put("contraseña", contraseña);
            db.insert(ManageOpenHelper.name_table, null, contentValues);
            return true;

        }catch (Exception e){

            return false;

        }

    }

    public Usuarios traerUsuario(int id_de_usuario){
        Usuarios usuario = null;
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ManageOpenHelper.name_table+" where id="+id_de_usuario
                ,null);
        if (cursor.moveToFirst()){
            do {
                usuario = new Usuarios();
                usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
                usuario.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                usuario.setApellido(cursor.getString(cursor.getColumnIndex("apellido")));
                usuario.setContraseña(cursor.getString(cursor.getColumnIndex("contraseña")));
                usuario.setCorreo(cursor.getString(cursor.getColumnIndex("correo")));

                Log.d("SentenciasSQL","Encontrò usuario->"+usuario.toString());
            }while (cursor.moveToNext());

        }
        return usuario;
    }


    public ArrayList<Usuarios> listarUsuarios(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+ManageOpenHelper.name_table, null);
        ArrayList<Usuarios> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                lista.add(new Usuarios(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("correo")),
                        cursor.getString(cursor.getColumnIndex("contraseña")),
                        cursor.getString(cursor.getColumnIndex("apellido")),
                        cursor.getString(cursor.getColumnIndex("nombre")),
                        cursor.getInt(cursor.getColumnIndex("cantidad"))
                ));
            }while (cursor.moveToNext());
        }
        return  lista;
    }

    public int ultimoValor(String correo){
        int cantidad = 0;
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select (cantidad) from "+ManageOpenHelper.name_table+" where correo ='"+correo+"'",null);
        if (cursor.moveToFirst()){
            do {
                cantidad = cursor.getInt(cursor.getColumnIndex("cantidad"));
            }while (cursor.moveToNext());
        }
        return cantidad;
    }




}

package com.example.os.pruebaaplicaciones.modelos;

/**
 * Created by OS on 16/07/2016.
 */
public class Usuarios {

    private int id,cantidad;

    private String nombre,apellido,contraseña,correo;

    public Usuarios(){}

    public Usuarios(int id, String correo, String contraseña, String apellido, String nombre, int cantidad) {
        this.id = id;
        this.correo = correo;
        this.contraseña = contraseña;
        this.apellido = apellido;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

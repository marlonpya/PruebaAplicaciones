package com.example.os.pruebaaplicaciones;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.os.pruebaaplicaciones.sqlite.ManageOpenHelper;
import com.example.os.pruebaaplicaciones.sqlite.SentenciaSQL;

public class MainActivity extends AppCompatActivity {
    private EditText txtCorreo, txtContraseña;
    private Button btnIngresar, btnRegistrarse;
    private ManageOpenHelper db;
    private SentenciaSQL sentencias;
    private String correo, contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtContraseña = (EditText)findViewById(R.id.txtContraseña);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);

        db = new ManageOpenHelper(getApplicationContext());
        sentencias = new SentenciaSQL(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = txtCorreo.getText().toString().trim();
                contrasenia = txtContraseña.getText().toString().trim();

                if (!correo.isEmpty() && !contrasenia.isEmpty()){

                    boolean resultado = sentencias.validarUsuario(correo,contrasenia);

                    if (resultado == true){
                        SQLiteDatabase acceso = db.getWritableDatabase();

                        int valor_cantidad = sentencias.ultimoValor(correo) + 1 ;

                        ContentValues values = new ContentValues();
                        values.put("cantidad",valor_cantidad);

                        //traigo el String que tiene el nombre de la tabla en la clase ManageOpenHelper()
                        acceso.update(ManageOpenHelper.name_table,values,"correo='"+correo+"'",null);
                        startActivity(new Intent(MainActivity.this,ListaActivity.class));

                    }else{
                        Toast.makeText(MainActivity.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistroActivity.class));
            }
        });
    }
}

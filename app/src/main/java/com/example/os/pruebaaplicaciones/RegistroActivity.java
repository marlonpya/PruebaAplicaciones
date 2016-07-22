package com.example.os.pruebaaplicaciones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.os.pruebaaplicaciones.modelos.Usuarios;
import com.example.os.pruebaaplicaciones.sqlite.ManageOpenHelper;
import com.example.os.pruebaaplicaciones.sqlite.SentenciaSQL;

//NO TE DEJES GUIAR  POR EL NOMBRE AQUÍ SE REGISTRA Y SE ACTUALIZA !
public class RegistroActivity extends AppCompatActivity {
    private TextView txtTitulo;
    private EditText txtNombre, txtApellido, txtCorreo, txtContra1, txtContra2;
    private Button btnRegistrar;
    private String nombre, apellido, correo, contra1, contra2;
    private ManageOpenHelper db;
    private SentenciaSQL sentencias;
    private int id_sqlite, id_para_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);

        txtTitulo = (TextView)findViewById(R.id.txtTitulo);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtContra1 =(EditText)findViewById(R.id.txtContra1);
        txtContra2 =(EditText)findViewById(R.id.txtContra2);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);

        sentencias = new SentenciaSQL(getApplicationContext());
        db = new ManageOpenHelper(getApplicationContext());

        if (getIntent().hasExtra("clave")){

            txtTitulo.setText("ACTUALIZAR");

            id_sqlite = getIntent().getIntExtra("clave",-1);
            Usuarios usuariosqlite = sentencias.traerUsuario(id_sqlite);

            id_para_registrar = usuariosqlite.getId();

            txtNombre.setText(usuariosqlite.getNombre());
            txtApellido.setText(usuariosqlite.getApellido());
            txtContra1.setText(usuariosqlite.getContraseña());
            txtContra2.setText(usuariosqlite.getContraseña());

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = txtNombre.getText().toString().trim();
                apellido = txtApellido.getText().toString().trim();
                correo = txtCorreo.getText().toString().trim();
                contra1 = txtContra1.getText().toString().trim();
                contra2 = txtContra2.getText().toString().trim();

                //no va a insertar ningún usuario si existe un campo vacío para eso es el if !
                if (!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !contra1.isEmpty() && !contra2.isEmpty()
                        //sólo va a registrar si las 2 contraseñas son iguales
                        && (contra1.equals(contra2)) ){

                    boolean resultado = sentencias.registrarUsuario(nombre,apellido,correo,contra1);

                    if (resultado==true){

                        Toast.makeText(getApplicationContext(), "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                        // del registro te dirige al login
                        startActivity(new Intent(RegistroActivity.this,MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "ERROR DE DATOS", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}

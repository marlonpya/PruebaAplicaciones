package com.example.os.pruebaaplicaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.os.pruebaaplicaciones.adapters.UsuarioAdapter;
import com.example.os.pruebaaplicaciones.modelos.Usuarios;
import com.example.os.pruebaaplicaciones.sqlite.SentenciaSQL;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Usuarios> lista;
    private UsuarioAdapter adapter;
    private SentenciaSQL sentencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listView = (ListView)findViewById(R.id.lista);

        sentencia = new SentenciaSQL(getApplicationContext());

        listar();

    }

    @Override
    protected void onResume() {
        super.onResume();

        listar();
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaActivity.this,RegistroActivity.class);
                intent.putExtra("clave",adapter.getItemId(position));
                startActivity(intent);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void listar(){
        lista = sentencia.listarUsuarios();

        adapter = new UsuarioAdapter(lista,ListaActivity.this);
        listView.setAdapter(adapter);
    }
}

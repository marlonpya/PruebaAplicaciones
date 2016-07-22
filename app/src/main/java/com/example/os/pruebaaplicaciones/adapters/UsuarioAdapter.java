package com.example.os.pruebaaplicaciones.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.os.pruebaaplicaciones.R;
import com.example.os.pruebaaplicaciones.modelos.Usuarios;

import java.util.ArrayList;

/**
 * Created by OS on 16/07/2016.
 */
public class UsuarioAdapter extends BaseAdapter{
    private TextView iNombre, iCorreo, iCantidadVeces;

    private ArrayList<Usuarios> lista;
    private Context context;

    public UsuarioAdapter(ArrayList<Usuarios> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_usuario,parent,false);

        iNombre = (TextView)convertView.findViewById(R.id.iNombre);
        iCorreo = (TextView)convertView.findViewById(R.id.iCorreo);
        iCantidadVeces =(TextView)convertView.findViewById(R.id.iCantidadVeces);

        iNombre.setText(lista.get(position).getNombre());
        iCorreo.setText(lista.get(position).getCorreo());
        iCantidadVeces.setText(lista.get(position).getCantidad());

        return convertView;
    }
}

package ittepic.edu.mx.sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hector on 28/02/17.
 */

public class AdapterRegistros extends BaseAdapter {

    protected Activity activity;
    //protected CheckBox cb;
    protected ArrayList<Datos> items;

    public AdapterRegistros(Activity activity, ArrayList<Datos> items){
        this.activity=activity;
        this.items =items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(convertView==null){
            LayoutInflater inf=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.activity_list,null);
        }
        Datos dir = items.get(position);

        TextView titulo = (TextView) v.findViewById(R.id.concepto);
        titulo.setText(dir.getConcepto());

      /*
        TextView fecha = (TextView) v.findViewById(R.id.editFecha2);
        fecha.setText(dir.getFecha());

        TextView descripcion = (TextView) v.findViewById(R.id.editDescripcion2);
        descripcion.setText(dir.getDescripcion());

        TextView observacion = (TextView) v.findViewById(R.id.editObservaciones2);
        observacion.setText(dir.getObservaciones());
        */
        /*TextView id = (TextView) v.findViewById(R.id.ID1);
        id.setText(String.valueOf(dir.getID()));*/

        return v;
    }


}

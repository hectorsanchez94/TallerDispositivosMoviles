package com.example.edgar.prou2;

/**
 * Created by edgar on 13/05/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    String[] titulos;
    Bitmap[] imagenes;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, String[] titulos,Bitmap[]  imagenes) {
        this.context = context;
        this.titulos = titulos;
        this.imagenes = imagenes;
    }
    @Override
    public int getCount() {
        return titulos.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView txtTitle;
        ImageView imgImg;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row, parent, false);
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title);
        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image);

        // Capture position and set to the TextViews
        txtTitle.setText(titulos[position]);
        txtTitle.setTextSize(20);


        imgImg.setImageBitmap(imagenes[position]);

        return itemView;
    }
}
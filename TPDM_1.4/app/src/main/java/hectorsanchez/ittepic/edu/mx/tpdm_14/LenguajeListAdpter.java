package hectorsanchez.ittepic.edu.mx.tpdm_14;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Hector on 04/09/17.
 */

public class LenguajeListAdpter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] itecontrol;
    private final Integer[] integers;

    public LenguajeListAdpter(Activity context, String[] itemname,String[] control,Integer[] integers ) {
        super(context, R.layout.fila_lista, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.itecontrol=control;
        this.integers=integers;
    }

    public View getView(int posicion, View view, ViewGroup parent ){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.fila_lista,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.texto_principal);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView etxDescripcion = (TextView) rowView.findViewById(R.id.texto_secundario);

        txtTitle.setText(itemname[posicion]);
        imageView.setImageResource(integers[posicion]);
        etxDescripcion.setText(itecontrol[posicion]);

        return rowView;
    }
}

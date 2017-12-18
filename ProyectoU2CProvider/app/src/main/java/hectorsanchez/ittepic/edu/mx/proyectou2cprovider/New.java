package hectorsanchez.ittepic.edu.mx.proyectou2cprovider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class New extends AppCompatActivity {

    private static final int SELECT_FILE = 1;
    Button btng, btna;
    EditText txtnom, txtcorreo;
    Conexion bd;
    Bitmap bmp = null;
    byte[] blob = null;
    CharSequence[] items = {""};
    AlertDialog.Builder dialog, dialog2;
    EditText input=null;
    int[] idP=null;

    ImageButton img;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        bd = new Conexion(this, "Diccionario", null, 1);

        img = (ImageButton) findViewById(R.id.imageButton2);
        btng = (Button) findViewById(R.id.btnGuardar);
        btna = (Button) findViewById(R.id.btnAfi);
        txtcorreo = (EditText) findViewById(R.id.txtCorreo);
        txtnom = (EditText) findViewById(R.id.txtNombre);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abrirGaleria(v);
                // seleccionar(v);
            }
        });
        btng.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                guardar(v);
            }
        });

        btna.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                che();
            }
        });
    }

    public void abrirGaleria(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), SELECT_FILE);
    }


    public void seleccionar(View v) {
        ActivityCompat.requestPermissions(New.this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);

    }

    private byte[] imageViewTobyte() {
        Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "Sin permiso", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
    }

    public void guardar(View v) {
        try {
            //bd.InsertarPer(txtnom.getText().toString(),imageViewTobyte(), bd);


            SQLiteDatabase base = bd.getWritableDatabase();
            String SQL = "INSERT INTO TablaImg VALUES(NULL,'" + txtnom.getText().toString() + "','" + imageViewTobyte() + "')";
            base.execSQL(SQL);
            base.close();


            Toast.makeText(this, SQL, Toast.LENGTH_SHORT).show();


            Toast.makeText(this, "Salvado con exito" + SQL, Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void actua() {


        try {
            SQLiteDatabase base = bd.getReadableDatabase();
            String sql = "SELECT * FROM TablaImg";

            Cursor cursor = base.rawQuery(sql, null);


            int i = 0;
            if (cursor.moveToFirst()) {
                do {


                    blob = cursor.getBlob(2);

                    bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);


                    img.setImageBitmap(bmp);


                    i++;


                } while (cursor.moveToNext());

            } else {
                Toast.makeText(New.this, "no existe", Toast.LENGTH_LONG).show();

            }
            cursor.close();
            base.close();
        } catch (SQLiteException sqle) {
            //imprime el error
        }

    }

    public void che() {
        afisV();
        dialog = new AlertDialog.Builder(this);
        input = new EditText(New.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        dialog.setView(input);
        final ArrayList seletedItems = new ArrayList();

        dialog.setTitle("Aficiones")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(indexSelected);

                            //Toast.makeText(AddActivity.this, ""+seletedItems.get(1).toString(), Toast.LENGTH_LONG).show();

                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        }).setNegativeButton("Nueva", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                afis(input.getText().toString());
                che();
            }
        }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //  Your code when user clicked on Cancel
            }
        }).create();
        dialog.show();

    }




    public void afis(String afi) {
        try {

            SQLiteDatabase base = bd.getWritableDatabase();
            String SQL = "INSERT INTO TablaAfi VALUES(NULL,'" + afi + "')";
            base.execSQL(SQL);
            base.close();


            Toast.makeText(this, SQL, Toast.LENGTH_SHORT).show();


            Toast.makeText(this, "Salvado con exito" + SQL, Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void afisV() {


        try {
            SQLiteDatabase base = bd.getReadableDatabase();
            String sql = "SELECT * FROM TablaAfi";

            Cursor cursor = base.rawQuery(sql, null);

            items = new CharSequence[cursor.getCount()];


            int i = 0;
            if (cursor.moveToFirst()) {
                do {


                    items[i] = cursor.getString(1);


                    i++;


                } while (cursor.moveToNext());

            } else {
                Toast.makeText(New.this, "no existe", Toast.LENGTH_LONG).show();

            }
            cursor.close();
            base.close();
        } catch (SQLiteException sqle) {
            //imprime el error
        }

    }
}

package hectorsanchez.ittepic.edu.mx.a223_12400322;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre;
    Button btnGuardar;

    Button consultarid,elegir;
    TextView resultado;

    String latitud="",longitud="";
    Float pais;
    String tiempo="";
    WSIdalumno hiloconexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edt_nombre);
        btnGuardar = (Button) findViewById(R.id.btn_guardar);
        elegir = (Button) findViewById(R.id.button);
        consultarid = (Button) findViewById(R.id.consultarid);
        resultado = (TextView) findViewById(R.id.tresultado);

        elegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText("");
                soli();
            }
        });


        consultarid.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiloconexion = new WSIdalumno();
                hiloconexion.execute(latitud,longitud);
                //new WSIdalumno().execute();
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(edtNombre.getText().toString(),resultado.getText().toString());
                startActivity(new Intent(MainActivity.this,Listado.class));
                finish();

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            startActivity(new Intent(MainActivity.this,Listado.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardar (String Nombre, String Telefono){
        BaseHelper helper = new BaseHelper(this,"BD2.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("nombre", Nombre);
            c.put("telefono", Telefono);
            db.insert("CONTACTOS",null,c);
            db.close();
            Toast.makeText(this,"Registro insertado",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void soli(){
        resultado.setText("");
        String url="http://api.openweathermap.org/data/2.5/weather?q="+edtNombre.getText().toString()+",mx&APPID=f984a946569c92d056a2c65fe7a42e3f";
        new ReadJSONFeed().execute(url);
    }

    private class WSIdalumno extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {

            String cadena = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";

            //http://maps.googleapis.com/maps/api/geocode/json?latlng=38.404593,-0.529534&sensor=false
            cadena = cadena + params[0];
            cadena = cadena + ",";
            cadena = cadena + params[1];
            cadena = cadena + "&sensor=false";


            String devuelve = "";

            URL url = null; // Url de donde queremos obtener información
            try {
                url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                //connection.setHeader("content-type", "application/json");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK){


                    InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                    // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                    // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                    // StringBuilder.

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);        // Paso toda la entrada al StringBuilder
                    }

                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados
                    JSONArray resultJSON = respuestaJSON.getJSONArray("results");   // results es el nombre del campo en el JSON

                    //Vamos obteniendo todos los campos que nos interesen.
                    //En este caso obtenemos la primera dirección de los resultados.
                    String direccion="SIN DATOS PARA ESA LONGITUD Y LATITUD";
                    if (resultJSON.length()>0){
                        direccion = resultJSON.getJSONObject(0).getString("formatted_address");    // dentro del results pasamos a Objeto la seccion formated_address
                    }
                    devuelve = "Dirección: " + direccion;   // variable de salida que mandaré al onPostExecute para que actualice la UI

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return devuelve;
        }


        @Override
        protected void onPostExecute(String result) {

            resultado.setText(result);

        }

        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onPreExecute() {

            resultado.setText("");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }


    }

    private class ReadJSONFeed extends AsyncTask<String, String, String>  {

        protected void onPreExecute(){}
        @Override

        protected String doInBackground(String... urls){
            HttpClient httpclient=new DefaultHttpClient();
            StringBuilder builder=new StringBuilder();
            HttpPost httppost= new HttpPost(urls[0]);

            try{

                HttpResponse response= httpclient.execute(httppost);
                StatusLine statusLine= response.getStatusLine();
                int statusCode = statusLine.getStatusCode();

                if (statusCode==200){
                    HttpEntity entity= response.getEntity();
                    InputStream content= entity.getContent();
                    BufferedReader reader= new BufferedReader(new InputStreamReader(content));
                    String line;
                    while((line = reader.readLine()) != null){
                        builder.append(line);

                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return builder.toString();

        }
        protected void onPostExecute(String resultad){



            try{
                JSONObject jsonObject = new JSONObject(resultad);

                tiempo+= "El reporte del tiempo para "+jsonObject.getString("name") +" es: \n";
                tiempo+="Codigo: "+ jsonObject.getString("id")+"\n";


                JSONObject jcoordObject = new JSONObject(jsonObject.getString("coord"));

                longitud=jcoordObject.getString("lon");
                latitud=jcoordObject.getString("lat");
                tiempo+="Longitud: "+ jcoordObject.getString("lon")+"\n";
                tiempo+="Latitud: "+ jcoordObject.getString("lat")+"\n";

                JSONArray jsweatherObject = new JSONArray(jsonObject.getString("weather"));
                JSONObject jweatherObject= jsweatherObject.getJSONObject(0);
                tiempo+="Nubes: "+ jweatherObject.getString("description")+"\n";

                JSONObject jsmainObject= new JSONObject(jsonObject.getString("main"));
                pais=Float.parseFloat(jsmainObject.getString("temp"));
                pais=(pais-254);
                pais=5*pais;
                pais=pais/9;
                tiempo+="Temperatura: "+(pais).intValue()+" ºC\n";
                tiempo+="Humedad: "+ jsmainObject.getString("humidity")+"%\n";
                tiempo+="Presión atmosferica: "+ jsmainObject.getString("pressure")+" hpa \n";

                JSONObject jswindObject = new JSONObject(jsonObject.getString(" wind"));
                tiempo+="velocidad del viento : "+ jswindObject.getString("speed")+" m/s \n";




            }catch (Exception e){
                e.printStackTrace();
            }

            if(tiempo.trim().length()>0){
                resultado.setText("");
                resultado.setText(tiempo);
                tiempo="";
            }else{
                resultado.setText("no se encontro");
            }

        }



    }
}

package com.example.edgar.clima;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity {
    String cuidad="";
    Float pais;
    String tiempo="";
    TextView resp;
    Button btng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         resp = (TextView) findViewById(R.id.txtTemp);
        btng = (Button)  findViewById(R.id.btnre);
        soli();
        btng.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                soli();
            }
        });

    }

    public void soli(){

        String url="http://api.openweathermap.org/data/2.5/weather?q=Tepic,mx&APPID=f984a946569c92d056a2c65fe7a42e3f";
        new ReadJSONFeed().execute(url);
    }

    private class ReadJSONFeed extends AsyncTask<String, String, String>  {

       protected void onPreExecute(){}
        @Override

        protected String doInBackground(String... urls){
            HttpClient httpclient=new DefaultHttpClient();
            StringBuilder builder=new StringBuilder();
            HttpPost  httppost= new HttpPost(urls[0]);

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

      protected void onPostExecute(String resultado){



          try{
              JSONObject jsonObject = new JSONObject(resultado);

              tiempo+= "El reporte del tiempo para "+jsonObject.getString("name") +" es: \n";
              tiempo+="Codigo: "+ jsonObject.getString("id")+"\n";


              JSONObject jcoordObject = new JSONObject(jsonObject.getString("coord"));

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
              resp.setText(tiempo);
          }else{
                 resp.setText("no se encontro");
          }

      }


    }

}

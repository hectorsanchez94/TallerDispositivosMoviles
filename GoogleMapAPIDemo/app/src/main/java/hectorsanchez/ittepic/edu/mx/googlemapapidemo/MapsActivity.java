package hectorsanchez.ittepic.edu.mx.googlemapapidemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {

    private GoogleMap mMap;
    private Marker marcador;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    double lat = 0.0;
    double lng = 0.0;
    DatabaseHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        bd = new DatabaseHelper(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etOrigin = (EditText) findViewById(R.id.etOrigin);
        etDestination = (EditText) findViewById(R.id.etDestination);

        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
        //leer();
    }

    private void sendRequest() {
        String origin = etOrigin.getText().toString();
        String destination = etDestination.getText().toString();
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 18);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicaciona))

        );
    }

    private void actulizarUbicion(Location location) {

        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }


    }

    private void miUbicaion() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actulizarUbicion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1500,0, locListener);
    }

    LocationListener locListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            actulizarUbicion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        //miUbicaion();

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        double latitude = location.getLatitude();
        double longitud = location.getLongitude();

        LatLng tepic = new LatLng(latitude, longitud);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tepic, 18));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("Mi ubicación").position(tepic).icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicaciona))));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if(etOrigin.isFocused()==true) {
                    mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicaciona))
                        .anchor(0.0f, 1.0f)
                        .title("Marcador inicio")
                        .position(latLng));
                    etOrigin.setText(latLng.latitude+","+latLng.longitude);
                    insertarCoordenadas(""+latLng.latitude,""+latLng.longitude);
                }
                if(etDestination.isFocused()==true){
                    mMap.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicacionb))
                            .anchor(0.0f, 1.0f)
                            .title("Marcador fin")
                            .position(latLng));
                    etDestination.setText(latLng.latitude+","+latLng.longitude);
                    insertarCoordenadas(""+latLng.latitude,""+latLng.longitude);
                }
                //enviar(latLng.latitude,latLng.longitude);

            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(etOrigin.isFocused()==true) {
                    etOrigin.setText(marker.getPosition().latitude+","+marker.getPosition().longitude);
                }
                if(etDestination.isFocused()==true){
                    etDestination.setText(marker.getPosition().latitude+","+marker.getPosition().longitude);
                }
                //Toast.makeText(getApplicationContext(),"Has pulsado una marca", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        recuperarDatos();
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {

        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicaciona)).visible(false)
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicacionb)).visible(false)
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    private void insertarCoordenadas(String latitud, String longitud){
        //DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = bd.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.LATITUD,latitud);
        cv.put(DatabaseHelper.LONGITUD,longitud);

        db.insert("position",null,cv);
        db.close();
        Toast.makeText(MapsActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
    }

    private void recuperarDatos(){
        String dato="";
        SQLiteDatabase db = bd.getReadableDatabase();
        String[] campos = new String[]{"lat","lon"};

        Cursor c = db.query("position",campos,null,null,null,null,null);
        if(c.moveToFirst()){

            do {
                //dato = dato + c.getString(0)+","+c.getString(1);
                //agregarMarcador(Double.parseDouble(c.getString(0)),Double.parseDouble(c.getString(1)));
                //etOrigin.setText(dato);
                //LatLng sydney = new LatLng(Double.parseDouble(c.getString(1)),Double.parseDouble(c.getString(2)));
                mMap.addMarker(new MarkerOptions().position(new LatLng(c.getDouble(0),c.getDouble(1))).title("Marcador")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicaciona))
                );


            }while (c.moveToNext());

        }
    }

    public void leer() {

        try {
            SQLiteDatabase base = bd.getReadableDatabase();

            String dato="";
            String sql = "SELECT * FROM position";


            Cursor cursor = base.rawQuery(sql, null);



            if (cursor.moveToFirst()) {
                do {
                    //agregarMarcador2(cursor.getString(0),cursor.getString(1));

                    //dato = dato + cursor.getString(0)+","+cursor.getString(1);
                    //etOrigin.setText(dato);

                    LatLng sydney = new LatLng(Double.parseDouble(cursor.getString(0)),Double.parseDouble(cursor.getString(1)));
                    mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

                } while (cursor.moveToNext());

            } else {
                Toast.makeText(MapsActivity.this, "no existe", Toast.LENGTH_LONG).show();


            }
            cursor.close();
            base.close();
        } catch (SQLiteException sqle) {
            Toast.makeText(MapsActivity.this, "no existe", Toast.LENGTH_SHORT).show();
        }

    }
    private void agregarMarcador2(String lat, String lng) {

        LatLng coordenadas = new LatLng(Double.parseDouble(lat), Double.parseDouble((lng)));

        mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ubicaciona))

        );
    }
}

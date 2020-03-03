package com.example.macasjosue.Vista.actividades;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.macasjosue.R;
import com.example.macasjosue.modelo.RutaMapa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ActivityMapa extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Button botonSatelite,botonTerreno,botonHibrido;
    List<RutaMapa> listaRutas;
    LatLng loja = new LatLng(-4.001159, -79.235295);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        cargarComponentes();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void cargarComponentes(){
        botonSatelite= findViewById(R.id.btnSatelite);
        botonTerreno = findViewById(R.id.btnTerreno);
        botonHibrido = findViewById(R.id.btnHibrido);
        botonSatelite.setOnClickListener(this);
        botonTerreno.setOnClickListener(this);
        botonHibrido.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loja,13.0f));
        marcadores();
        lineas();
    }

    private void marcadores(){
        mMap.addMarker(new MarkerOptions()
                .position(loja)
                .title("Inicio Ruta")
                .snippet("Inicio de la ruta a la UNL desde CV")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_casa_foreground)).anchor(0.5f,0.5f));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-4.001239, -79.233744))
                .title("Canchas Deportivas")
                .snippet("Canchas deportivas de Ciudad Victoria")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_cacncha)).anchor(0.5f,0.5f));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-4.019215,-79.229116))
                .title("Urna Virgen del Cisne")
                .snippet("Urna de la Santisima Virgen del Cisne")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_urna_foreground)).anchor(0.5f,0.5f));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng( -4.025321, -79.218880))
                .title("Cementerio")
                .snippet("Campo Santo Jardines del Zamora")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_cementerio)).anchor(0.5f,0.5f));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng( -4.032247, -79.211953))
                .title("Capilla")
                .snippet("Capilla del Monasterio de Santa Clara del Divino Niño")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marcador_foreground)).anchor(0.5f,0.5f));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng( -4.032740, -79.202151))
                .title("Parada de Buses")
                .snippet("Parada de Buses Linea L2(Sauces-Argelia)")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parada)).anchor(0.5f,0.5f));


        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-4.030316,-79.199482))
                .title("Fin Ruta")
                .snippet("Fin de la ruta a la UNL")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_foreground)).anchor(0.5f,0.5f));
    }

    private void lineas() {
        List<RutaMapa> ruta = leerRuta();
        PolylineOptions lineas = new PolylineOptions();

        for(int i = 0; i< ruta.size(); i++){
            lineas.add(new LatLng(ruta.get(i).getLatitud(),ruta.get(i).getLongitud()))
                    .width(5)
                    .color(Color.RED);
            mMap.addPolyline(lineas);
        }
    }

    private List<RutaMapa> leerRuta(){
        try{

            InputStream input = getResources().openRawResource(R.raw.rawmapa);
            BufferedReader lector = new BufferedReader(new InputStreamReader(input));
            String cadena = lector.readLine();
            lector.close();
            listaRutas= new ArrayList<RutaMapa>();

            String [] a = cadena.split(";");
            String [] latlong;

            for (int i=0; i < a.length; i++){
                RutaMapa ruta = new RutaMapa();
                latlong = a[i].split(",");
                ruta.setLatitud(Double.parseDouble(latlong[0]));
                ruta.setLongitud(Double.parseDouble(latlong[1]));
                listaRutas.add(ruta);
            }
        }catch (Exception ex){
            Log.e("mesagge","Error leer Ruta");
        }
        return listaRutas;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSatelite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.btnTerreno:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.btnHibrido:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
        }
    }
}

package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macasjosue.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivitySWleerjson extends AppCompatActivity implements View.OnClickListener {

    TextView cajaDatos,cajalon,cajalat,cajaidW,cajamainW,cajadescripcionW,cajaiconW,cajabase,cajatemp;
    TextView cajapresion,cajahumedad,cajatepMin,cajatepMax,cajavisibilidad,cajaspeed,cajadeg,cajaall,cajacod;
    TextView cajadt,cajatypeS,cajaidS,cajamessageS,cajacountryS,cajasunriseS,cajasunsetS,cajaidP,cajanombre;
    Button botonLeer;

    String host = "https://samples.openweathermap.org";
    String leer = "/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swleerjson);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajaDatos = findViewById(R.id.lblDatosLeer);
        cajalon = findViewById(R.id.lblLongitud);
        cajalat = findViewById(R.id.lblLatitud);
        cajaidW = findViewById(R.id.lblIDClima);
        cajamainW= findViewById(R.id.lblMain);
        cajadescripcionW = findViewById(R.id.lblDescripcion);
        cajaiconW = findViewById(R.id.lblIcon);
        cajabase = findViewById(R.id.lblBase);
        cajatemp = findViewById(R.id.lblTemperatura);
        cajapresion = findViewById(R.id.lblPresion);
        cajahumedad = findViewById(R.id.lblHumedad);
        cajatepMin = findViewById(R.id.lblTempMin);
        cajatepMax = findViewById(R.id.lblTempMax);
        cajavisibilidad = findViewById(R.id.lblVisibilidad);
        cajaspeed = findViewById(R.id.lblVelocidad);
        cajadeg = findViewById(R.id.lblDeg);
        /*cajaall = findViewById(R.id.lblDatosLeer);
        cajacod = findViewById(R.id.lblDatosLeer);
        cajadt = findViewById(R.id.lblDatosLeer);
        cajaidS = findViewById(R.id.lblDatosLeer);
        cajamessageS = findViewById(R.id.lblDatosLeer);
        cajacountryS = findViewById(R.id.lblDatosLeer);
        cajasunriseS = findViewById(R.id.lblDatosLeer);
        cajasunsetS = findViewById(R.id.lblDatosLeer);
        cajaidP = findViewById(R.id.lblDatosLeer);
        cajanombre = findViewById(R.id.lblDatosLeer);*/

        botonLeer = findViewById(R.id.btnLeerDatos);
        botonLeer.setOnClickListener(this);
    }

    class ServicioWeb extends AsyncTask<String, Void,String> {

        @Override
        protected String doInBackground(String... parametros) {
            String ruta = parametros[0];
            String consulta = "";
            try {
                URL url = new URL(ruta);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                int codigoRespuesta = conexion.getResponseCode();
                Log.e("message", codigoRespuesta + "");
                if (codigoRespuesta == HttpURLConnection.HTTP_OK) {

                    InputStream in = new BufferedInputStream(conexion.getInputStream());
                    BufferedReader lector = new BufferedReader(new InputStreamReader(in));
                    consulta += lector.readLine();
                    lector.close();
                }
            }catch (Exception e){
                Log.e("mesage","Hubo un error");
            }
            return consulta;
        }

        @Override
        protected void onPostExecute(String s) {
            cargarDatos(s);
        }
    }

    private void cargarDatos(String cadena){
        String line = "";
        try {
            String id = "";
            String main = "";
            String description = "";
            String icon = "";

            JSONObject json = new JSONObject(cadena);
            JSONArray clima = json.getJSONArray("weather");
            for(int i=0; i < clima.length();i++){
                JSONObject c = clima.getJSONObject(i);
                id = c.getString("id");
                cajaidW.setText(id);
                main = c.getString("main");
                cajamainW.setText(main);
                description = c.getString("description");
                cajadescripcionW.setText(description);
                icon = c.getString("icon");
                cajaiconW.setText(icon);

            }

            String lon = json.getJSONObject("coord").getString("lon");
            cajalon.setText(lon);
            String lat = json.getJSONObject("coord").getString("lat");
            cajalat.setText(lat);
            String base = json.getString("base");
            cajabase.setText(base);
            String temp = json.getJSONObject("main").getString("temp");
            cajatemp.setText(temp);
            String pressure = json.getJSONObject("main").getString("pressure");
            cajapresion.setText(pressure);
            String humidity = json.getJSONObject("main").getString("humidity");
            cajahumedad.setText(humidity);
            String tempmin = json.getJSONObject("main").getString("temp_min");
            cajatepMin.setText(tempmin);
            String tempmax = json.getJSONObject("main").getString("temp_max");
            cajatepMax.setText(tempmax);
            String visibility = json.getString("visibility");
            cajavisibilidad.setText(visibility);
            String speed = json.getJSONObject("wind").getString("speed");
            cajaspeed.setText(speed);
            String deg = json.getJSONObject("wind").getString("deg");
            cajadeg.setText(deg);
            String all= json.getJSONObject("clouds").getString("all");
            String dts = json.getString("dt");
            String type= json.getJSONObject("sys").getString("type");
            String ids= json.getJSONObject("sys").getString("id");
            String message= json.getJSONObject("sys").getString("message");
            String country= json.getJSONObject("sys").getString("country");
            String sunrise= json.getJSONObject("sys").getString("sunrise");
            String sunset= json.getJSONObject("sys").getString("sunset");
            String idP = json.getString("id");
            String name = json.getString("name");
            String cod = json.getString("cod");

            line+= "La longitud es : " + lon + "\n";
            line+= "La latitud es : " + lat + "\n";
            line+= "El id es : " + id+ "\n";
            line+= "El main es : " + main + "\n";
            line+= "La descripcion es : " + description+ "\n";
            line+= "El icon es : " + icon + "\n";
            line+= "La base es : " + base+ "\n";
            line+= "La temperatura es : " + temp+ "\n";
            line+= "La presion es : " + pressure+ "\n";
            line+= "La humedad es : " + humidity+ "\n";
            line+= "La Temp max es : " + tempmax+ "\n";
            line+= "La Temp min es : " + tempmin+ "\n";
            line+= "La visibilidad es : " + visibility+ "\n";
            line+= "La velocidad del viento es : " + speed+ "\n";
            line+= "El Deg del viento es : " + deg+ "\n";
            line+= "Nublado All es : " + all+ "\n";
            line+= "El Dts es : " + dts+ "\n";
            line+= "El tipo del sys es : " + type+ "\n";
            line+= "El id del sys es : " + ids+ "\n";
            line+= "El mensaje del sys es : " + message+ "\n";
            line+= "El pais del sys es : " + country+ "\n";
            line+= "El amanecer es : " + sunrise+ "\n";
            line+= "La puesta de sol es : " + sunset+ "\n";
            line+= "El ID es : " + idP+ "\n";
            line+= "El Nombre es : " + name+ "\n";
            line+= "El Codigo es : " + cod+ "\n";

            //cajaDatos.setText(line);

        } catch (Exception e) {
            Log.e("messagge", "Error alcargar los datos");
        }
    }


    @Override
    public void onClick(View v) {
        ServicioWeb sw = new ServicioWeb();
        sw.execute(host.concat(leer));
    }
}

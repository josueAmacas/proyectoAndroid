package com.example.macasjosue.ui.send;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.actividades.ActivitySWleerjson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendFragment extends Fragment implements View.OnClickListener{

    TextView cajalon,cajalat,cajaidW,cajamainW,cajadescripcionW,cajaiconW,cajabase,cajatemp;
    TextView cajapresion,cajahumedad,cajatepMin,cajatepMax,cajavisibilidad,cajaspeed,cajadeg;
    Button botonLeer;

    String host = "https://samples.openweathermap.org";
    String leer = "/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_send, container, false);
        cajalon = v.findViewById(R.id.lblLongitudF);
        cajalat = v.findViewById(R.id.lblLatitudF);
        cajaidW = v.findViewById(R.id.lblIDClimaF);
        cajamainW= v.findViewById(R.id.lblMainF);
        cajadescripcionW = v.findViewById(R.id.lblDescripcionF);
        cajaiconW = v.findViewById(R.id.lblIconF);
        cajabase = v.findViewById(R.id.lblBaseF);
        cajatemp = v.findViewById(R.id.lblTemperaturaF);
        cajapresion = v.findViewById(R.id.lblPresionF);
        cajahumedad = v.findViewById(R.id.lblHumedadF);
        cajatepMin = v.findViewById(R.id.lblTempMinF);
        cajatepMax = v.findViewById(R.id.lblTempMaxF);
        cajavisibilidad = v.findViewById(R.id.lblVisibilidadF);
        cajaspeed = v.findViewById(R.id.lblVelocidadF);
        cajadeg = v.findViewById(R.id.lblDegF);

        botonLeer = v.findViewById(R.id.btnLeerDatosF);
        botonLeer.setOnClickListener(this);

        return v;
    }

    class ServicioW extends AsyncTask<String, Void,String> {

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

        } catch (Exception e) {
            Log.e("messagge", "Error alcargar los datos");
        }
    }


    @Override
    public void onClick(View v) {
        SendFragment.ServicioW sw = new SendFragment.ServicioW();
        sw.execute(host.concat(leer));
    }
}
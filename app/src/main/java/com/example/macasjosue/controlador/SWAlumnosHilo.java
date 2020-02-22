package com.example.macasjosue.controlador;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class SWAlumnosHilo extends AsyncTask<String, Void,String> {

    @Override
    protected String doInBackground(String... parametros) {

        String consulta ="";
        URL url = null;
        String ruta = parametros[0]; //esta es la ruta..... http://reneguaman..../obtener_alumnos.php

        if(parametros[1].equals("1") || parametros[1].equals("3")){

            try {
                url = new URL(ruta);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                int codigoRespuesta = conexion.getResponseCode();
                if (codigoRespuesta == HttpURLConnection.HTTP_OK){
                    InputStream in = new BufferedInputStream(conexion.getInputStream());
                    BufferedReader lector = new BufferedReader(new InputStreamReader(in));
                    consulta += lector.readLine();
                    lector.close();
                }else {
                    Log.e("mensaje","no hay coneccion");
                }
                conexion.disconnect();
            }catch (Exception ex){
                Log.e("mensaje","no hubo coneccion");
            }

        }else if(parametros[1].equals("2")){

            try{
                url = new URL(ruta);
                URLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                conexion.setUseCaches(false);
                conexion.setRequestProperty("Content-Type", "application/json");
                conexion.setRequestProperty("Accept", "application/json");
                conexion.connect();

                //se crea el json
                JSONObject json = new JSONObject();
                json.put("nombre", parametros[2]);
                json.put("direccion", parametros[3]);

                // Envio los parámetros post.
                OutputStream os = conexion.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(json.toString());
                writer.flush();
                writer.close();

                int respuesta = ((HttpURLConnection) conexion).getResponseCode();
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    consulta += lector.readLine();
                }
                ((HttpURLConnection) conexion).disconnect();
            }catch (Exception ex){
                Log.e("mensaje","no hubo conexion");
            }

        }else if(parametros[1].equals("4")){

            try{
                url = new URL(ruta);
                URLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                conexion.setUseCaches(false);
                conexion.addRequestProperty("Content-Type","application/json");
                conexion.addRequestProperty("Acepted","application/json");
                conexion.connect();

                //se crea el json
                JSONObject json = new JSONObject();
                json.put("idalumno", parametros[2]);

                // Envio los parámetros post.
                OutputStream os = conexion.getOutputStream();
                BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                escritor.write(json.toString());
                escritor.flush();
                escritor.close();

                int respuesta = ((HttpURLConnection) conexion).getResponseCode();
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    consulta += lector.readLine();

                }
                ((HttpURLConnection) conexion).disconnect();
            }catch (Exception ex){
                Log.e("message", "error:  Eliminar");
            }

        }else if(parametros[1].equals("5")){
            try{
                url = new URL(ruta);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                conexion.setUseCaches(false);
                conexion.connect();

                //se crea el json
                JSONObject json = new JSONObject();
                json.put("idalumno", parametros[2]);
                json.put("nombre", parametros[3]);
                json.put("direccion", parametros[4]);

                // Envio los parámetros post.
                OutputStream os = conexion.getOutputStream();
                BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                escritor.write(json.toString());
                escritor.flush();
                escritor.close();

                int respuesta = ((HttpURLConnection) conexion).getResponseCode();
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    consulta += lector.readLine();
                }else {
                    Log.e("mesagge","Codigo:  "+respuesta);
                }
                conexion.disconnect();
            }catch (Exception ex){

                Log.e("message", "error:  Editar");
            }
        }

        return consulta;
    }

}

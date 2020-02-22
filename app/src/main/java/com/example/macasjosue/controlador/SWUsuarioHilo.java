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

public class SWUsuarioHilo extends AsyncTask<String, Void,String> {

    @Override
    protected String doInBackground(String... parametros) {
        String consulta ="";
        URL url = null;
        String ruta = parametros[0]; //esta es la ruta..... http://reneguaman..../obtener_alumnos.php

        if(parametros[1].equals("1") || parametros[1].equals("2")){

            try {
                url = new URL(ruta);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setRequestMethod("GET");
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

        } else if(parametros[1].equals("3")){
            try {
                url = new URL(ruta);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                conexion.setUseCaches(false);
                conexion.setRequestProperty("Content-Type", "application/json");
                conexion.setRequestProperty("Accept", "application/json");
                conexion.connect();

                //se crea el json
                JSONObject json = new JSONObject();
                json.put("id", parametros[2]);
                json.put("nombre", parametros[3]);
                json.put("profesion", parametros[4]);
                json.put("imagen", parametros[5]);

                // Envio los parámetros post.
                OutputStream os = conexion.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(json.toString());
                writer.flush();
                writer.close();

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
                Log.e("mensaje","Error agrgar");
            }
        } else if(parametros[1].equals("4")) {
            try {
                url = new URL(ruta);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                conexion.setUseCaches(false);
                conexion.setRequestProperty("Content-Type", "application/json");
                conexion.setRequestProperty("Accept", "application/json");
                conexion.connect();

                //se crea el json
                JSONObject json = new JSONObject();
                json.put("documento", Integer.parseInt(parametros[2]));
                json.put("nombre", parametros[3]);
                json.put("profesion", parametros[4]);

                // Envio los parámetros post.
                OutputStream os = conexion.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(json.toString());
                writer.flush();
                writer.close();

                int respuesta = conexion.getResponseCode();
                Log.e("mensaje", respuesta + " codigo Resp");
                if (respuesta == HttpURLConnection.HTTP_OK) {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    consulta += lector.readLine();
                    Log.e("mensaje", consulta + " ");
                }
                conexion.disconnect();
            } catch (Exception ex) {
                Log.e("mensaje", "Error agrgar");
            }
        } else if (parametros[1].equals("5")){
            try{
                url = new URL(ruta);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                //conexion.setRequestMethod("GET");
                int codigoRespuesta = conexion.getResponseCode();
                if (codigoRespuesta == HttpURLConnection.HTTP_OK){
                    InputStream in = new BufferedInputStream(conexion.getInputStream());
                    BufferedReader lector = new BufferedReader(new InputStreamReader(in));
                    consulta += lector.readLine();
                    lector.close();
                    Log.e("mensaje",consulta+"");
                }else {
                    Log.e("mensaje","no hay coneccion");
                }
                conexion.disconnect();
            }catch (Exception ex){
                Log.e("message", "error:  Eliminar");
            }
        }
        return consulta;
    }
}

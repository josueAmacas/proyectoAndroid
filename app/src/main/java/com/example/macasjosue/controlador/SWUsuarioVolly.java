package com.example.macasjosue.controlador;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.macasjosue.modelo.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SWUsuarioVolly {

    //definimos url del servicio web
    //String host = "http://192.168.0.103/MacasCaraguay";
    String host = "http://192.168.1.15/MacasCaraguay";

    String get = "/wsJSONConsultarListaImagenes.php";
    String getById = "/wsJSONConsultarUsuarioImagen.php";
    String insert = "/wsJSONRegistro.php";
    String insertMovil = "/wsJSONRegistroMovil.php";
    String Update = "/wsJSONUpdateMovil.php";
    String Delete = "/wsJSONDeleteMovil.php";

    Context contexto;
    boolean estado;

    final private List<Usuario> listaUserV;

    public SWUsuarioVolly(Context contexto) {
        this.contexto = contexto;
        this.listaUserV = new ArrayList<>();
    }

    public List<Usuario> findAllUsers() {
        String path = host.concat(get);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Devuelve un json
                try {
                        JSONArray jsonUser = response.getJSONArray("usuario");
                        for (int i = 0; i < jsonUser.length(); i++) {
                            JSONObject jsonObject = jsonUser.getJSONObject(i);
                            Usuario user = new Usuario();
                            user.setCedula(jsonObject.getInt("documento"));
                            user.setNombres(jsonObject.getString("nombre"));
                            user.setProfesion(jsonObject.getString("profesion"));
                            user.setDato(jsonObject.getString("imagen"));
                            listaUserV.add(user);
                        }

                } catch (JSONException e) {
                    Log.e("ERROR", e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 Log.e("Message", "error al cargar todos");
            }
        });
        UsuarioSingletonVolly.getInstance(contexto).addToRequestQueue(jsonObjectRequest);
        return listaUserV;
    }

    public boolean insertUserMovil(final Usuario user){
        String path = host.concat(insertMovil);
        StringRequest request = new StringRequest(Request.Method.POST, path,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                estado = true;
                Log.e("mesage",response);
                Log.e("messagge",user.getDato()+ "     ********si llega");
                Log.e("mesage","true");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
                Log.e("mesage",error.toString());
                Log.e("mesage","false insert");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("documento", String.valueOf(user.getCedula()));
                parametros.put("nombre",user.getNombres());
                parametros.put("profesion",user.getProfesion());
                parametros.put("imagen", user.getDato());

                return parametros;
            }
        };

        UsuarioSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return estado;
    }

    public boolean insertUser(Usuario user){
        String path = host.concat(insert)+ "?documento="+user.getCedula()+"&nombre="+user.getNombres()+"&profesion="+user.getProfesion();
        //path = path.replace(" ","%20");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,path,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                estado = true;
                Log.e("mesage",response.toString());
                Log.e("mesage","true");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
                Log.e("mesage","false insert");

            }
        });
        UsuarioSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return estado;
    }

    public List<Usuario> findByID(String id){
        String path = host.concat(getById) + "?documento=" + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
               try {
                   JSONArray usuario = response.getJSONArray("usuario");
                   for (int i = 0; i< usuario.length() ; i++){
                       JSONObject a = usuario.getJSONObject(i);
                       Usuario user= new Usuario();
                       user.setCedula(Integer.parseInt(a.getString("documento")));
                       user.setNombres(a.getString("nombre"));
                       user.setProfesion(a.getString("profesion"));
                       user.setDato(a.getString("imagen"));
                       listaUserV.add(user);
                   }

               }catch (Exception ex){
                    Log.e("Message","Error cargar por ID");
                }
            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Messagge", "Error en listar");
                }
            });

        UsuarioSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return listaUserV;
    }

    public boolean updateUser(final Usuario user){
        String path = host.concat(Update);
        StringRequest request = new StringRequest(Request.Method.POST, path,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                estado = true;
                Log.e("mesage",response);
                Log.e("messagge",user.getDato()+ "     ********si llega");
                Log.e("mesage","true");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
                Log.e("mesage",error.toString());
                Log.e("mesage","false insert");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("documento", String.valueOf(user.getCedula()));
                parametros.put("nombre",user.getNombres());
                parametros.put("profesion",user.getProfesion());
                parametros.put("imagen", user.getDato());

                return parametros;
            }
        };

        UsuarioSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return estado;
    }

    public boolean Delete(String documento){
        String path = host.concat(Delete)+"?documento="+documento;
        JSONObject  object = new JSONObject();
        JsonObjectRequest dr = new JsonObjectRequest(Request.Method.POST, path,object,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        estado = true;
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        estado = false;
                    }
                });
        UsuarioSingletonVolly.getInstance(contexto).addToRequestQueue(dr);
        return estado;

    }


}

package com.example.macasjosue.controlador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.macasjosue.modelo.Alumno;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SWAlumnoVolly {

    //definimos url del servicio web
    String host = "http://reneguaman.000webhostapp.com";
    String insert = "/insertar_alumno.php";
    String get = "/obtener_alumnos.php";
    String getById = "/obtener_alumno_por_id.php";
    String Update = "/actualizar_alumno.php";
    String Delete = "/borrar_alumno.php";

    Context contexto;
    boolean estado;

    final private List<Alumno> listaAlumnosV;

    public SWAlumnoVolly(Context contexto) {
        this.contexto = contexto;
        this.listaAlumnosV = new ArrayList<>();
    }

    public List<Alumno> findAllStudent() {
        String path = host.concat(get);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Devuelve un json
                try {
                    String estado = response.getString("estado");
                    if (estado.equals("1")) {
                        JSONArray jsonAlumnos = response.getJSONArray("alumnos");
                        for (int i = 0; i < jsonAlumnos.length(); i++) {
                            JSONObject jsonObject = jsonAlumnos.getJSONObject(i);
                            Alumno alumno = new Alumno();
                            alumno.setIdalumno(jsonObject.getInt("idalumno"));
                            alumno.setNombrealumno(jsonObject.getString("nombre"));
                            alumno.setDireccionalumno(jsonObject.getString("direccion"));
                            listaAlumnosV.add(alumno);
                        }
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
        AlumnoSingletonVolly.getInstance(contexto).addToRequestQueue(jsonObjectRequest);
        return listaAlumnosV;
    }

    public boolean insertStudent(Alumno alumno){
        String path = host.concat(insert);
        JSONObject json = new JSONObject();
        try {
            json.put("nombre", alumno.getNombrealumno());
            json.put("direccion", alumno.getDireccionalumno());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path,json ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                estado = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        AlumnoSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return estado;
    }

    public List<Alumno> findByID(String id){
        String path = host.concat(getById) + "?idalumno=" + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
               try {
                    String estado = response.getString("estado");
                    if (estado.equals("1")) {
                        Alumno alumno = new Alumno();
                        alumno.setIdalumno(Integer.parseInt(response.getJSONObject("alumno").getString("idAlumno")));
                        alumno.setNombrealumno(response.getJSONObject("alumno").getString("nombre"));
                        alumno.setDireccionalumno(response.getJSONObject("alumno").getString("direccion"));
                        listaAlumnosV.add(alumno);
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

        AlumnoSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return listaAlumnosV;
    }

    public boolean updateStudents( Alumno alumno){
        String path = host.concat(Update);
        final JSONObject  object = new JSONObject();
        try {
            object.put("idalumno",alumno.getIdalumno());
            object.put("nombre",alumno.getNombrealumno());
            object.put("direccion",alumno.getDireccionalumno());
        }catch (Exception es){
            es.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path,object, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(contexto,response.toString(),Toast.LENGTH_SHORT);
                estado = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });

        AlumnoSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return estado;
    }

    public boolean Delte(Alumno alumno){
        String path = host.concat(Delete);
        JSONObject  object = new JSONObject();
        try {
            object.put("idalumno",alumno.getIdalumno());
        }catch (Exception es){
            es.printStackTrace();
        }
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
        AlumnoSingletonVolly.getInstance(contexto).addToRequestQueue(dr);
        return estado;

    }


}

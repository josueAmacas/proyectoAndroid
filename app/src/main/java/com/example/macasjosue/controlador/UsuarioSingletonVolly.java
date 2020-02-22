package com.example.macasjosue.controlador;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class UsuarioSingletonVolly {

    private RequestQueue queue;
    private Context context;
    private static UsuarioSingletonVolly miInstancia;

    public UsuarioSingletonVolly(Context context) {
        this.context = context;
        //get RequestQueue
        queue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if (queue == null)
            queue = Volley.newRequestQueue(context.getApplicationContext());

         return queue;
    }

    public static synchronized UsuarioSingletonVolly getInstance(Context contexto){
        if (miInstancia == null){
            miInstancia = new UsuarioSingletonVolly(contexto);
        }
        return miInstancia;
    }

    public <T> void addToRequestQueue(Request request){
        queue.add(request);
    }
}

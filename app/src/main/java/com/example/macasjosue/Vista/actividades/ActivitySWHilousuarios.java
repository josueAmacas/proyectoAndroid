package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macasjosue.R;
import com.example.macasjosue.Vista.adapter.UsuarioAdapter;
import com.example.macasjosue.controlador.SWUsuarioHilo;
import com.example.macasjosue.modelo.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivitySWHilousuarios extends AppCompatActivity implements View.OnClickListener {

    EditText cajaDocumento, cajaNombres, cajaProfesion;
    Button botonAgregar, botonListarT, botonBuscarDNI,getBotonModificar,botonEliminar,botonSelectImg;
    ImageView imagenSelected;
    UsuarioAdapter adapter;
    RecyclerView recyclerUsuario;
    List<Usuario> listaUsuarios;
    Bitmap bitmap;

    //definimos url del servicio web
    //String host = "http://192.168.0.103/MacasCaraguay";
    String host = "http://192.168.1.15/MacasCaraguay";
    String get = "/wsJSONConsultarListaImagenes.php";
    String getById = "/wsJSONConsultarUsuarioImagen.php";
    String insert = "/wsJSONRegistro.php";
    String insertMovil = "/wsJSONRegistroMovil.php";
    String Update = "/wsJSONUpdateMovil.php";
    String Delete = "/wsJSONDeleteMovil.php";
    SWUsuarioHilo sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swhilousuarios);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajaDocumento = findViewById(R.id.txtDocumentoUsuario);
        cajaNombres = findViewById(R.id.txtNombresUsuario);
        cajaProfesion = findViewById(R.id.txtProfecionUsuario);
        recyclerUsuario= findViewById(R.id.recyclerUsuario);
        imagenSelected = findViewById(R.id.imgSelectUsuario);

        botonAgregar = findViewById(R.id.btnAgregarUsuario);
        botonListarT = findViewById(R.id.btnListarUsuario);
        botonBuscarDNI = findViewById(R.id.btnBuscarUsuario);
        getBotonModificar = findViewById(R.id.btnModificarUsuario);
        botonEliminar = findViewById(R.id.btnEliminarUsuario);
        botonSelectImg = findViewById(R.id.btnSelectImgUsuario);

        botonAgregar.setOnClickListener(this);
        botonListarT.setOnClickListener(this);
        botonBuscarDNI.setOnClickListener(this);
        getBotonModificar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
        botonSelectImg.setOnClickListener(this);
    }

    private void cargarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                imagenSelected.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bitmap = redimensionarImagen(bitmap,600,800);
    }

    private Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo) {

        int ancho=bitmap.getWidth();
        int alto=bitmap.getHeight();

        if(ancho>anchoNuevo || alto>altoNuevo){
            float escalaAncho=anchoNuevo/ancho;
            float escalaAlto= altoNuevo/alto;

            Matrix matrix=new Matrix();
            matrix.postScale(escalaAncho,escalaAlto);

            return Bitmap.createBitmap(bitmap,0,0,ancho,alto,matrix,false);

        }else{
            return bitmap;
        }


    }

    private String convertirImgString(Bitmap bitmap) {

        ByteArrayOutputStream array=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte=array.toByteArray();
        String imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);

        return imagenString;
    }


    private void cargarByYD(String cadena){
        try {
            JSONObject jsonObject = new JSONObject(cadena);
            JSONArray usuarios = jsonObject.getJSONArray("usuario");
            List<Usuario> lista = new ArrayList<>();
            for (int i = 0; i< usuarios.length() ; i++){
                JSONObject a = usuarios.getJSONObject(i);
                Usuario user= new Usuario();
                user.setCedula(Integer.parseInt(a.getString("documento")));
                user.setNombres(a.getString("nombre"));
                user.setProfesion(a.getString("profesion"));
                user.setDato(a.getString("imagen"));
                lista.add(user);
            }
            cargarLista(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarLista(List<Usuario> lista){
        listaUsuarios= new ArrayList<>();
        listaUsuarios= lista;
        recyclerUsuario.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsuarioAdapter(listaUsuarios);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos(v);
            }
        });
        recyclerUsuario.setAdapter(adapter);
    }

    private void cargarRecycler(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray usuario = jsonObject.getJSONArray("usuario");
            listaUsuarios= new ArrayList<>();
            for (int i = 0; i< usuario.length() ; i++){
                JSONObject a = usuario.getJSONObject(i);
                Usuario user= new Usuario();
                user.setCedula(Integer.parseInt(a.getString("documento")));
                user.setNombres(a.getString("nombre"));
                user.setProfesion(a.getString("profesion"));
                user.setDato(a.getString("imagen"));
                listaUsuarios.add(user);
            }
            cargarLista(listaUsuarios);
        }catch (Exception ex){
            ex.printStackTrace();
            Log.e("Mensaje", "Hubo error cargar Recycler");
        }
    }

    private void cargarDatos(View v){
        int cedula = listaUsuarios.get(recyclerUsuario.getChildAdapterPosition(v)).getCedula();
        String nombre= listaUsuarios.get(recyclerUsuario.getChildAdapterPosition(v)).getNombres();
        String profesion = listaUsuarios.get(recyclerUsuario.getChildAdapterPosition(v)).getProfesion();
        Bitmap img =  listaUsuarios.get(recyclerUsuario.getChildAdapterPosition(v)).getImagen();
        cajaDocumento.setText(cedula+"");
        cajaNombres.setText(nombre);
        cajaProfesion.setText(profesion);
        imagenSelected.setImageBitmap(img);
    }

    @Override
    public void onClick(View v) {
        sw = new SWUsuarioHilo();
        switch (v.getId()){
            case R.id.btnListarUsuario:
                try {
                    String cadena = sw.execute(host.concat(get), "1").get();
                    //cajaDatos.setText(cadena);
                    cargarRecycler(cadena);
                }catch (Exception ex){
                    Log.e("mesagge","Error x aca");
                }
                break;
            case R.id.btnBuscarUsuario:
                try {
                    String idUs = cajaDocumento.getText().toString();
                    String buscarID = sw.execute(host.concat(getById) + "?documento=" + idUs, "2").get();
                   // cajaDatos.setText(buscarID);
                    cargarByYD(buscarID);
                }catch (Exception ex){
                    Log.e("mesagge","Error al buscar x DNI");
                }
                break;
            case R.id.btnAgregarUsuario:
                String ced = cajaDocumento.getText().toString();
                String nom = cajaNombres.getText().toString();
                String prof = cajaProfesion.getText().toString();
                String img = convertirImgString(bitmap);
                if(ced.length() == 0 || nom.length() == 0 || prof.length() == 0){
                    Toast.makeText(this, "Por favor llene todos los campos!!", Toast.LENGTH_SHORT).show();
                }else {
                    sw.execute(host.concat(insertMovil),"3",ced,nom,prof,img);
                    Toast.makeText(this,"Exito",Toast.LENGTH_SHORT).show();
                    Log.e("messagge","registro exitoso");
                }
                break;
            case R.id.btnModificarUsuario:
                sw.execute(host.concat(Update),"4",cajaDocumento.getText().toString(),cajaNombres.getText().toString(),cajaProfesion.getText().toString());
                Toast.makeText(this,"Exito",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEliminarUsuario:
                String id = cajaDocumento.getText().toString();
                if(id.length() == 0){
                    Toast.makeText(this,"Por Favor llene el campo ID para poder Eliminar",Toast.LENGTH_SHORT).show();
                } else {
                    sw.execute(host.concat(Delete)+ "?documento=" + id, "5");
                    //vaciarCampos();
                    Toast.makeText(this,"Alumno eliminado con exito",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnSelectImgUsuario:
                cargarImagen();
                break;


        }

    }
}

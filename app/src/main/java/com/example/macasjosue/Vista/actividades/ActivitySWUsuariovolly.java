package com.example.macasjosue.Vista.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.macasjosue.controlador.SWUsuarioVolly;
import com.example.macasjosue.modelo.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivitySWUsuariovolly extends AppCompatActivity implements View.OnClickListener {

    private static final String CARPETA_PRINCIPAL = "misImagenesApp/";//directorio principal
    private static final String CARPETA_IMAGEN = "imagenes";//carpeta donde se guardan las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//ruta carpeta de directorios
    private String path;//almacena la ruta de la imagen
    File fileImagen;

    Bitmap bitmap;

    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;

    EditText cajaDocumento, cajaNombres, cajaProfesion;
    Button botonAgregar, botonListarT, botonBuscarDNI,getBotonModificar,botonEliminar,botonselectImg;
    ImageView imagenSelected;
    UsuarioAdapter adapter;
    RecyclerView recyclerUsuario;
    List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swusuariovolly);
        cargarComponentes();
    }

    private void cargarComponentes(){
        cajaDocumento = findViewById(R.id.txtDocumentoUerVolly);
        cajaNombres = findViewById(R.id.txtNombresUserVolly);
        cajaProfesion = findViewById(R.id.txtProfecionUserVolly);
        recyclerUsuario= findViewById(R.id.recyclerUserVolly);
        imagenSelected = findViewById(R.id.imgSelectUser);

        botonAgregar = findViewById(R.id.btnAgregarUserVolly);
        botonListarT = findViewById(R.id.btnListarUservolly);
        botonBuscarDNI = findViewById(R.id.btnBuscarUserVolly);
        getBotonModificar = findViewById(R.id.btnModificarUserVolly);
        botonEliminar = findViewById(R.id.btnEliminarUserVolly);
        botonselectImg= findViewById(R.id.btnSelectImgUser);

        botonAgregar.setOnClickListener(this);
        botonListarT.setOnClickListener(this);
        botonBuscarDNI.setOnClickListener(this);
        getBotonModificar.setOnClickListener(this);
        botonEliminar.setOnClickListener(this);
        botonselectImg.setOnClickListener(this);
    }

    /*private void mostrarDialogOpciones() {
        final CharSequence[] opciones={"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    abriCamara();
                }else{
                    if (opciones[i].equals("Elegir de Galeria")){
                        Intent intent=new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("imagze/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();
    }

    private void abriCamara() {
        File miFile=new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada=miFile.exists();

        if(isCreada==false){
            isCreada=miFile.mkdirs();
        }

        if(isCreada==true){
            Long consecutivo= System.currentTimeMillis()/1000;
            String nombre=consecutivo.toString()+".jpg";

            path=Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;//indicamos la ruta de almacenamiento

            fileImagen=new File(path);

            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));

            ////
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                String authorities = this.getPackageName()+".provider";
                Uri imageUri= FileProvider.getUriForFile(this,authorities,fileImagen);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }else
            {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));
            }
            startActivityForResult(intent,COD_FOTO);

            ////

        }

    }*/

    private void cargarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case COD_SELECCIONA:
                Uri miPath=data.getData();
                imagenSelected.setImageURI(miPath);
                Log.e("Mesagge","entra1");

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),miPath);
                    imagenSelected.setImageBitmap(bitmap);
                    Log.e("Mesagge","entra2");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile(this, new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });

                bitmap= BitmapFactory.decodeFile(path);
                imagenSelected.setImageBitmap(bitmap);

                break;
        }
        bitmap=redimensionarImagen(bitmap,600,800);
        Log.e("Mesagge","entra3");
    }

    private Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo) {

        int ancho=bitmap.getWidth();
        int alto=bitmap.getHeight();
        Log.e("Mesagge","entra4");
        if(ancho>anchoNuevo || alto>altoNuevo){
            float escalaAncho=anchoNuevo/ancho;
            float escalaAlto= altoNuevo/alto;

            Matrix matrix=new Matrix();
            matrix.postScale(escalaAncho,escalaAlto);
            Log.e("Mesagge","entra5");
            return Bitmap.createBitmap(bitmap,0,0,ancho,alto,matrix,false);


        }else{
            Log.e("Mesagge","entra6");
            return bitmap;
        }


    }

    private String convertirImgString(Bitmap bitmap) {

        ByteArrayOutputStream array=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte=array.toByteArray();
        String imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);
        Log.e("Mesagge","entra7");
        return imagenString;
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
        SWUsuarioVolly sw  = new SWUsuarioVolly(this);
        switch (v.getId()){
            case R.id.btnListarUservolly:
                cargarLista(sw.findAllUsers());
                break;
            case R.id.btnBuscarUserVolly:
                String cedula = cajaDocumento.getText().toString();
                if (cedula.length() == 0){
                    Toast.makeText(this,"Por favor llene el campos ID para Buscar Alumno",Toast.LENGTH_SHORT).show();
                }else {
                    cargarLista(sw.findByID(cedula));
                }
                break;
            case R.id.btnAgregarUserVolly:
                String ced = cajaDocumento.getText().toString();
                String nom = cajaNombres.getText().toString();
                String prof = cajaProfesion.getText().toString();
                String imag = convertirImgString(bitmap);
                Log.e("Mesagge",imag+"");
                if(ced.length() == 0 || nom.length() == 0 || prof.length() == 0){
                    Toast.makeText(this, "Por favor llene todos los campos!!", Toast.LENGTH_SHORT).show();
                }else {
                    Usuario us = new Usuario();
                    us.setCedula(Integer.parseInt(ced));
                    us.setNombres(nom);
                    us.setProfesion(prof);
                    us.setDato(imag);
                    us.setImagen(bitmap);
                    sw.insertUserMovil(us);
                }
                break;
            case R.id.btnModificarUserVolly:
                String cedu = cajaDocumento.getText().toString();
                String nomb = cajaNombres.getText().toString();
                String profe = cajaProfesion.getText().toString();
                String image = convertirImgString(bitmap);
                if(cedu.length() == 0 || nomb.length() == 0 || profe.length() == 0){
                    Toast.makeText(this, "Por favor llene todos los campos!!", Toast.LENGTH_SHORT).show();
                }else {
                    Usuario us2 = new Usuario();
                    us2.setCedula(Integer.parseInt(cedu));
                    us2.setNombres(nomb);
                    us2.setProfesion(profe);
                    us2.setDato(image);
                    us2.setImagen(bitmap);
                    sw.updateUser(us2);
                }
                break;
            case R.id.btnEliminarUserVolly:
                String idE = cajaDocumento.getText().toString();
                if (idE.length() == 0){
                    Toast.makeText(this,"Por favor llene el campos ID para Eliminar Alumno",Toast.LENGTH_SHORT).show();
                } else{
                    sw.Delete(idE);
                    Toast.makeText(this,"Alumno eliminado correctamente",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnSelectImgUser:
                cargarImagen();
                //mostrarDialogOpciones();
                break;


        }

    }
}

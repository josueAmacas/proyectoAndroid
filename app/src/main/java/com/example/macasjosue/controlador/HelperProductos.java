package com.example.macasjosue.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.macasjosue.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class HelperProductos extends SQLiteOpenHelper {

    public HelperProductos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table producto(idproducto Integer NOT NULL PRIMARY KEY AUTOINCREMENT,codigo Integer, descripcion text, precio Double, cantidad Integer);");
    }

    public void insertar(Producto producto){
        ContentValues values = new ContentValues();
        values.put("codigo",producto.getCodigo());
        values.put("descripcion",producto.getDescripcion());
        values.put("precio",producto.getPrecio());
        values.put("cantidad",producto.getCantidad());
        this.getWritableDatabase().insert("producto",null,values);

    }
    public List<Producto> getAll(){
        List<Producto> lista = new ArrayList<Producto>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select *from producto", null);
        if(cursor.moveToFirst()){
            do{
                Producto p = new Producto();
                p.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                p.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                p.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                p.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                lista.add(p);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public void modificar(Producto producto){
        ContentValues values = new ContentValues();
        values.put("codigo", producto.getCodigo());
        values.put("descripcion",producto.getDescripcion());
        values.put("precio",producto.getPrecio());
        values.put("cantidad",producto.getCantidad());
        this.getWritableDatabase().update("producto",values,"codigo =" + producto.getCodigo(),null);
    }

    public void eliminarCodigo(Producto producto){
        this.getWritableDatabase().delete("producto","codigo="+producto.getCodigo(),null);
    }

    public void eliminarTodo(){
        this.getWritableDatabase().delete("producto",null,null);
    }

    public List<Producto> getProductoByCodigo(String codigo){

        List<Producto> lista = new ArrayList<Producto>();
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from producto where codigo = '" + codigo + "'",null);
        if(cursor.moveToFirst()){
            do{
                Producto p = new Producto();
                p.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                p.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                p.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                p.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                lista.add(p);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

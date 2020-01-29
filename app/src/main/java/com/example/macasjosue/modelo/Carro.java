package com.example.macasjosue.modelo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import java.util.List;

@Table(name="carro")
public class Carro extends Model {

    @Column(name = "placa", unique = true)
    private String placa;
    @Column(name = "modelo", notNull = true)
    private String modelo;
    @Column(name = "anio", notNull = true)
    private int anio;
    @Column(name = "marca", notNull = true)
    private String marca;

    public Carro() {
        super();
    }

    public Carro(String placa, String modelo, int anio, String marca) {
        this.placa = placa;
        this.modelo = modelo;
        this.anio = anio;
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public static List<Carro> getAllCars(){
        return  new Select().from(Carro.class).execute();
    }

    public static Carro getCarByPlate(String placa){
        return  new Select().from(Carro.class).where("placa=?",placa).executeSingle();
    }

    public static void deleteAllCars(){
        new Delete().from(Carro.class).execute();
    }

    public static void deleteByPlate(String placa){
        new Delete().from(Carro.class).where("placa=?",placa).execute();
    }

}

package com.example.macasjosue.modelo;

public class Alumno {

    private int idalumno;
    private String nombrealumno;
    private String direccionalumno;

    public Alumno() {
    }

    public Alumno(int idalumno, String nombrealumno, String direccionalumno) {
        this.idalumno = idalumno;
        this.nombrealumno = nombrealumno;
        this.direccionalumno = direccionalumno;
    }

    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombrealumno() {
        return nombrealumno;
    }

    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }

    public String getDireccionalumno() {
        return direccionalumno;
    }

    public void setDireccionalumno(String direccionalumno) {
        this.direccionalumno = direccionalumno;
    }
}

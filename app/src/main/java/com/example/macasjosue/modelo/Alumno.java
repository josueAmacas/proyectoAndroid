package com.example.macasjosue.modelo;

public class Alumno {

    private String idalumno;
    private String nombrealumno;
    private String direccionalumno;

    public Alumno() {
    }

    public Alumno(String idalumno, String nombrealumno, String direccionalumno) {
        this.idalumno = idalumno;
        this.nombrealumno = nombrealumno;
        this.direccionalumno = direccionalumno;
    }

    public String getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(String idalumno) {
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

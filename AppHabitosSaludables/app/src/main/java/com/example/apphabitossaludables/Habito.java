package com.example.apphabitossaludables;

public class Habito {

    private String id; // Identificador único
    private String nombre;
    private String descripcion;
    private int progreso;

    public Habito(String id, String nombre, String descripcion, int progreso) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.progreso = progreso;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }
}

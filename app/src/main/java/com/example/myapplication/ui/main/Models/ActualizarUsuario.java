package com.example.myapplication.ui.main.Models;

public class ActualizarUsuario {
    private  String id;
    private String role;

    private String nombre;
    private String email;
    private String cedula;


    /**
     * No args constructor for use in serialization
     *
     */
    public ActualizarUsuario() {
    }

    /**
     *
     * @param role
     * @param cedula

     * @param nombre
     * @param email
     */
    public ActualizarUsuario(String role, String nombre, String email, String cedula) {
        super();
        this.role = role;
        this.nombre = nombre;
        this.email = email;
        this.cedula = cedula;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package com.example.myapplication.ui.main.Models;

public class DatosUsuariosP {
    private String role;
    private String nombre;
    private String email;
    private String cedula;
    private String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public DatosUsuariosP() {
    }

    /**
     *
     * @param role
     * @param cedula
     * @param password

     * @param nombre
     * @param email
     */
    public DatosUsuariosP(String role, String nombre, String email, String cedula,String password) {
        super();
        this.role = role;
        this.nombre = nombre;
        this.email = email;
        this.cedula = cedula;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
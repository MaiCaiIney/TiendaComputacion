package com.tienda.clientes;

public class Cliente {
    private static int autoid = 0;
    private final int id;
    private String nombre;
    private String dni;
    private String email;
    private boolean premium;//si verdadero el tipo recibe descuento

    public Cliente(String nombre, String dni) {
        this.id = autoid;
        this.nombre = nombre;
        this.dni = dni;
        this.premium=false;
        autoid++;
    }

    public Cliente(String nombre, String dni, String email,boolean premium) {
        this.id = autoid;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.premium= premium;

        autoid++;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%d. %s - %s ", this.id, this.dni, this.nombre);
    }
}

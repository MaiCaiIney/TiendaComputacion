package com.tienda.clientes;

public class Cliente {
    private static int autoid = 0;
    private final int id;
    private String nombre;
    private String dmi;
    private String email;

    /**
     * @param nombre Nombre
     * @param dni    Número de documento
     */
    public Cliente(String nombre, String dni) {
        this.id = autoid;
        this.nombre = nombre;
        this.dmi = dni;

        autoid++;
    }

    public Cliente(String nombre, String dmi, String email) {
        this.id = autoid;
        this.nombre = nombre;
        this.dmi = dmi;
        this.email = email;

        autoid++;
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

    public String getDmi() {
        return dmi;
    }

    public void setDmi(String dmi) {
        this.dmi = dmi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

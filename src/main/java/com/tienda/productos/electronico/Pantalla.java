package com.tienda.productos.electronico;

public class Pantalla extends ProductoElectronico {
    private float resolucion;

    public Pantalla(String nombre, float precio, String marca, float resolucion) {
        super(nombre, precio, marca);
        this.resolucion = resolucion;
    }

    public Pantalla(String nombre, float precio, int stock, String marca, float resolucion) {
        super(nombre, precio, stock, marca);
        this.resolucion = resolucion;
    }

    public float getResolucion() {
        return resolucion;
    }

    public void setResolucion(float resolucion) {
        this.resolucion = resolucion;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Resolución: %.2f", this.resolucion);
    }
}

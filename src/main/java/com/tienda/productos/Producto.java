package com.tienda.productos;

import java.util.StringJoiner;

/**
 * Responsabilidades de la clase Producto:
 * 1. Encapsular los datos comunes a todos los productos
 * 2. Proporcionar acceso seguro a los datos
 * 3. Servir como base para la especialización
 * 4. Facilitar el polimorfismo
 */
public abstract class Producto {
    private static int autoid = 0;
    private final int id;
    private String nombre;
    private float precio;
    private int stock;

    public Producto(String nombre, float precio, int stock) {
        this.id = autoid;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;

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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        if (precio < 0) return;
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) return;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("%d. %s. Precio: $%.2f. Stock: %d. ", this.id, this.nombre, this.precio, this.stock);
    }
}

package com.tienda.productos.electronico;

import com.tienda.productos.Producto;

/**
 * Responsabilidad de la clase ProductoElectronico:
 * 1. Especificar atributos únicos de productos electrónicos
 * 2. Implementar comportamientos específicos
 * 3. Facilitar el polimorfismo con especificidad
 * <p>
 * Las clases derivadas de ProductoElectronico llevan la especialización un paso más allá dentro de la jerarquía de herencia,
 * enfodcándose en características y comportamientos específicos de tipos particulares de productos.
 */
public abstract class ProductoElectronico extends Producto {
    private String marca;

    public ProductoElectronico(String nombre, float precio, String marca) {
        super(nombre, precio, 0);
        this.marca = marca;
    }

    public ProductoElectronico(String nombre, float precio, int stock, String marca) {
        super(nombre, precio, stock);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Marca: %s. ", this.marca);
    }
}

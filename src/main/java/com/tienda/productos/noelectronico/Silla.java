package com.tienda.productos.noelectronico;

public class Silla extends ProductoNoElectronico {
    private String material;

    public Silla(String nombre, float precio) {
        super(nombre, precio);
    }

    public Silla(String nombre, float precio, int stock) {
        super(nombre, precio, stock);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return String.format("[SILLA] %s. Material: %s.", super.toString(), this.material);
    }
}

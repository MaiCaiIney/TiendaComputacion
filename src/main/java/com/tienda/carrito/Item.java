package com.tienda.carrito;

import com.tienda.productos.Producto;

public class Item {
    private final int cantidad;
    private final Producto producto;

    public Item(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public double getSubtotal() {
        return this.cantidad * this.getProducto().getPrecio();
    }
}

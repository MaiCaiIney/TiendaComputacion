package com.tienda.carrito;

import com.tienda.productos.Producto;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Item> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public String addProducto(Producto producto, int cantidad) {
        if (this.items == null) this.items = new ArrayList<>();

        if(producto.getStock() < cantidad) return "No existe stock suficiente.";

        Item item = new Item(cantidad, producto);
        if(this.items.add(item)) return "Producto agregado al carrito.";
        else return "No se pudo agregar el producto al carrito.";
    }

    public void mostrarCarrito() {
        this.items.forEach(item -> {
            float total = item.getProducto().getPrecio() * item.getCantidad();
            System.out.printf("\t%s\t%d x $%.2f\t$%.2f\n", item.getProducto().getNombre(), item.getCantidad(), item.getProducto().getPrecio(), total);
        });
    }
}

package com.tienda.carrito;

import com.tienda.productos.Producto;

import java.util.ArrayList;

public class Carrito {
    private ArrayList<Item> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public String agregarProducto(Producto producto, int cantidad) {
        if (this.items == null) this.items = new ArrayList<>();

        if (producto.getStock() < cantidad) return "No existe stock suficiente.";

        Item item = new Item(cantidad, producto);
        if (this.items.add(item)) return "Producto agregado al carrito.";
        else return "No se pudo agregar el producto al carrito.";
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void mostrarCarrito() {
        this.items.forEach(item -> {
            System.out.printf("\t%-25s\t%d x $%.2f\t$%.2f\n", item.getProducto().getNombre(), item.getCantidad(), item.getProducto().getPrecio(), item.getSubtotal());
        });
        System.out.printf("\tTOTAL: $%.2f%n", getTotal());
    }

    public double getTotal() {
        return this.items.stream().mapToDouble(Item::getSubtotal).sum();
    }
}

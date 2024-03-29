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

    public String mostrarCarrito() {
        StringBuilder sb = new StringBuilder();
        this.items.forEach(item -> {
            sb.append(String.format("\t%-25s\t%d x $%.2f\t$%.2f\n", item.getProducto().getNombre(), item.getCantidad(), item.getProducto().getPrecio(), item.getSubtotal()));
        });
        sb.append(String.format("\tTOTAL: $%.2f%n", getTotal()));
        return sb.toString();
    }

    public double getTotal() {
        return this.items.stream().mapToDouble(Item::getSubtotal).sum();
    }
}

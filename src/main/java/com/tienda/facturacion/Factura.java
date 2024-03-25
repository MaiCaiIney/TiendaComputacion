package com.tienda.facturacion;

import com.tienda.clientes.Cliente;
import com.tienda.productos.Producto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
    private static int autoid = 0;
    private final int id;
    private final Cliente cliente;
    private final ArrayList<Producto> productos;
    private final LocalDate fecha;
    private final float monto;

    public Factura(Cliente cliente, ArrayList<Producto> productos) {
        this.id = autoid;
        this.cliente = cliente;
        this.productos = productos;
        this.fecha = LocalDate.now();
        this.monto = calcularMonto();

        autoid++;
    }

    private float calcularMonto() {
        float total = 0;
        for (Producto producto : productos) {
            total = +producto.getPrecio();
        }
        return total;
    }
}

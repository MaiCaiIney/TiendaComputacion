package com.tienda.facturacion;

import com.tienda.carrito.Carrito;
import com.tienda.carrito.Item;
import com.tienda.clientes.Cliente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Factura {
    private static int autoid = 1;
    private final int id;
    private final Cliente cliente;
    private final List<Item> items;
    private final LocalDate fecha;
    private final double total;

    public Factura(Cliente cliente, Carrito carrito) {
        this.id = autoid;
        this.cliente = cliente;
        this.items = carrito.getItems();
        this.fecha = LocalDate.now();
        this.total = carrito.getTotal();

        autoid++;
    }

    public void mostrar() {

        String fechaFormato = this.fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        StringBuilder items = new StringBuilder();
        for (Item item : this.items) {
            items.append(String.format("%-25s|  $%.2f  | %d |  $%.2f\n",
                    item.getProducto().getNombre(), item.getProducto().getPrecio(), item.getCantidad(), item.getSubtotal() ));
        }

        String formato = """
                
                ------------------------------------------------------------
                \t         TIENDA               \t|\tFecha: %s
                \t     DE COMPUTACIÓN             |\tNro. %d
                ------------------------------------------------------------
                \t%s
                \tIVA Consumidor Final - DNI %s
                ------------------------------------------------------------
                \t     CONCEPTO            |   PRECIO   | U |   SUBT
                ------------------------------------------------------------
                \t%s
                ------------------------------------------------------------
                \t                                      TOTAL $%.2f
                ------------------------------------------------------------
                """;

        System.out.println(String.format(formato, fechaFormato, this.id, this.cliente.getNombre(), this.cliente.getDni(), items, this.total));
    }
}

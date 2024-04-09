package com.tienda.facturacion;

import com.tienda.carrito.Carrito;
import com.tienda.carrito.Item;
import com.tienda.clientes.Cliente;
import com.tienda.clientes.Corportativo;
import com.tienda.clientes.Descuento;
import com.tienda.clientes.Premium;

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
    float agregarDescuento(Cliente cliente,float total){
        if(cliente instanceof Premium){
            total= (float) (total*0.7);//descuento del 30
        }
        else{
            if(cliente instanceof Corportativo){
                total=(float)(total*0.5);//descuento del 50
            }
        }
        return total;
    }


    public String mostrar() {

        try {
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

            return String.format(formato, fechaFormato, this.id, this.cliente.getNombre(), this.cliente.getDni(), items, this.total=agregarDescuento(cliente,total));//se agrego la funcion
        } catch (Exception e) {
            return null;
        }
    }
}

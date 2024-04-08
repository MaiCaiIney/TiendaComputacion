package com.tienda;

import com.github.javafaker.Faker;
import com.tienda.carrito.Carrito;
import com.tienda.clientes.Cliente;
import com.tienda.facturacion.Factura;
import com.tienda.menu.Menu;
import com.tienda.menu.MenuConsola;
import com.tienda.menu.MenuVisual;
import com.tienda.productos.Producto;
import com.tienda.productos.electronico.Mouse;
import com.tienda.productos.electronico.Pantalla;
import com.tienda.productos.electronico.ProductoElectronico;
import com.tienda.productos.electronico.Teclado;
import com.tienda.productos.noelectronico.Pad;
import com.tienda.productos.noelectronico.ProductoNoElectronico;
import com.tienda.productos.noelectronico.Silla;

import java.util.*;
import java.util.stream.Collectors;

public class Tienda {
    private final List<Cliente> clientes;
    private final List<Producto> productos;

    private Carrito carrito;

    private final List<Factura> facturas;

    private final Menu menu;

    public Tienda(boolean menuVisual) {
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.menu = (menuVisual) ? new MenuVisual(this) : new MenuConsola(this);
    }

    public void iniciar() {
        cargarDatos();
        this.menu.mostrarMenuPrincipal();
    }

    public String listarProductos() {
        if(this.productos.isEmpty()) return "\nNo hay productos cargados.\n";

        StringBuilder sb = new StringBuilder();

        sb.append("\nProductos Electrónicos\n");
        List<Producto> electronicos = this.productos.stream().filter(producto -> producto instanceof ProductoElectronico).sorted(Comparator.comparing(Producto::getId)).toList();
        if (electronicos.isEmpty()) {
            sb.append("\tNo hay productos.\n");
        } else {
            electronicos.forEach(p -> sb.append("\t").append(p).append("\n"));
        }

        sb.append("\nProductos No Electrónicos\n");
        List<Producto> noElectronicos = this.productos.stream().filter(producto -> producto instanceof ProductoNoElectronico).sorted(Comparator.comparing(Producto::getId)).toList();
        if (noElectronicos.isEmpty()) {
            sb.append("\tNo hay productos.\n");
        } else {
            electronicos.forEach(p -> sb.append("\t").append(p).append("\n"));
        }

        return sb.toString();
    }

    public String listarClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nClientes\n");

        if (this.clientes.isEmpty()) {
            sb.append("\tNo hay clientes registrados.\n");
        } else {
            this.clientes.forEach(cliente -> sb.append("\t").append(cliente).append("\n"));
        }

        return sb.toString();
    }

    public void iniciarCarrito() {
        this.carrito = new Carrito();
    }

    public String listarCarrito() {
        return this.carrito.mostrarCarrito();
    }

    public String agregarProductoAlCarrito(Producto producto, int cantidad) {
        return this.carrito.agregarProducto(producto, cantidad);
    }

    public double getTotalCarrito() {
        return this.carrito.getTotal();
    }

    public void finalizarCarrito() {
        this.carrito = null;
    }

    public Producto buscarProducto(int id) {
        for(Producto producto : this.productos) {
            if(producto.getId() == id) return producto;
        }
        return null;
    }

    public List<Producto> buscarProducto(String nombre) {
        return this.productos.stream().filter(producto -> producto.getNombre().toUpperCase().contains(nombre.toUpperCase())).collect(Collectors.toList());
    }

    public Cliente buscarCliente(String dni) {
        for(Cliente cliente : this.clientes) {
            if(cliente.getDni().equals(dni)) return cliente;
        }
        return null;
    }

    public void actualizarStock() {
        this.carrito.getItems().forEach(item -> {
            int stock = item.getProducto().getStock() - item.getCantidad();
            item.getProducto().setStock(stock);
        });
    }

    public String generarFactura(Cliente cliente) {
        Factura factura = new Factura(cliente, this.carrito);
        this.facturas.add(factura);
        return factura.mostrar();
    }

    private void cargarDatos() {
        Faker faker = new Faker();
        int contador = 0;
        // Cargar clientes random
        do {
            String dni = String.valueOf(faker.number().randomNumber(8, true));
            Cliente cliente = new Cliente(faker.name().fullName(), dni, faker.internet().emailAddress(),false);
            clientes.add(cliente);

            contador++;
        } while (contador < 10);

        contador = 0;

        // Cargar productos random
        do {
            String nombre = faker.commerce().productName();
            float precio = faker.number().numberBetween(2000, 5000);
            int stock = faker.number().randomDigitNotZero();
            String marca = faker.commerce().department();

            Pad pad = new Pad(nombre, precio, stock);
            pad.setTamanio(faker.number().randomDigit());
            agregarProducto(pad);

            Pad pad2 = new Pad(nombre, precio, stock);
            pad.setTamanio(faker.number().randomDigit());
            agregarProducto(pad2);

            nombre = faker.commerce().productName();
            precio = faker.number().numberBetween(25000, 100000);
            stock = faker.number().randomDigitNotZero();
            Silla silla = new Silla(nombre, precio, stock);
            silla.setMaterial(faker.commerce().material());
            silla.setErgonomico(faker.random().nextBoolean());
            agregarProducto(silla);

            nombre = faker.commerce().productName();
            precio = faker.number().numberBetween(20000, 75000);
            stock = faker.number().randomDigitNotZero();
            Mouse mouse = new Mouse(nombre, precio, stock, marca);
            mouse.setGamer(faker.random().nextBoolean());
            agregarProducto(mouse);

            nombre = faker.commerce().productName();
            precio = faker.number().numberBetween(100000, 500000);
            stock = faker.number().randomDigitNotZero();
            marca = faker.commerce().department();
            Producto pantalla = new Pantalla(nombre, precio, stock, marca, faker.number().numberBetween(1, 120));
            agregarProducto(pantalla);

            nombre = faker.commerce().productName();
            precio = faker.number().numberBetween(10000, 50000);
            stock = faker.number().randomDigitNotZero();
            marca = faker.commerce().department();
            Producto teclado = new Teclado(nombre, precio, stock, marca);
            agregarProducto(teclado);

            contador++;
        } while (contador < 3);
    }

    private void agregarProducto(Producto producto) {
        if (!this.productos.contains(producto)) productos.add(producto);
    }
}

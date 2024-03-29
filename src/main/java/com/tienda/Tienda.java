package com.tienda;

import com.github.javafaker.Faker;
import com.tienda.carrito.Carrito;
import com.tienda.clientes.Cliente;
import com.tienda.facturacion.Factura;
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
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Producto> productos;

    private Carrito carrito;

    private final ArrayList<Factura> facturas;

    private final Scanner scanner;

    public Tienda() {
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        cargarDatos();
        mostrarMenuPrincipal();
    }

    private void mostrarMenuPrincipal() {
        String menu = """

                Menú
                \t1. Listar todos los productos
                \t2. Iniciar carrito de compra
                \t0. Finalizar
                
                Opción:""";
        int opcion = 0;

        do {
            try {
                if(opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }

                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 0 -> finalizar();
                    case 1 -> listarProductos();
                    case 2 -> iniciarCarrito();
                    default -> opcion = gestionarValorInvalido(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                System.out.println("Error: " + e.getLocalizedMessage());
                opcion = -1;
            }
        } while(opcion != 0);
    }

    private void mostrarMenuCarrito() {
        String menu = """
                
                Menú
                \t1. Listar todos los productos
                \t2. Ingresar producto por código
                \t3. Buscar producto por nombre
                \t4. Continuar compra
                \t0. Cancelar compra
                
                Opción:""";
        int opcion = 0;

        do {
            try {
                if(opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }

                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 0 -> {
                        finalizarCarrito();
                        finalizar();
                        return;
                    }
                    case 1 -> listarProductos();
                    case 2 -> ingresarItemPorCodigo();
                    case 3 -> buscarProductoPorNombre();
                    case 4 -> {
                        continuarCompra();
                        return;
                    }
                    default -> opcion = gestionarValorInvalido(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                System.out.println("Error: " + e.getLocalizedMessage());
                opcion = -1;
            }
        } while(opcion != 0);
    }

    private void listarProductos() {
        if(this.productos.isEmpty()) System.out.println("\nNo hay productos cargados.\n");

        System.out.println("\nProductos Electrónicos\n");

        var electronicos = this.productos.stream().filter(producto -> producto instanceof ProductoElectronico).sorted(Comparator.comparing(Producto::getId));
        electronicos.forEach(p -> System.out.println("\t" + p));

        System.out.println("\nProductos No Electrónicos\n");
        var noElectronicos = this.productos.stream().filter(producto -> producto instanceof ProductoNoElectronico).sorted(Comparator.comparing(Producto::getId));
        noElectronicos.forEach(p -> System.out.println("\t" + p));
    }

    private void iniciarCarrito() {
        this.carrito = new Carrito();
        mostrarMenuCarrito();
    }

    private void ingresarItemPorCodigo() {
        try {
            System.out.println("\nCódigo: ");
            int id = this.scanner.nextInt();
            Producto producto = buscarProducto(id);

            if(producto == null) {
                System.out.printf("Producto %d no encontrado.%n", id);
                return;
            }

            System.out.printf("Producto %s.%n", producto.getNombre().toUpperCase());
            System.out.println("Cantidad: ");

            int cantidad = this.scanner.nextInt();

            if(cantidad < 1) {
                mostrarMensajeValorInvalido();
                return;
            }

            String result = this.carrito.agregarProducto(producto, cantidad);
            System.out.println(result);
        } catch (Exception e) {
            this.scanner.nextLine();
            mostrarMensajeError();
            ingresarItemPorCodigo();
        }
    }

    private void buscarProductoPorNombre() {
        try {
            System.out.println("\nNombre: ");
            String nombre = this.scanner.next();
            List<Producto> productos = buscarProducto(nombre);

            System.out.println("\nProductos encontrados:\n");
            productos.forEach(p -> System.out.println("\t" + p));
        } catch (Exception e) {
            mostrarMensajeError();
            buscarProductoPorNombre();
        }
    }

    private Producto buscarProducto(int id) {
        for(Producto producto : this.productos) {
            if(producto.getId() == id) return producto;
        }
        return null;
    }

    private List<Producto> buscarProducto(String nombre) {
        return this.productos.stream().filter(producto -> producto.getNombre().toUpperCase().contains(nombre.toUpperCase())).collect(Collectors.toList());
    }

    private void continuarCompra() {
        String menu = """
                
                Menú
                \t1. Mostrar carrito
                \t2. Realizar pago
                \t3. Agregar productos al carrito
                \t0. Cancelar compra
                
                Opción:""";
        int opcion = 0;

        do {
            try {
                if(opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }
                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 0 -> {
                        finalizarCarrito();
                        finalizar();
                    }
                    case 1 -> mostrarCarrito();
                    case 2 -> {
                        realizarPago();
                        return;
                    }
                    case 3 -> mostrarMenuCarrito();
                    default -> opcion = gestionarValorInvalido(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                System.out.println("Error: " + e.getLocalizedMessage());
                opcion = -1;
            }
        } while(opcion != 0);
    }

    private void mostrarCarrito() {
        System.out.println("\nCARRITO:");
        this.carrito.mostrarCarrito();
    }

    private void realizarPago() {
        String menu = """
                
                Menú
                \t1. Pago en efectivo
                \t2. Pago con tarjeta de débito o crédito
                \t0. Cancelar compra
                
                Opción:""";
        int opcion = 0;

        do {
            try {
                if(opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }
                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 0 -> {
                        finalizarCarrito();
                        finalizar();
                    }
                    case 1 -> {
                        cobrarEfectivo();
                        return;
                    }
                    case 2 -> {
                        cobrarTarjeta();
                        return;
                    }
                    default -> opcion = gestionarValorInvalido(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                System.out.println("Error: " + e.getLocalizedMessage());
                opcion = -1;
            }
        } while(opcion < 0);
    }

    private void cobrarEfectivo() {
        String menu = "\nMonto en efectivo:";
        int monto = 0;

        do {
            try {
                if(monto < 0) {
                    monto = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    monto = this.scanner.nextInt();
                }

                if(monto < carrito.getTotal()) {
                    System.out.println("\nMonto insuficiente para saldar el monto total de la compra.");
                } else {
                    System.out.println("\nPago registrado en efectivo.");
                    finalizarCompra();
                    return;
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                monto = -1;
            }
        } while(monto < 0 || monto < carrito.getTotal());
    }

    private void cobrarTarjeta() {
        System.out.println("Pago registrado con tarjeta.");
        finalizarCompra();
    }

    private void finalizarCompra() {
        actualizarStock();
        mostrarMenuCliente();
        finalizarCarrito();
    }

    private void actualizarStock() {
        this.carrito.getItems().forEach(item -> {
            int stock = item.getProducto().getStock() - item.getCantidad();
            item.getProducto().setStock(stock);
        });
    }

    private void mostrarMenuCliente() {
        String menu = """
                
                Menú
                \t1. Listar clientes
                \t2. Generar factura
                
                Opción:""";
        int opcion = 0;

        do {
            try {
                if(opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }

                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 1 -> listarClientes();
                    case 2 -> {
                        boolean resultado = generarFactura();
                        opcion = resultado ? 2 : -1;
                    }
                    default -> opcion = gestionarValorInvalido(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                System.out.println("Error: " + e.getLocalizedMessage());
                opcion = -1;
            }
        } while(opcion != 2);
    }

    private void listarClientes() {
        System.out.println("\nClientes\n");
        this.clientes.forEach( cliente -> System.out.println("\t" + cliente));
    }

    private boolean generarFactura() {
        Cliente cliente = buscarCliente();
        if(cliente == null) return false;
        Factura factura = new Factura(cliente, this.carrito);
        factura.mostrar();
        return this.facturas.add(factura);
    }

    private Cliente buscarCliente() {
        try {
            System.out.println("\nDNI del cliente:");
            long dni = this.scanner.nextLong();
            Cliente cliente = buscarCliente(String.valueOf(dni));
            if(cliente == null) {
                System.out.println("Cliente no encontrado.");
            }
            return cliente;
        } catch (Exception e) {
            this.scanner.nextLine();
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return null;
    }

    private Cliente buscarCliente(String dni) {
        for(Cliente cliente : this.clientes) {
            if(cliente.getDni().equals(dni)) return cliente;
        }
        return null;
    }

    private void finalizarCarrito() {
        this.carrito = null;
    }

    private int gestionarError(String menu) {
        mostrarMensajeError();
        System.out.println(menu);
        try {
            return this.scanner.nextInt();
        } catch (Exception e) {
            this.scanner.nextLine();
            return -1;
        }
    }

    private int gestionarValorInvalido(String menu) {
        mostrarMensajeValorInvalido();
        System.out.print(menu);
        try {
            return this.scanner.nextInt();
        } catch (Exception e) {
            this.scanner.nextLine();
            return gestionarError(menu);
        }

    }

    private void finalizar() {

    }

    private void cargarDatos() {
        Faker faker = new Faker();
        int contador = 0;
        // Cargar clientes random
        do {
            String dni = String.valueOf(faker.number().randomNumber(8, true));
            Cliente cliente = new Cliente(faker.name().fullName(), dni, faker.internet().emailAddress());
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

    private void mostrarMensajeValorInvalido() {
        System.out.println("\nEl valor ingresado no es válido.");
    }

    private void mostrarMensajeError() {
        System.out.println("\nNo fue posible procesar el valor.");
    }
}

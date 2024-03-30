package com.tienda.menu;

import com.tienda.Tienda;
import com.tienda.clientes.Cliente;
import com.tienda.facturacion.Factura;
import com.tienda.productos.Producto;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class MenuConsola extends Menu {

    private final Scanner scanner;

    public MenuConsola(Tienda tienda) {
        super(tienda);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void mostrarMenuPrincipal() {
        String menu = """
                                
                Menú
                \t1. Listar todos los productos
                \t2. Iniciar carrito de compra
                \t3. Mostrar facturas realizadas
                \t0. Finalizar

                Opción:""";
        int opcion = 0;

        do {
            try {
                if (opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }

                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 0 -> {
                        return;
                    }
                    case 1 -> System.out.println(tienda.listarProductos());
                    case 2 -> {
                        tienda.iniciarCarrito();
                        mostrarMenuCarrito();
                    }
                    case 3 -> mostrarFacturasporDNI();
                    default -> opcion = gestionarValorInvalido(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                System.out.println("Error: " + e.getLocalizedMessage());
                opcion = -1;
            }
        } while (opcion != 0);
    }

    @Override
    protected void mostrarMenuCarrito() {
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
                if (opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }

                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 0 -> {
                        tienda.finalizarCarrito();
                        return;
                    }
                    case 1 -> System.out.println(tienda.listarProductos());
                    case 2 -> ingresarItemPorCodigo();
                    case 3 -> buscarProductoPorNombre();
                    case 4 -> {
                        mostrarMenuCompra();
                        return;
                    }
                    default -> opcion = gestionarValorInvalido(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                System.out.println("Error: " + e.getLocalizedMessage());
                opcion = -1;
            }
        } while (opcion != 0);
    }

    @Override
    protected void mostrarMenuCompra() {
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
                if (opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }
                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 0 -> tienda.finalizarCarrito();
                    case 1 -> System.out.println(tienda.listarCarrito());
                    case 2 -> {
                        mostrarMenuPago();
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
        } while (opcion != 0);
    }

    @Override
    protected void mostrarMenuPago() {
        String menu = """
                                
                Menú
                \t1. Pago en efectivo
                \t2. Pago con tarjeta de débito o crédito
                \t0. Cancelar compra
                                
                Opción:""";
        int opcion = 0;

        do {
            try {
                if (opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }
                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 0 -> tienda.finalizarCarrito();
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
        } while (opcion < 0);
    }

    @Override
    protected void mostrarMenuCliente() {
        String menu = """
                                
                Menú
                \t1. Listar clientes
                \t2. Generar factura
                                
                Opción:""";
        int opcion = 0;

        do {
            try {
                if (opcion < 0) {
                    opcion = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    opcion = this.scanner.nextInt();
                }

                switch (opcion) {
                    case -1 -> opcion = gestionarError(menu);
                    case 1 -> System.out.println(tienda.listarClientes());
                    case 2 -> {
                        String factura = generarFactura();
                        if (factura == null) opcion = -1;
                        else System.out.println(factura);
                    }
                    default -> opcion = gestionarValorInvalido(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                System.out.println("Error: " + e.getLocalizedMessage());
                opcion = -1;
            }
        } while (opcion != 2);
    }

    private void ingresarItemPorCodigo() {
        try {
            System.out.println("\nCódigo: ");
            int id = this.scanner.nextInt();
            Producto producto = tienda.buscarProducto(id);

            if (producto == null) {
                System.out.printf("Producto %d no encontrado.%n", id);
                return;
            }

            System.out.printf("Producto %s.%n", producto.getNombre().toUpperCase());
            System.out.println("Cantidad: ");

            int cantidad = this.scanner.nextInt();

            if (cantidad < 1) {
                mostrarMensajeValorInvalido();
                return;
            }

            String resultado = this.tienda.agregarProductoAlCarrito(producto, cantidad);
            System.out.println(resultado);
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
            List<Producto> productos = tienda.buscarProducto(nombre);

            System.out.println("\nProductos encontrados:\n");
            productos.forEach(p -> System.out.println("\t" + p));
        } catch (Exception e) {
            mostrarMensajeError();
            buscarProductoPorNombre();
        }
    }

    private void cobrarEfectivo() {
        String menu = "\nMonto en efectivo:";
        int monto = 0;

        do {
            try {
                if (monto < 0) {
                    monto = gestionarError(menu);
                } else {
                    System.out.println(menu);
                    monto = this.scanner.nextInt();
                }

                if (monto < tienda.getTotalCarrito()) {
                    System.out.println("\nMonto insuficiente para saldar el monto total de la compra.");
                } else {
                    System.out.println("\nPago registrado en efectivo.");
                    tienda.actualizarStock();
                    mostrarMenuCliente();
                    tienda.finalizarCarrito();
                    return;
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                monto = -1;
            }
        } while (monto < 0 || monto < tienda.getTotalCarrito());
    }

    private void cobrarTarjeta() {
        System.out.println("Pago registrado con tarjeta.");
        tienda.actualizarStock();
        mostrarMenuCliente();
        tienda.finalizarCarrito();
    }

    private String generarFactura() {
        Cliente cliente = buscarCliente();
        if (cliente == null) return null;
        return tienda.generarFactura(cliente);
    }

    private Cliente buscarCliente() {
        try {
            System.out.println("\nDNI del cliente:");
            long dni = this.scanner.nextLong();
            Cliente cliente = tienda.buscarCliente(String.valueOf(dni));
            if (cliente == null) {
                System.out.println("Cliente no encontrado.");
            }
            return cliente;
        } catch (Exception e) {
            this.scanner.nextLine();
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return null;
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

    private void mostrarMensajeValorInvalido() {
        System.out.println("\nEl valor ingresado no es válido.");
    }

    private void mostrarMensajeError() {
        System.out.println("\nNo fue posible procesar el valor.");
    }

    private void mostrarFacturasporDNI() {
        try {
            System.out.println("Ingrese DNI:");
            String dni = scanner.next();

            Cliente cliente = tienda.buscarCliente(dni);
            if (cliente != null) {
                boolean encontradas = false;
                System.out.println("Facturas para el cliente con DNI " + dni + ":");
                for (Factura factura : tienda.getFacturas()) {
                    if (factura.getCliente().getDni().equals(dni)) {
                        System.out.println(factura.mostrar());
                        encontradas = true;
                    }
                }
                if (!encontradas) {
                    System.out.println("No se encontraron facturas para el cliente con DNI " + dni + ".");
                }
            } else {
                System.out.println("No se encontró ningún cliente con el DNI " + dni + ".");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}

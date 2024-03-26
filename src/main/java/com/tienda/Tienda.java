package com.tienda;

import com.github.javafaker.Faker;
import com.tienda.carrito.Carrito;
import com.tienda.clientes.Cliente;
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

    private Scanner scanner;

    public Tienda() {
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        cargarDatos();
        gestionarMenuPrincipal();
    }

    private void gestionarMenuPrincipal() {
        String menu = """

                Menú
                \t1. Listar todos los productos
                \t2. Iniciar carrito de compra
                \t0. Finalizar

                Ingresar opción: """;
        //String menu = "Menu\n\t1. Listar\n\t2.Iniciar\n\t0. Finalizar";
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
                    case 1 -> listarProductosPorCategorias();
                    case 2 -> iniciarCarrito();
                    default -> opcion = gestionarOpcionInvalida(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                opcion = -1;
            }
        } while(opcion != 0);
    }

    private void gestionarMenuCarrito() {
        String menu = """
                
                Menú Carrito
                \t1. Listar todos los productos
                \t2. Ingresar producto por código
                \t3. Buscar producto por nombre
                \t4. Continuar compra
                \t0. Finalizar
                
                Ingresar opción:\s""";
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
                    case 1 -> listarProductosPorCategorias();
                    case 2 -> agregarItemsAlCarrito();
                    case 3 -> gestionarBusquedaPorNombre();
                    case 4 -> continuarCompra();
                    default -> opcion = gestionarOpcionInvalida(menu);
                }
            } catch (Exception e) {
                this.scanner.nextLine();
                opcion = -1;
            }
        } while(opcion != 0);
    }

    private void listarProductosPorCategorias() {
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
        gestionarMenuCarrito();
    }

    private void agregarItemsAlCarrito() {
        try {
            System.out.println("Código del producto: ");
            int id = this.scanner.nextInt();
            Producto producto = buscarProducto(id);

            if(producto == null) {
                System.out.printf("El producto con código %d no existe.%n", id);
                return;
            }

            System.out.printf("Vas a comprar %s.%n", producto.getNombre().toUpperCase());
            System.out.println("Cantidad a comprar: ");
            int cantidad = this.scanner.nextInt();

            if(cantidad < 1) {
                System.out.printf("Cantidad inválida.%n");
                return;
            }

            String result = this.carrito.addProducto(producto, cantidad);
            System.out.println(result);
        } catch (Exception e) {
            this.scanner.nextLine();
            System.out.println("Por favor ingrese opciones válidas.");
            agregarItemsAlCarrito();
        }
    }

    private void gestionarBusquedaPorNombre() {
        try {
            System.out.println("Nombre a buscar: ");
            String nombre = this.scanner.next();
            List<Producto> productos = buscarProducto(nombre);

            System.out.println("\n\tProductos encontrados:\n");
            productos.forEach(p -> System.out.println("\t" + p));
        } catch (Exception e) {
            System.out.println("\nPor favor ingrese opciones válidas.");
            gestionarBusquedaPorNombre();
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
        System.out.println("\nVas a comprar:\n");
        this.carrito.mostrarCarrito();
    }

    private void finalizarCarrito() {
        this.carrito = null;
    }

    private int gestionarError(String menu) {
        System.out.println("\nOcurrió un error al interpretar la opción.\n" + menu);

        try {
            return this.scanner.nextInt();
        } catch (Exception e) {
            this.scanner.nextLine();
            return -1;
        }
    }

    private int gestionarOpcionInvalida(String menu) {
        System.out.println("\nLa opción ingresada no es válida.\n" + menu);
        try {
            return this.scanner.nextInt();
        } catch (Exception e) {
            this.scanner.nextLine();
            return gestionarError(menu);
        }

    }

    private void finalizar() {
        System.out.println("\nGracias por utilizar el sistema.");
    }

    private void cargarDatos() {
        Faker faker = new Faker();
        int contador = 0;
        // Cargar clientes random
        do {
            Cliente cliente = new Cliente(faker.name().fullName(), faker.internet().emailAddress());
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
        if(!this.productos.contains(producto)) productos.add(producto);
    }
}

package com.tienda.menu;

import com.tienda.Tienda;
import com.tienda.productos.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MenuVisual extends Menu {

    private JFrame frame;
    private JPanel panel;

    public MenuVisual(Tienda tienda) {
        super(tienda);
        inicializarGUI();
    }

    @Override
    public void mostrarMenuPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JButton botonIniciarCarrito = new JButton("Iniciar carrito de compra");
        botonIniciarCarrito.addActionListener(e -> {
            tienda.iniciarCarrito();
            mostrarMenuCarrito();
        });

        panel.add(botonListarProductos());
        panel.add(botonIniciarCarrito);
        panel.add(botonFinalizar());

        mostrarPanel(panel);
    }

    @Override
    protected void mostrarMenuCarrito() {
    }

    @Override
    protected void mostrarMenuCompra() {
    }

    @Override
    protected void mostrarMenuPago() {
    }

    @Override
    protected void mostrarMenuCliente() {

    }

    private void inicializarGUI() {
        frame = new JFrame("Tienda de Computación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    private void mostrarPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().doLayout();
        updateFrame();
    }

    private void updateFrame() {
        frame.validate();
        frame.repaint();
    }

    private JScrollPane listadoProductos() {
        String contenidoCarrito = tienda.listarProductos();
        JTextArea areaTextoCarrito = new JTextArea(contenidoCarrito);
        areaTextoCarrito.setEditable(false);
        return new JScrollPane(areaTextoCarrito);
    }

    private JButton botonListarProductos() {
        JButton boton = new JButton("Listar todos los productos");
        boton.addActionListener(this::accionListarProductos);
        return boton;
    }

    private void accionListarProductos(ActionEvent e) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(listadoProductos(), BorderLayout.CENTER);
        panel.add(botonVolver(), BorderLayout.SOUTH);

        mostrarPanel(panel);
    }

    private JButton botonVolver() {
        JButton boton = new JButton("Volver");
        boton.addActionListener(ev -> {
            mostrarMenuPrincipal();
        });
        return boton;
    }

    private JButton botonFinalizar() {
        JButton boton = new JButton("Finalizar");
        boton.addActionListener(e -> System.exit(0));
        return boton;
    }
}

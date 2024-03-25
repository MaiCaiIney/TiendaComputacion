package com.tienda.productos.electronico;

public class Teclado extends ProductoElectronico {
    private boolean mecanico;
    private boolean numerico;

    public Teclado(String nombre, float precio, String marca) {
        super(nombre, precio, marca);
    }

    public Teclado(String nombre, float precio, int stock, String marca) {
        super(nombre, precio, stock, marca);
    }

    public boolean isMecanico() {
        return mecanico;
    }

    public void setMecanico(boolean mecanico) {
        this.mecanico = mecanico;
    }

    public boolean isNumerico() {
        return numerico;
    }

    public void setNumerico(boolean numerico) {
        this.numerico = numerico;
    }
}

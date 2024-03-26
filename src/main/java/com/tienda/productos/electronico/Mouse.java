package com.tienda.productos.electronico;

public class Mouse extends ProductoElectronico {
    private boolean gamer;
    private boolean luz;

    public Mouse(String nombre, float precio, int stock, String marca) {
        super(nombre, precio, stock, marca);
        this.gamer = false;
        this.luz = false;
    }

    public boolean isGamer() {
        return gamer;
    }

    public void setGamer(boolean gamer) {
        this.gamer = gamer;
    }

    public boolean isLuz() {
        return luz;
    }

    public void setLuz(boolean luz) {
        this.luz = luz;
    }

    @Override
    public float calcularPrecio() {
        return super.getPrecio() * ((this.gamer) ? 1.21f : 1);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Gamer: %s. Con luz: %s. ", (this.gamer ? "sí" : "no"), (this.luz ? "sí" : "no"));
    }
}

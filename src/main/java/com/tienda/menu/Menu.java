package com.tienda.menu;

import com.tienda.Tienda;

public abstract class Menu {

    protected Tienda tienda;

    public Menu(Tienda tienda) {
        this.tienda = tienda;
    }

    public abstract void mostrarMenuPrincipal();
    protected abstract void mostrarMenuCarrito();
    protected abstract void mostrarMenuCompra();
    protected abstract void mostrarMenuPago();
    protected abstract void mostrarMenuCliente();
}

package com.tienda.clientes;

public class Premium extends Cliente implements Descuento{
    public Premium(String nombre, String dni) {
        super(nombre, dni);
    }

    public Premium(String nombre, String dni, String email) {
        super(nombre, dni, email);
    }

    @Override
    public float procentaje() {
        return 0.3f;
    }
}

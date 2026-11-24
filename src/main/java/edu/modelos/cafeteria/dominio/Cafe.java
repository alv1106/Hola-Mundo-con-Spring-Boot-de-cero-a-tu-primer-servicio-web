package edu.modelos.cafeteria.dominio;
// Cafe.java
// PRODUCTO CONCRETO: una bebida base. No sabe nada de extras, descuentos ni pedidos.
public class Cafe implements Bebida {

    @Override
    public String descripcion() {
        return "Cafe";
    }

    @Override
    public int precio() {
        return 5000;
    }
}
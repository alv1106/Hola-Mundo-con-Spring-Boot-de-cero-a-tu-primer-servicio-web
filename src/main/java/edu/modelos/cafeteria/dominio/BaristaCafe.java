package edu.modelos.cafeteria.dominio;
// BaristaCafe.java
// CREADOR CONCRETO: la unica diferencia con los demas baristas es QUE bebida crea.
public class BaristaCafe extends Barista {

    @Override
    protected Bebida crearBebida() {
        return new Cafe();
    }
}
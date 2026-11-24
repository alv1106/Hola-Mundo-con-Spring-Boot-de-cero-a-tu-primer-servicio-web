package edu.modelos.cafeteria.dominio;
// BaristaTe.java
// CREADOR CONCRETO: agregar una bebida nueva significa agregar una pareja de clases
// (el producto y su barista) sin modificar ninguna de las que ya existen.
public class BaristaTe extends Barista {

    @Override
    protected Bebida crearBebida() {
        return new Te();
    }
}
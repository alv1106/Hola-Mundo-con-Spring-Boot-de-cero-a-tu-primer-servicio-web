package edu.modelos.cafeteria.dominio;
// Te.java
// OTRO PRODUCTO CONCRETO: misma interfaz que Cafe, distinto comportamiento.
public class Te implements Bebida {

    @Override
    public String descripcion() {
        return "Te";
    }

    @Override
    public int precio() {
        return 4000;
    }
}
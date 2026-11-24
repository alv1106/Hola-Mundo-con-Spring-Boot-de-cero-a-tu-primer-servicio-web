package edu.modelos.cafeteria.dominio;
// ConLeche.java
// DECORATOR CONCRETO: agrega leche. Suma texto a la descripcion y plata al precio.
public class ConLeche extends ExtraBebida {

    public ConLeche(Bebida bebida) {
        super(bebida);   // guarda la bebida envuelta en la clase padre
    }

    @Override
    public String descripcion() {
        return super.descripcion() + " con leche";   // lo que ya tenia + el extra
    }

    @Override
    public int precio() {
        return super.precio() + 1000;                // lo que ya costaba + el extra
    }
}
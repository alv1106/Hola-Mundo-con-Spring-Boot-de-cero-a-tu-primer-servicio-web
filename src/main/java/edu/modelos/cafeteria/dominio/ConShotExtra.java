package edu.modelos.cafeteria.dominio;
// ConShotExtra.java
// DECORATOR CONCRETO: agrega un shot extra de espresso.
public class ConShotExtra extends ExtraBebida {

    public ConShotExtra(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + " con shot extra";
    }

    @Override
    public int precio() {
        return super.precio() + 2000;
    }
}

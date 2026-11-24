package edu.modelos.cafeteria.dominio;
// ExtraBebida.java
// DECORATOR BASE: una Bebida que "envuelve" a otra Bebida.
//
// Dos cosas a notar:
//   1) Es una Bebida (implements Bebida): por eso el resto del programa no nota la diferencia
//      entre una bebida simple y una con extras.
//   2) TIENE una Bebida (el atributo "bebida"): la que envuelve. Por defecto le delega todo.
// Los extras concretos heredan de aqui y solo cambian lo que les interesa.
public abstract class ExtraBebida implements Bebida {

    // La bebida envuelta. "protected final": los extras hijos la pueden usar, pero nadie la cambia.
    protected final Bebida bebida;

    // La bebida llega desde afuera, por el constructor (por eso la relacion es de agregacion).
    protected ExtraBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    @Override
    public String descripcion() {
        return bebida.descripcion();   // por defecto: lo mismo que la bebida envuelta
    }

    @Override
    public int precio() {
        return bebida.precio();        // por defecto: el mismo precio
    }
}
package edu.modelos.cafeteria.dominio;
// Barista.java
// CREADOR del patron Factory Method.
//
// Idea: esta clase define el FLUJO comun (preparar una bebida), pero deja que cada
// subclase decida QUE bebida concreta se crea. Quien usa un Barista nunca escribe "new Cafe()".
public abstract class Barista {

    // FACTORY METHOD: es el metodo "fabrica". Es abstracto: cada subclase lo implementa
    // y devuelve la bebida que le corresponde. (protected: solo lo usan esta clase y sus hijas.)
    protected abstract Bebida crearBebida();

    // Operacion comun a todos los baristas. Fijate que llama a crearBebida() SIN saber
    // cual bebida va a salir: eso lo decide la subclase en tiempo de ejecucion.
    public Bebida preparar() {
        Bebida bebida = crearBebida();
        System.out.println("[Barista] Prepara la base: " + bebida.descripcion());
        return bebida;
    }
}
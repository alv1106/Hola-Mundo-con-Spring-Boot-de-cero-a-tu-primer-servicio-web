package edu.modelos.cafeteria.dominio;
// Bebida.java
// Esta interfaz cumple DOS papeles a la vez:
//   - Es el PRODUCTO del Factory Method (lo que las fabricas crean).
//   - Es el COMPONENTE del Decorator (lo que los extras envuelven).
// Todo lo que se pueda servir en la cafeteria sabe responder estas dos preguntas.
public interface Bebida {

    // Texto que describe la bebida, por ejemplo "Cafe con leche".
    String descripcion();

    // Precio en pesos colombianos. Usamos int (pesos enteros) para evitar errores de redondeo.
    int precio();
}
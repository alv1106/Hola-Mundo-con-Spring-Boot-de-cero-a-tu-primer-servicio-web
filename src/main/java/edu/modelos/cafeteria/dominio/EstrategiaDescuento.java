package edu.modelos.cafeteria.dominio;
// EstrategiaDescuento.java
// STRATEGY: la interfaz que representa "una forma de calcular el descuento".
// Cada politica de descuento es una implementacion distinta e intercambiable.
public interface EstrategiaDescuento {

    // Recibe el subtotal y devuelve lo que realmente se debe pagar.
    int aplicar(int subtotal);
}
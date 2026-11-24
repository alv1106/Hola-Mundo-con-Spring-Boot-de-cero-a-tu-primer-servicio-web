package edu.modelos.cafeteria.dominio;
// SinDescuento.java
// ESTRATEGIA CONCRETA: no aplica ningun descuento (el total es igual al subtotal).
public class SinDescuento implements EstrategiaDescuento {

    @Override
    public int aplicar(int subtotal) {
        return subtotal;
    }
}
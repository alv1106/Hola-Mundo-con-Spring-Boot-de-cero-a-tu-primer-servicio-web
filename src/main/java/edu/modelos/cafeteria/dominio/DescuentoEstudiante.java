package edu.modelos.cafeteria.dominio;
// DescuentoEstudiante.java
// ESTRATEGIA CONCRETA: 10% de descuento.
public class DescuentoEstudiante implements EstrategiaDescuento {

    @Override
    public int aplicar(int subtotal) {
        // Se paga el 90%. Se multiplica primero y se divide al final (enteros, sin decimales).
        return subtotal * 90 / 100;
    }
}
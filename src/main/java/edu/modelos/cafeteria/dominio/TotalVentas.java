package edu.modelos.cafeteria.dominio;

// TotalVentas.java
// OTRO OBSERVADOR CONCRETO: va acumulando cuantos pedidos se han confirmado y cuanto se vendio.
// Observa que la Cafeteria no sabe que este objeto existe: solo conoce la interfaz ObservadorPedido.
public class TotalVentas implements ObservadorPedido {

    private int pedidos = 0;
    private int total = 0;

    @Override
    public void alConfirmar(Pedido pedido) {
        pedidos++;
        total += pedido.total();
    }

    public int getPedidos() {
        return pedidos;
    }

    public int getTotal() {
        return total;
    }
}
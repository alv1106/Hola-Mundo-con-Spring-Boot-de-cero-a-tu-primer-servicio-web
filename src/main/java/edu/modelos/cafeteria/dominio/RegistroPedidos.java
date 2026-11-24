package edu.modelos.cafeteria.dominio;
// RegistroPedidos.java
// OBSERVADOR CONCRETO: deja constancia en consola de cada pedido confirmado.
public class RegistroPedidos implements ObservadorPedido {

    @Override
    public void alConfirmar(Pedido pedido) {
        System.out.println("[Registro] Pedido #" + pedido.numero() + ": " + pedido.descripcion()
                + " | subtotal $" + pedido.subtotal() + " | total $" + pedido.total());
    }
}
package edu.modelos.cafeteria.dominio;
// ObservadorPedido.java
// OBSERVER: la interfaz de quien quiere enterarse cuando se confirma un pedido.
// La cafeteria llamara a este metodo en todos los observadores suscritos.
public interface ObservadorPedido {

    void alConfirmar(Pedido pedido);
}
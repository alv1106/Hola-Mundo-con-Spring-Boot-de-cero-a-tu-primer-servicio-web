package edu.modelos.cafeteria.web;

import edu.modelos.cafeteria.dominio.Pedido;

// LO QUE SALE: el JSON que se devuelve al cliente. DTO = "Data Transfer Object"
// (objeto de transferencia de datos): una clase hecha SOLO para viajar por la red.
//
// Es casi identica al Pedido del dominio, y aun asi la separamos a proposito: el Pedido es
// una decision interna nuestra; el DTO es un CONTRATO PUBLICO con los clientes de la API.
// Si manana cambia el Pedido, el DTO puede seguir igual y nadie se entera.
public record PedidoDto(int numero, String descripcion, int subtotal, int total) {

    // Convierte el objeto del dominio en el objeto que viaja por la red.
    public static PedidoDto desde(Pedido pedido) {
        return new PedidoDto(pedido.numero(), pedido.descripcion(), pedido.subtotal(), pedido.total());
    }
}
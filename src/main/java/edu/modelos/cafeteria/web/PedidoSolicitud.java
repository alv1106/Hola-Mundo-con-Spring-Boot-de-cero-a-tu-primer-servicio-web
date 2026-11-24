package edu.modelos.cafeteria.web;

import java.util.List;

// LO QUE LLEGA: el cuerpo JSON de la peticion POST /api/pedidos. Por ejemplo:
//     {"bebida": "cafe", "extras": ["leche", "shot"]}
// Spring (con su libreria de JSON) convierte ese texto en un objeto de esta clase
// automaticamente. A ese proceso se le llama DESERIALIZACION.
// Los nombres de los campos del record deben coincidir con las claves del JSON.
public record PedidoSolicitud(String bebida, List<String> extras) {

    // Constructor compacto: se ejecuta siempre al crear el record.
    // Si el cliente no envia la clave "extras", llegaria null; aqui lo convertimos en una
    // lista vacia para que el dominio nunca tenga que preocuparse por ese caso.
    public PedidoSolicitud {
        if (extras == null) {
            extras = List.of();
        }
    }
}
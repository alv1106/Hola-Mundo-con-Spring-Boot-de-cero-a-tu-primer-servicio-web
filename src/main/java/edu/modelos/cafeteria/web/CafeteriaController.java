package edu.modelos.cafeteria.web;

import edu.modelos.cafeteria.dominio.Cafeteria;
import edu.modelos.cafeteria.dominio.Pedido;
import edu.modelos.cafeteria.dominio.TotalVentas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// EL CONTROLADOR: la "puerta de entrada" web. Su unico trabajo es TRADUCIR:
//     HTTP (peticion JSON)  ->  llamada Java al dominio  ->  HTTP (respuesta JSON)
// No contiene ninguna regla de negocio: eso ya esta en el dominio (los patrones).
//
// @RestController: "esta clase atiende peticiones web, y lo que devuelven sus metodos se
// convierte en el cuerpo de la respuesta (en JSON si es un objeto)".
@RestController
public class CafeteriaController {

    private final Cafeteria cafeteria;
    private final TotalVentas ventas;

    // El constructor pide los dos objetos que necesita. SPRING SE LOS ENTREGA: son los beans
    // creados en CafeteriaConfig. (No escribimos "new" en ningun lado: eso es inyeccion de
    // dependencias. Como hay un solo constructor, no hace falta ninguna anotacion mas.)
    public CafeteriaController(Cafeteria cafeteria, TotalVentas ventas) {
        this.cafeteria = cafeteria;
        this.ventas = ventas;
    }

    // @PostMapping("/api/pedidos"): "este metodo atiende las peticiones POST a /api/pedidos".
    // POST se usa para CREAR algo, y aqui creamos (confirmamos) un pedido.
    //
    // @RequestBody: "toma el cuerpo JSON de la peticion y conviertelo en un PedidoSolicitud".
    //
    // ResponseEntity: representa la respuesta completa (codigo de estado + cuerpo). Lo usamos
    // porque queremos responder 201 (Created) en lugar del 200 por defecto.
    @PostMapping("/api/pedidos")
    public ResponseEntity<PedidoDto> crearPedido(@RequestBody PedidoSolicitud solicitud) {
        // Esta es LA UNICA linea que usa el dominio: la misma llamada que hacia App.java.
        // Si el pedido es invalido, confirmar() lanza IllegalArgumentException y la atiende
        // ManejadorErrores (ver esa clase).
        Pedido pedido = cafeteria.confirmar(solicitud.bebida(), solicitud.extras());
        return ResponseEntity.status(HttpStatus.CREATED).body(PedidoDto.desde(pedido));
    }

    // @GetMapping("/api/ventas"): "este metodo atiende las peticiones GET a /api/ventas".
    // GET se usa para LEER. Devuelve lo que acumulo el Observer TotalVentas.
    // Como devolvemos un objeto (no un texto), Spring lo convierte en JSON automaticamente.
    @GetMapping("/api/ventas")
    public VentasDto resumenVentas() {
        return new VentasDto(ventas.getPedidos(), ventas.getTotal());
    }
}
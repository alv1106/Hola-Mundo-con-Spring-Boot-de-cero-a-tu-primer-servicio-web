package edu.modelos.cafeteria.web;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// EL TRADUCTOR DE ERRORES. En la version de consola, App.java atrapaba la excepcion con
// try/catch y mostraba el mensaje con System.out.println. En la web no hay consola: el cliente
// espera un CODIGO DE ESTADO y un JSON. Esta clase hace esa traduccion en UN solo lugar.
//
// @RestControllerAdvice: "esta clase vigila a TODOS los controladores. Si alguno deja escapar
// una excepcion, mira aqui si hay un metodo que sepa responderla".
@RestControllerAdvice
public class ManejadorErrores {

    // @ExceptionHandler(IllegalArgumentException.class): "este metodo atiende esa excepcion".
    // El dominio lanza IllegalArgumentException("Bebida no existe: pizza") cuando el cliente pide
    // algo que no existe; eso es culpa del CLIENTE, asi que respondemos 400 (Bad Request).
    // El dominio no sabe nada de codigos HTTP: solo lanza la excepcion.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> peticionInvalida(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
    }
}
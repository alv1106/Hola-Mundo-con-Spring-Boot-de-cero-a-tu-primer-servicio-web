package edu.modelos.cafeteria.dominio;
// Pedido.java
// Resultado de confirmar un pedido. Es un "record": una clase de datos inmutable.
// Java genera automaticamente el constructor, los metodos de acceso (numero(), total()...)
// y el toString. Es lo mismo que escribir una clase con atributos final y sus getters.
public record Pedido(int numero, String descripcion, int subtotal, int total) { }
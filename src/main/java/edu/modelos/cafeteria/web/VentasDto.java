package edu.modelos.cafeteria.web;

// JSON de respuesta para GET /api/ventas, por ejemplo: {"pedidos":3,"total":15300}
public record VentasDto(int pedidos, int total) { }
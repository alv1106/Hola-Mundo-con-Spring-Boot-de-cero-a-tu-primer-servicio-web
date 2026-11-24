package edu.modelos.cafeteria.config;

import edu.modelos.cafeteria.dominio.Cafeteria;
import edu.modelos.cafeteria.dominio.DescuentoEstudiante;
import edu.modelos.cafeteria.dominio.EstrategiaDescuento;
import edu.modelos.cafeteria.dominio.RegistroPedidos;
import edu.modelos.cafeteria.dominio.SinDescuento;
import edu.modelos.cafeteria.dominio.TotalVentas;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ESTA CLASE ES EL NUEVO "ENSAMBLAJE".
// En la version de consola, el main de App.java hacia esto:
//     Cafeteria cafeteria = new Cafeteria(new DescuentoEstudiante());
//     TotalVentas ventas = new TotalVentas();
//     cafeteria.suscribir(new RegistroPedidos());
//     cafeteria.suscribir(ventas);
// Ahora lo hace esta clase, con la misma logica, pero Spring ejecuta estos metodos por nosotros.
//
// @Configuration: marca la clase como "libro de recetas" de Spring. Spring la lee al arrancar.
@Configuration
public class CafeteriaConfig {

    // @Bean: "este metodo es una receta. Ejecutalo UNA vez, guarda el objeto que devuelve
    // y entregaselo a quien lo pida". A cada objeto que Spring guarda asi se le llama BEAN.
    //
    // @Value("${cafeteria.descuento:ninguno}"): lee la propiedad "cafeteria.descuento" del archivo
    // application.properties. Lo que va despues de ":" es el valor por defecto si la propiedad
    // no existe. Asi se puede cambiar de estrategia editando un archivo de texto, sin recompilar.
    @Bean
    public EstrategiaDescuento estrategiaDescuento(@Value("${cafeteria.descuento:ninguno}") String politica) {
        // Este es el mismo "elegir la estrategia" que antes estaba escrito a mano en App.java.
        if (politica.equals("estudiante")) {
            return new DescuentoEstudiante();
        }
        return new SinDescuento();
    }

    // Los observadores tambien son beans, para que PUEDAN COMPARTIRSE: el mismo TotalVentas
    // que se suscribe a la cafeteria sera el que lea el controlador web mas adelante.
    @Bean
    public RegistroPedidos registroPedidos() {
        return new RegistroPedidos();
    }

    @Bean
    public TotalVentas totalVentas() {
        return new TotalVentas();
    }

    // Los PARAMETROS de un metodo @Bean los entrega Spring: ve que este metodo necesita una
    // EstrategiaDescuento, un RegistroPedidos y un TotalVentas, busca los beans de esos tipos
    // (los de arriba) y los pasa. A esto se le llama INYECCION DE DEPENDENCIAS.
    @Bean
    public Cafeteria cafeteria(EstrategiaDescuento descuento, RegistroPedidos registro, TotalVentas ventas) {
        Cafeteria cafeteria = new Cafeteria(descuento);   // Strategy
        cafeteria.suscribir(registro);                    // Observer 1
        cafeteria.suscribir(ventas);                      // Observer 2
        return cafeteria;
    }
}
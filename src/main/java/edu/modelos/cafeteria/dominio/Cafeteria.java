package edu.modelos.cafeteria.dominio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Cafeteria.java (version final del paso 5: agrega el Observer)
//
// Esta clase es el "punto de entrada" de la logica: recibe lo que el cliente pide
// (una bebida y una lista de extras) y devuelve un Pedido. Ella conecta las piezas,
// pero no contiene ningun if por bebida, por extra ni por tipo de descuento.
public class Cafeteria {

    // Registro de baristas: nombre de la bebida -> el barista que sabe crearla.
    private final Map<String, Barista> baristas = new HashMap<>();

    // Registro de extras: nombre del extra -> una "funcion" que recibe una Bebida y devuelve
    // la misma Bebida envuelta con el extra. (Function<Bebida, Bebida> es eso: una funcion
    // que entra con una Bebida y sale con otra. "ConLeche::new" significa "usa el
    // constructor de ConLeche".)
    private final Map<String, Function<Bebida, Bebida>> extras = new HashMap<>();

    // La estrategia de descuento llega desde afuera (por eso es agregacion, no composicion).
    private final EstrategiaDescuento descuento;

    // OBSERVER: la lista de interesados en enterarse de cada pedido confirmado.
    // La cafeteria solo conoce la interfaz ObservadorPedido, nunca las clases concretas.
    private final List<ObservadorPedido> observadores = new ArrayList<>();

    private int ultimoNumero = 0;

    public Cafeteria(EstrategiaDescuento descuento) {
        this.descuento = descuento;

        // Menu de la cafeteria. Para agregar una bebida o un extra nuevo, se agrega UNA linea aqui
        // (y la clase nueva correspondiente); el resto del metodo confirmar() no cambia.
        baristas.put("cafe", new BaristaCafe());
        baristas.put("te", new BaristaTe());
        extras.put("leche", ConLeche::new);
        extras.put("shot", ConShotExtra::new);
    }

    // Permite que alguien se "suscriba" para recibir avisos de cada pedido confirmado.
    public void suscribir(ObservadorPedido observador) {
        observadores.add(observador);
    }

    public Pedido confirmar(String tipo, List<String> pedidoExtras) {

        // 1) Validar y buscar el barista (Factory Method)
        if (tipo == null) {
            throw new IllegalArgumentException("Debe indicar la bebida");
        }
        Barista barista = baristas.get(tipo.toLowerCase());
        if (barista == null) {
            throw new IllegalArgumentException("Bebida no existe: " + tipo);
        }

        // 2) Validar TODOS los extras antes de preparar nada
        List<Function<Bebida, Bebida>> decoradores = new ArrayList<>();
        for (String nombre : pedidoExtras) {
            Function<Bebida, Bebida> decorador = extras.get(nombre.toLowerCase());
            if (decorador == null) {
                throw new IllegalArgumentException("Extra no existe: " + nombre);
            }
            decoradores.add(decorador);
        }

        // 3) Preparar la bebida base y envolverla con cada extra (Decorator)
        Bebida bebida = barista.preparar();
        for (Function<Bebida, Bebida> decorador : decoradores) {
            bebida = decorador.apply(bebida);
        }

        // 4) Calcular el total con la politica de descuento (Strategy)
        int subtotal = bebida.precio();
        int total = descuento.aplicar(subtotal);

        // 5) Armar el pedido con el siguiente numero
        ultimoNumero++;
        Pedido pedido = new Pedido(ultimoNumero, bebida.descripcion(), subtotal, total);

        // 6) Avisar a todos los observadores suscritos (Observer)
        for (ObservadorPedido observador : observadores) {
            observador.alConfirmar(pedido);
        }

        return pedido;
    }
}
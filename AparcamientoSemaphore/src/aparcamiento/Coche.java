package aparcamiento;

import java.util.Random;

/**
 * Clase que representa un coche que intenta aparcar.
 * Cada coche funciona como un hilo independiente que ejecuta el método run().
 */
public class Coche implements Runnable {
    // Nombre del coche (por ejemplo, “Coche-1”)
    private final String nombre;
    // Referencia al mismo objeto Aparcamiento compartido por todos los coches
    private final Aparcamiento aparcamiento;
    // Objeto Random para generar tiempos aleatorios de estancia
    private final Random random = new Random();

    /**
     * Constructor del coche.
     * @param nombre nombre del coche (identificador)
     * @param aparcamiento referencia al objeto Aparcamiento compartido
     */
    public Coche(String nombre, Aparcamiento aparcamiento) {
        this.nombre = nombre;
        this.aparcamiento = aparcamiento;
    }

    /**
     * Método principal del hilo.
     * Define el comportamiento completo del coche:
     * 1. Intenta entrar.
     * 2. Permanece dentro un tiempo aleatorio (1–4 segundos).
     * 3. Sale del aparcamiento y libera su plaza.
     */
    @Override
    public void run() {
        // El coche intenta entrar al aparcamiento
        aparcamiento.entrar(nombre);
        try {
            // Genera un tiempo de espera aleatorio entre 1 y 4 segundos
            int tiempo = 1000 + random.nextInt(3000);
            Thread.sleep(tiempo); // Simula el tiempo que el coche está aparcado
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Una vez pasado el tiempo, el coche sale y libera su plaza
        aparcamiento.salir(nombre);
    }
}

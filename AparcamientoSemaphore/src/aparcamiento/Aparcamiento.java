package aparcamiento;

import java.util.concurrent.Semaphore;

/**
 * Clase que gestiona el acceso concurrente a las plazas del aparcamiento.
 * Usa un objeto Semaphore para limitar cuántos coches pueden estar dentro a la vez.
 */
public class Aparcamiento {
    // Objeto semáforo: controla los permisos de entrada según la capacidad
    private final Semaphore semaforo;
    // Contador de cuántas plazas están ocupadas en este momento
    private int plazasOcupadas = 0;
    // Número máximo de plazas que tiene el aparcamiento
    private final int capacidad;

    /**
     * Constructor del aparcamiento.
     * Inicializa el semáforo con la cantidad máxima de plazas disponibles.
     * @param capacidad número de plazas del aparcamiento (por ejemplo, 3)
     */
    public Aparcamiento(int capacidad) {
        this.capacidad = capacidad;
        // El segundo parámetro “true” da prioridad justa (FIFO) a los hilos en espera
        this.semaforo = new Semaphore(capacidad, true);
    }

    /**
     * Método que gestiona la entrada de un coche.
     * Si no hay plazas libres, el hilo quedará bloqueado hasta que otra se libere.
     * @param nombreCoche nombre identificativo del coche (ej. “Coche-1”)
     */
    public void entrar(String nombreCoche) {
        try {
            // Mensaje informativo: el coche intenta entrar
            System.out.println(nombreCoche + " está intentando entrar...");

            // Intenta adquirir un permiso (si no hay, el hilo espera)
            semaforo.acquire();

            // Bloque sincronizado: actualiza el contador de plazas ocupadas
            synchronized (this) {
                plazasOcupadas++;
                System.out.println(nombreCoche + " ha entrado. Plazas ocupadas: " + plazasOcupadas);
            }
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido mientras espera, se notifica y termina
            Thread.currentThread().interrupt();
            System.out.println(nombreCoche + " fue interrumpido.");
        }
    }

    /**
     * Método que gestiona la salida del coche y libera una plaza.
     * @param nombreCoche nombre del coche que abandona el aparcamiento
     */
    public void salir(String nombreCoche) {
        // Bloque sincronizado: resta una plaza ocupada
        synchronized (this) {
            plazasOcupadas--;
            System.out.println(nombreCoche + " ha salido. Plazas ocupadas: " + plazasOcupadas);
        }

        // Libera un permiso del semáforo para que otro coche pueda entrar
        semaforo.release();
    }
}

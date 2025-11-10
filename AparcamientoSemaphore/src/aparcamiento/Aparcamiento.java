package aparcamiento;

import java.util.concurrent.Semaphore;

/**
 * Clase que gestiona el acceso concurrente a las plazas del aparcamiento.
 * Controla cuántos coches pueden entrar simultáneamente mediante un Semaphore.
 */
public class Aparcamiento {
    private final Semaphore semaforo;
    private int plazasOcupadas = 0;
    private final int capacidad;

    public Aparcamiento(int capacidad) {
        this.capacidad = capacidad;
        this.semaforo = new Semaphore(capacidad, true);
    }

    public void entrar(String nombreCoche) {
        try {
            System.out.println(nombreCoche + " está intentando entrar...");
            semaforo.acquire();
            synchronized (this) {
                plazasOcupadas++;
                System.out.println(nombreCoche + " ha entrado. Plazas ocupadas: " + plazasOcupadas);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(nombreCoche + " fue interrumpido.");
        }
    }

    public void salir(String nombreCoche) {
        synchronized (this) {
            plazasOcupadas--;
            System.out.println(nombreCoche + " ha salido. Plazas ocupadas: " + plazasOcupadas);
        }
        semaforo.release();
    }
}

package aparcamiento;

import java.util.Random;

/**
 * Clase que representa un coche (hilo) que intenta entrar al aparcamiento.
 */
public class Coche implements Runnable {
    private final String nombre;
    private final Aparcamiento aparcamiento;
    private final Random random = new Random();

    public Coche(String nombre, Aparcamiento aparcamiento) {
        this.nombre = nombre;
        this.aparcamiento = aparcamiento;
    }

    @Override
    public void run() {
        aparcamiento.entrar(nombre);
        try {
            int tiempo = 1000 + random.nextInt(3000); // entre 1 y 4 segundos
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        aparcamiento.salir(nombre);
    }
}

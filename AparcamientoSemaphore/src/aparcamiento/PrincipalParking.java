package aparcamiento;

/**
 * Clase principal que lanza la simulación del aparcamiento concurrente.
 */
public class PrincipalParking {
    public static void main(String[] args) {
        Aparcamiento aparcamiento = new Aparcamiento(3);

        for (int i = 1; i <= 7; i++) {
            Coche coche = new Coche("Coche-" + i, aparcamiento);
            Thread hilo = new Thread(coche);
            hilo.start();
        }
    }
}

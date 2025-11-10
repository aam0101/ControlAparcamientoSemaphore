package aparcamiento;

/**
 * Clase principal que lanza la simulación del aparcamiento.
 * Crea un aparcamiento con 3 plazas y lanza 7 hilos (coches) que compiten por entrar.
 */
public class PrincipalParking {
    public static void main(String[] args) {
        // Se crea el aparcamiento con capacidad de 3 plazas
        Aparcamiento aparcamiento = new Aparcamiento(3);

        // Bucle que genera 7 coches numerados del 1 al 7
        for (int i = 1; i <= 7; i++) {
            // Se crea un coche con su nombre y la referencia al aparcamiento
            Coche coche = new Coche("Coche-" + i, aparcamiento);
            // Se crea un hilo para ese coche
            Thread hilo = new Thread(coche);
            // Se inicia el hilo (el coche empieza a “moverse”)
            hilo.start();
        }

        // El programa termina cuando todos los coches han pasado por el aparcamiento
    }
}

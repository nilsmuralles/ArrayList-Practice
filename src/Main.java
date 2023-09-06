/*
 * @author: Nils Muralles Morales
 * @version: 1.0
 * @since: 03/09/2023
 * Clase Main que contiene el Driver Program
 */

// Importar las clases necesarias
import java.util.Scanner;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Universidad miUniversidad = new Universidad("UVG");

        // Ciclo principal
        boolean seguir = true;
        while (seguir) {
            System.out.println("---BIENVENIDO---");
            System.out.println("1. Nueva sede");
            System.out.println("2. Administrar sede");
            System.out.println("3. Resumen estadístico");
            System.out.println("4. Salir");
            System.out.print("¿Qué desea hacer? ");
            String opcion = input.nextLine();

            try { // Válidar la opción del usuario
                int op = Integer.parseInt(opcion);

                switch (op) {
                    case 1:
                        miUniversidad.nuevaSede();
                        break;

                    case 2:
                        miUniversidad.administrarSede();
                        break;

                    case 3:
                        miUniversidad.mostrarEstadisticas();
                        break;

                    case 4:
                        seguir = false;
                        break;

                    default:
                        System.out.println("Seleccione una opción válida");
                        ;
                }

            } catch (Exception illegalStateException) {
                System.out.println("La opción seleccionada debe ser un número");
            }

        }

        input.close();
    }
}

/*
 * @author: Nils Muralles Morales
 * @version: 1.0
 * @since: 03/09/2023
 * Clase Universidad que contiene la lógica del programa
 */

// Importar las clases necesarias
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Universidad {

    // Atributos y objetos
    String nombre;
    ArrayList<Sede> sedes = new ArrayList<Sede>();
    Scanner input = new Scanner(System.in);
    ArrayList<String> asignaturas = new ArrayList<String>(
            Arrays.asList("Matematica", "Lenguaje", "Quimica", "Fisica", "Comprension Lectora", "Estadistica"));

    public Universidad(String nombre) { // Constructor de Universidad
        this.nombre = nombre;
        crearSedePrincipal();
    }

    /**
     * @return String
     */
    // Setters y getters
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return ArrayList<Sede>
     */
    public ArrayList<Sede> getSedes() {
        return sedes;
    }

    /**
     * @param sedes
     */
    public void setSedes(ArrayList<Sede> sedes) {
        this.sedes = sedes;
    }

    public void nuevaSede() {

        // Ciclo que asegura que se cree la sede
        boolean sedeCreada = false;

        do {
            // Menú para crear una nueva sede
            System.out.println("");
            System.out.println("---NUEVA SEDE---");
            System.out.print("Ingrese el código único (id) de la sede: ");
            String idSede = input.nextLine();

            System.out.print("Ingrese el nombre de la sede: ");
            String nombreSede = input.nextLine();

            try { // Validar el ingreso del id de la sede
                int id = Integer.parseInt(idSede);

                // Crear la nueva sede
                Sede nuevaSede = new Sede(id, nombreSede);
                sedes.add(nuevaSede);
                System.out.println("Sede creada exitosamente");
                sedeCreada = true;

            } catch (Exception e) {
                System.out.println("El id de la sede debe ser un número");
            }

        } while (!sedeCreada);

    }

    public void administrarSede() {

        // Menú para administrar las sedes
        System.out.println("");
        System.out.println("---ADMINISTRAR SEDE---");

        System.out.println("Sedes actuales");
        System.out.println("ID - Nombre");

        for (Sede sede : sedes) { // Mostrar los datos de las sedes
            System.out.println(sede.toString());
        }

        System.out.println("");
        System.out.print("Ingrese el id de la sede que desea modificar: ");
        String idString = input.nextLine();

        try { // Validar que el id ingresado sea un número
            int id = Integer.parseInt(idString);
            boolean esUnaSede = false;

            for (Sede sede : sedes) { // Ciclo que valida que el ID ingresado esté en la lista
                if (sede.getIdSede() == id) {
                    sede.administrarSede();
                    esUnaSede = true;
                    break;
                }
            }

            if (!esUnaSede) { // Validar que se encuentre la sede
                System.out.println("No se encontró el ID de la sede");
            }

        } catch (Exception e) {
            System.out.println("Ingreso inválido. Ingrese el ID de una sede");
        }

    }

    public void mostrarEstadisticas() {

        // Menú para el resumen estadístico
        System.out.println("");
        System.out.println("---RESUMEN ESTADISTICO---");
        System.out.print("Ingrese la materia que desea consultar: ");
        String materia = input.nextLine();
        System.out.println("");

        // Mostrar el resumen de la universidad por materia
        if (asignaturas.contains(materia)) {
            for (Sede sede : sedes) {
                if (!sede.getCalificacionesSede().isEmpty()) {
                    System.out.println("---SEDE " + sede.getNombreSede() + "---");
                    System.out.println("Promedio: " + sede.promedio(materia));
                    System.out.println("Moda: " + sede.moda(materia));
                    System.out.println("Mediana: " + sede.mediana(materia));
                    System.out.println("Desviación estándar: " + sede.desvEsta(materia));
                    System.out.println("");
                }
            }

        } else {
            System.out.println("La materia no es una asignatura impartida por la Universidad");
        }
    }

    public void crearSedePrincipal() {

        // Crear la sede central
        Sede sedePrincipal = new Sede(1, "Central");
        sedes.add(0, sedePrincipal);
    }

}

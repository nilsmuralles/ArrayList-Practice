/*
 * @author: Nils Muralles Morales
 * @version: 1.0
 * @since: 03/09/2023
 * Clase Sede que es otro controlador de otras funciones
 */

// Importar las clases necesarias
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Date;

public class Sede {

    // Atributos y objetos
    int idSede;
    ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
    String nombreSede;
    Scanner input = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList<String> asignaturas = new ArrayList<String>(
            Arrays.asList("Matematica", "Lenguaje", "Quimica", "Fisica", "Comprension Lectora", "Estadistica"));
    ArrayList<Examen> calificacionesSede = new ArrayList<Examen>();

    public Sede(int idSede, String nombreSede) { // Constructor de sede
        this.idSede = idSede;
        this.nombreSede = nombreSede;
    }

    /**
     * @return int
     */
    // Setters y getters
    public int getIdSede() {
        return idSede;
    }

    /**
     * @param idSede
     */
    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    /**
     * @return ArrayList<Alumno>
     */
    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    /**
     * @param alumnos
     */
    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    /**
     * @return String
     */
    public String getNombreSede() {
        return nombreSede;
    }

    /**
     * @param nombreSede
     */
    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    /**
     * @return ArrayList<Examen>
     */
    public ArrayList<Examen> getCalificacionesSede() {
        return calificacionesSede;
    }

    public void nuevoAlmuno() {
        // Ciclo de un nuevo alumno
        boolean seguir = true;
        while (seguir) {

            // Menú para un nuevo alumno
            System.out.println("");
            System.out.println("---NUEVO ALUMNO---");
            System.out.print("Ingrese el nombre del alumno: ");
            String nombre = input.nextLine();

            System.out.print("Ingrese el apellido del alumno: ");
            String apellido = input.nextLine();

            System.out.print("Ingrese el código único (ID) del estudiante: ");
            String idAlumnoString = input.nextLine();

            System.out.print("Introduzca la fecha de nacimiento del estudiante (dd/MM/yyyy): ");
            String fechaNacimientoAlumno = input.nextLine();

            System.out.print("Introduzca el correo electrónico del alumno: ");
            String correo = input.nextLine();

            try { // Validar los datos del alumno
                int idAlumno = Integer.parseInt(idAlumnoString);
                Date fechaN = dateFormat.parse(fechaNacimientoAlumno);

                // Crear nuevo alumno
                Alumno nuevoAlumno = new Alumno(nombre, apellido, idAlumno, fechaN, correo);
                alumnos.add(nuevoAlumno);
                System.out.println("Alumno creado exitosamente");
                seguir = false;

            } catch (Exception NumberFormatException) {
                System.out.println("Datos inválidos de alumno");
            }

        }
    }

    public void asignarNotas() {
        // Ciclo de asignación de notas
        ArrayList<Examen> notas = new ArrayList<Examen>();
        boolean seguir = true;
        while (seguir) {

            // Menú para asignación de notas
            System.out.println("");
            System.out.println("---ASIGNAR NOTAS A ALUMNO---");
            System.out.print("ID del alumno: ");
            String idString = input.nextLine();

            try {
                int id = Integer.parseInt(idString);

                // Validar que el alumno este en la lista de alumnos
                boolean encontrado = false;
                boolean alumnoValidado = false;

                if (!encontrado) {
                    for (Alumno alumno : alumnos) {
                        if (alumno.getId() == id) {
                            encontrado = true;
                            alumnoValidado = true;
                        }
                    }
                }

                if (alumnoValidado) { // Si el alumno si se encuentra

                    for (String asignatura : asignaturas) { // Ciclo que recibe las notas del alumno
                        boolean notaValidada = false;

                        do { // Recibir notas
                            System.out.print("Nota de " + asignatura + ": ");
                            String notaString = input.nextLine();

                            try { // Validar la nota
                                double nota = Double.parseDouble(notaString);

                                Examen examenActual = new Examen(asignatura, nota, id);
                                notas.add(examenActual);

                                boolean idEncontrada = false;
                                if (!idEncontrada) { // Asignar las notas al alumno
                                    for (Alumno alumno : alumnos) {
                                        if (alumno.getId() == id) {
                                            alumno.setResultados(notas);
                                            calificacionesSede.add(examenActual);
                                            idEncontrada = true;
                                        }
                                    }
                                }

                                notaValidada = true;

                            } catch (Exception e) {
                                System.out.println("Ingreso inválido");
                            }
                        } while (!notaValidada);

                    }
                    seguir = false;

                } else if (!alumnoValidado) { // Si no se encuentra
                    System.out.println("El alumno indicado no se encuentra entre los estudiantes");
                }

            } catch (Exception e) {
                System.out.println("Los datos ingresados no son válidos");
            }
        }

    }

    public void mostrarAlumnos() {
        System.out.println("");
        System.out.println("ID  Nombre  Correo"); // Encabezado de la lista de alumnos

        for (Alumno alumno : alumnos) { // Ciclo que muestra a los alumnos
            System.out.println("");
            System.out.println(alumno.toString());
            for (Examen examen : alumno.getResultados()) {
                System.out.print(" " + examen.getMateria() + ": " + examen.getNota());
            }
            System.out.println("");
        }

    }

    /**
     * @param materia
     * @return double
     */
    public double promedio(String materia) {
        double total = 0.0;
        ArrayList<Double> notas = new ArrayList<Double>(); // Arreglo con notas

        for (Examen examen : calificacionesSede) { // Llenar el arreglo
            if (examen.getMateria().equals(materia)) {
                notas.add(examen.getNota());
            }
        }

        if (notas.isEmpty()) { // Verificar que no esté vacío
            return 0.0;

        } else { // Si no está vacío
            for (Double nota : notas) {
                total += nota;
            }

            return total / (double) notas.size();
        }
    }

    /**
     * @param materia
     * @return double
     */
    public double moda(String materia) {
        int maxReps = 0;
        double moda = 0;
        ArrayList<Double> notas = new ArrayList<Double>(); // Arreglo de notas

        for (Examen examen : calificacionesSede) { // Llenar el arreglo de notas
            if (examen.getMateria().equals(materia)) {
                notas.add(examen.getNota());
            }
        }

        if (notas.isEmpty()) { // Verificar que no esté vacío
            return 0.0;

        } else { // Si no está vacío
            for (int i = 0; i < notas.size(); i++) {
                int numReps = 0;
                for (int j = 0; j < notas.size(); j++) {
                    if (notas.get(i) == notas.get(j)) {
                        numReps++;
                    }
                    if (numReps > maxReps) {
                        moda = notas.get(i);
                        maxReps = numReps;
                    }
                }
            }
            return moda;
        }
    }

    /**
     * @param materia
     * @return Double
     */
    public Double mediana(String materia) {
        ArrayList<Double> notas = new ArrayList<Double>(); // Arreglo de notas

        for (Examen examen : calificacionesSede) { // Llenar el arreglo de notas
            if (examen.getMateria().equals(materia)) {
                notas.add(examen.getNota());
            }
        }

        if (notas.isEmpty()) { // Verificar que no esté vacío
            return 0.0;

        } else { // Si no está vacío
            Collections.sort(notas);
            if (notas.size() % 2 == 0) {
                return (double) (notas.get(notas.size() / 2 - 1) + notas.get(notas.size() / 2)) / 2;
            } else {
                return (double) notas.get(notas.size() / 2);
            }
        }
    }

    /**
     * @param materia
     * @return double
     */
    public double desvEsta(String materia) {
        double desvEsta = 0;
        ArrayList<Double> notas = new ArrayList<Double>(); // Listado de notas

        for (Examen examen : calificacionesSede) { // Llenar el arreglo de notas
            if (examen.getMateria().equals(materia)) {
                notas.add(examen.getNota());
            }
        }

        for (Double nota : notas) {
            desvEsta += Math.pow(nota - promedio(materia), 2);
        }
        return Math.sqrt(desvEsta / (notas.size() - 1));

    }

    public void administrarSede() {

        // Ciclo de una sede
        boolean seguir = true;
        while (seguir) {

            // Menú principal de una sede
            System.out.println("");
            System.out.println("---SEDE " + nombreSede.toUpperCase() + "---");
            System.out.println("1. Nuevo alumno");
            System.out.println("2. Asignar notas a alumno");
            System.out.println("3. Listado de alumnos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = input.nextLine();

            try { // Validar la opción del usuario
                int op = Integer.parseInt(opcion);

                switch (op) {
                    case 1:
                        nuevoAlmuno();
                        break;

                    case 2:
                        asignarNotas();
                        break;

                    case 3:
                        mostrarAlumnos();
                        break;

                    case 4:
                        seguir = false;
                        break;

                    default:
                        break;
                }

            } catch (Exception e) {
                System.out.println("Ingreso inválido. Introduzca un número");
            }
        }
    }

    @Override
    public String toString() {
        return idSede + " - " + nombreSede;
    }

}

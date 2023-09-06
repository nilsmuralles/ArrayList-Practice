/*
 * @author: Nils Muralles Morales
 * @version: 1.0
 * @since: 03/09/2023
 * Clase Examen que modela el resultado de un alumno
 */

public class Examen {

    // Atributos
    String materia;
    double nota;
    int idAlumnoExamen;

    public Examen(String materia, double nota, int idAlumnoExamen) { // Constructor de Examen
        this.materia = materia;
        this.nota = nota;
        this.idAlumnoExamen = idAlumnoExamen;
    }

    /**
     * @return String
     */
    // Setters y getters
    public String getMateria() {
        return materia;
    }

    /**
     * @param materia
     */
    public void setMateria(String materia) {
        this.materia = materia;
    }

    /**
     * @return double
     */
    public double getNota() {
        return nota;
    }

    /**
     * @param nota
     */
    public void setNota(double nota) {
        this.nota = nota;
    }

    /**
     * @return int
     */
    public int getIdAlumnoExamen() {
        return idAlumnoExamen;
    }

    /**
     * @param idAlumnoExamen
     */
    public void setIdAlumnoExamen(int idAlumnoExamen) {
        this.idAlumnoExamen = idAlumnoExamen;
    }

}

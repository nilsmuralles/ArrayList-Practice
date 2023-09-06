/*
 * @author: Nils Muralles Morales
 * @version: 1.0
 * @since: 03/09/2023
 * Clase Alumno que modela a un alumno con sus notas
 */

// Importar las clases necesarias
import java.util.Date;
import java.util.ArrayList;

public class Alumno {

    // Atributos
    String nombre;
    String apellido;
    int id;
    Date fechaNacimiento;
    String correo;
    ArrayList<Examen> resultados = new ArrayList<Examen>();

    // Constructor de Alumno
    public Alumno(String nombre, String apellido, int id, Date fechaNacimiento, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
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
     * @return String
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Date
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return ArrayList<Examen>
     */
    public ArrayList<Examen> getResultados() {
        return resultados;
    }

    /**
     * @param resultados
     */
    public void setResultados(ArrayList<Examen> resultados) {
        this.resultados = resultados;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return id + "|" + nombre + " " + apellido + "|" + correo;
    }

}

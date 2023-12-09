package com.mycompany.biblioteca;

import java.util.LinkedList;

/**
 *
 * @author JMINOTA
 */
public class Copia extends Libro {
    
    
    /**
     * ESTADO (DISPONIBLE - PRESTADO)
     * PUEDE SER BOOL TRUE=DISPONIBLE FALSE=PRESTADO
     **/
    int id;
    String estado;

    public Copia(int id, String estado, String nombre, String tipo, String editorial, int anio, LinkedList<Autor> autores) {
        super(nombre, tipo, editorial, anio, autores);
        this.id = id;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public LinkedList<Autor> getAutores() {
        return autores;
    }

    public void setAutores(LinkedList<Autor> autores) {
        this.autores = autores;
    }
}

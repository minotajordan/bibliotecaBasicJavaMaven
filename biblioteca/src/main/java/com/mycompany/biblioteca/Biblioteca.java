package com.mycompany.biblioteca;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.LinkedList;
import java.time.LocalDate;

public class Biblioteca {

    public static void main(String[] args) {

        Gson gson = new Gson();
        LinkedList<Libro> libros = new LinkedList<>();
        
        LinkedList<Autor> autoresLibro1 = new LinkedList<>();
        LinkedList<Autor> autoresGenericos = new LinkedList<>();
        
        LinkedList<Copia> copias = new LinkedList<>();
        LinkedList<Lector> lectores = new LinkedList<>();
        
        LinkedList<Prestamo> prestamos = new LinkedList<>();
        
        LinkedList<Multa> multas = new LinkedList<>();
        
        
        /** Obtener la fecha actual **/ 
        LocalDate fechaActual = LocalDate.now();
        
        /** Obtener el mes actual como un objeto Month **/
        int mesActual = fechaActual.getMonthValue();
        int anioActual = fechaActual.getYear();
        
        /**
         *
         * ADD AUTORES - CREAMOS UN METODO LLAMADO ADDAUTOR addAutor
         * @params
         * (String nombre, String nacionalidad, String hbd)
         * EN ESTE ENVIO LOS PARAMETROS NECESARIO PARA GENERAR EL NUEVO OBJ
         * Y EL METODO ME RETORNA UN OBJETO AUTOR PARA MANIPULAR
         *
         **/

         
         autoresLibro1.add(addAutor("Gabriel", "Colombia", "1900", null));
         
         
         autoresGenericos.add(addAutor("Pepe", "Colombia", "1910", null));
         autoresGenericos.add(addAutor("Fabiola", "Chile", "2000", null));

        /**
         *
         * ADD LIBROS - CREAMOS UN METODO LLAMADO ADDLIBRO addLibro 
         * @params
         * (String nombre,String tipo, String editorial, String anio)
         * EN ESTE ENVIO LOS PARAMETROS NECESARIO PARA GENERAR EL NUEVO OBJ
         * Y EL METODO ME RETORNA UN OBJETO LIBRO PARA MANIPULAR
         *
         **/
        
        libros.add(addLibro("Lbro1", "tipo 1", "economica", 2013, autoresLibro1));
        libros.add(addLibro("Lbro2", "tipo 2", "top", 2005, autoresGenericos));
        libros.add(addLibro("Lbro3", "tipo 3", "economica", 1995, autoresGenericos));
        libros.add(addLibro("Lbro4", "tipo 4", "basica", 2015, autoresGenericos));
        libros.add(addLibro("Lbro5", "tipo 5", "costosa", 2020, autoresGenericos));
        
        
        /**
         *
         * ADD COPIAS DE LOS LIBROS - CREAMOS UN METODO LLAMADO ADDCOPIA addCopy
         * @params
         * (String nombre,String tipo, String editorial, String anio)
         * EN ESTE ENVIO LOS PARAMETROS NECESARIO PARA GENERAR EL NUEVO OBJ
         * Y EL METODO ME RETORNA UN OBJETO COPIA PARA MANIPULAR
         *
         **/
        
        int indiceCopia = 1;
        for (Libro libroCopiar : libros) {
            
            /**
             * POR CADA LIBRO EN NUESTRA BIBLIOTECA TENDREMOS 2 COPIAS
             * POR ESE MOTIVO AUMENTAMOS EL INDICE CADA QUE AÑADIMOS UN LIBROS
             * AQUÍ POR EJEMPLO AÑADIRMOS LA PRIMER COPIA
             **/
            copias.add(addCopia(
                    indiceCopia,
                    "DISPONIBLE", 
                    libroCopiar.nombre,
                    libroCopiar.tipo,
                    libroCopiar.editorial,
                    libroCopiar.anio,
                    libroCopiar.autores
            ));
            indiceCopia++;
            
            /**
             * Y AQUÍ POR AÑADIRMOS LA SEGUNDA COPIA
             **/
            copias.add(addCopia(
                    indiceCopia,
                    "DISPONIBLE", 
                    libroCopiar.nombre,
                    libroCopiar.tipo,
                    libroCopiar.editorial,
                    libroCopiar.anio,
                    libroCopiar.autores
            ));
            indiceCopia++;
        }
        
        /**
         * PARA EFECTOS PRACTICOS LOGICAMENTE AÑADIMOS DE ENTRADA LOS LECTORES 
         * QUE TENDREMOS, PERO ESTOS TAMBIEN SE DEBEN LLENAR AL MOMENTO DE UNA
         * SOLICITUD DE PRESTAMOS.
         * 
         * EJEMPLO SI YO VOY A PRESTAR UN LIBRO Y EL LECTOR AUN NO EXISTE
         * LOGICAMENTE DEBEO AÑADIRLO, PERO SI ESTE YA EXISTE SOLO DEBO PASARLE
         * EL IDENTIFICADOR Y EL LIBRO
         * 
         * @params
         * (int id, String nombre, String apellido, String dir)
         * EN ESTE ENVIO LOS PARAMETROS NECESARIO PARA GENERAR EL NUEVO OBJ
         * Y EL METODO ME RETORNA UN OBJETO COPIA PARA MANIPULAR
         **/
        
        lectores.add(addLector(32434, "Alvaro", "Rubie", "Uberrimo") );
        lectores.add(addLector(66666, "Gustavo", "Petro", "Casa Nariño") );
        
        
        
        // ------------- ESTO DEBERIA SER UN METODO PARA PRESTAR --------------
        
        /**
         * VAMOS A AÑDIR UN PRESTAMO PARA ESTE EJEMPLO
         * PRESTAREMOS AL USUARIO 32434 EL LIBRO Lbro1 QUE ESTA EN LA COPIA 1, 2
         * SOLO PRESTAREMOS LA 1
         **/
        
        /**
         * OBTENDREMOS EL AUTOR INDICE DEL AUTOR IDENTIFICADO CON 32434 
         * PARA LUEGO AÑADIRLO AL NUEVO PRESTAMOS
         * LO MISMO HACEMOS CON LA COPIA  PUESTO QUE YA SABEMOS QUE COPIA TIENE
         * INDICE 2 ENTONCES LA BUSCAMOS Y OBTENEMOS ESE OBJETO
         **/
        
        Lector lectorPrestar = null;
        for (Lector lector : lectores) {
            if(lector.getIdentificador() == 32434){
               // Con esto ahora tenemos el objeto del lector deseado 
               lectorPrestar = lector;
            }
        }
        
        Copia copiaPrestar = null;
        for (Copia copia : copias) {
            if(copia.getId() == 2){
              copiaPrestar = copia;  
              copiaPrestar.setEstado("PRESTADO");
            }
        }
        
        if (copiaPrestar != null && lectorPrestar != null) {
            prestamos.add(
                    nuevoPrestamo(101, 82023, 92023, copiaPrestar, lectorPrestar)
            );
        }
        
        // --------------------------- FIN -------------------------------------
        
        /**
         * RECORDEMOS QUE EL NUEVO PRESTAMOS QUE AÑADIMOS LE ASIGNAMOS UN ID 101
         * POSTERIORMENTE COMO LA FECHA ES 8 2023 DE INICIO Y FECHA 9 2023
         * SE SUPONE QUE TENIA QUE DEVOLVER EL LIBRO EN AGOSTO O NOVIEMBRE
         * Y COMO ESTAMOS EN DICIEMBRE O SEA 12 2023 
         * SE DEBE GENERAR UNA MULTA SUMANDO EL MES 10 11 Y 12 PARA PAGAR
         **/
        
        
        // ------------- ESTO DEBERIA SER UN METODO PARA MULTA --------------
        
        int idPrestamo = 101;
        
        // VAMOS A CALCULAR CUANTO TIEMPO DEBE COMO FECHA INICIO Y FIN DE LA
        // MULTA PARA QUE ESTA QUEDE REGISTRADA
        
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getId() == idPrestamo) {
                
                // Añadimos la logica de las fechas
                int fechaInicioMulta = prestamo.getFechaFin();
                int fechaFinMulta = mesActual;
                
                //obtenemos el lector y tambien obtenemos el prestamos
                Lector lectorMulta = prestamo.getLector();
                Prestamo prestamoMulta = prestamo;
                
                // creamos un objeto de multa y luego lo añadimos al array de multas y tambien al lector
                Multa multaGenerada = nuevaMulta( 
                        idPrestamo,
                        fechaInicioMulta,
                        fechaFinMulta,
                        lectorMulta, 
                        prestamoMulta
                );
                
                multas.add(multaGenerada);
            }
        }
        
        
        // --------------------------- FIN -------------------------------------
        
        get("/libros", (req, res) -> {
            res.type("application/json");
            return gson.toJson(libros);
        });
        
        get("/copias", (req, res) -> {
            res.type("application/json");
            return gson.toJson(copias);
        });

        get("/lectores", (req, res) -> {
            res.type("application/json");
            return gson.toJson(lectores);
        });

        get("/prestamos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(prestamos);
        });
    }

    public static Libro addLibro(String nombre, String tipo, String editorial, int anio, LinkedList<Autor> autores) {
        Libro libro = new Libro(nombre, tipo, editorial, anio, autores);
        return libro;
    }
    
    public static Autor addAutor(String nombre, String nacionalidad, String hbd, LinkedList<Libro> libros) {
        Autor autor = new Autor(nombre, nacionalidad, hbd, libros);
        return autor;
    }
    
    public static Copia addCopia(int id, String estado, String nombre, String tipo, String editorial, int anio, LinkedList<Autor> autores) {
        Copia copia = new Copia(id, estado, nombre, tipo, editorial, anio, autores);
        return copia;
    }
    
    public static Lector addLector(int id, String nombre, String apellido, String dir) {
        // dejamos el ultimo en null porque este no tiene multas apenas lo creamos
        Lector lector = new Lector(id, nombre, apellido, dir, null);
        return lector;
    }
    
    public static Prestamo nuevoPrestamo(int id, int fechaInicio, int fechaFin, Copia copia, Lector lector) {
        Prestamo prestamo = new Prestamo(id, fechaInicio, fechaFin, copia, lector);
        return prestamo;
    }
    
    public static Multa nuevaMulta(int id, int fechaInicio, int fechaFin, Lector lector, Prestamo prestamo) {
        Multa multa = new Multa(id, fechaInicio, fechaFin, lector, prestamo);
        return multa;
    }
    
}

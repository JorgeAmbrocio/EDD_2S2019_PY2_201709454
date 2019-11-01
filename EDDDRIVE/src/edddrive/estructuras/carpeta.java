/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.estructuras;

import edddrive.classes.archivo;

/**
 *
 * @author David Ventura
 */
public class carpeta {
    public String nombre;
    public arbolAVL archivos;
    public listaDobleEnlazada carpetas;
    
    public carpeta () {
        this.archivos = new arbolAVL();
        this.carpetas  = new listaDobleEnlazada();
    }
    
    public void crearCarpeta (String nombre_) {
        carpeta tmp = new carpeta();
        tmp.nombre = nombre_;
        this.carpetas.insertarFinal(nombre_, tmp);
    }
    
    public void crearArchivo (String nombre, String extension, String contenido) {
        archivo tmp = new archivo(nombre, extension , contenido);
        this.archivos.insertar(nombre, tmp);
    }
    
    
    public void eliminarArchivo (String nombre) {
        this.archivos.eliminar(nombre);
    }
    
    public void eliminarCarpeta(String nombre){
        this.carpetas.eliminarDato(nombre);
    }
    
    
}

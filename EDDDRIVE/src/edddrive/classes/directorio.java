/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.classes;

/**
 *
 * @author David Ventura
 */
import edddrive.estructuras.*;
public class directorio {
    
    public matrizAdyacente mt;
    public listaDobleEnlazada lde;
    
    // constructor vacío
    public directorio  () {
        this.mt = new matrizAdyacente();
        this.lde = new listaDobleEnlazada ();
    }
    
    
    public void crearCarpeta () {}
    
    public void crearArchivo() {}
    
    public void eliminarCarpeta () {}
    
    public void eliminarArchivo () {}
    
    public boolean existeArchivo () { return true;}
    
    public boolean existeCarpeta (){ return true;}
}

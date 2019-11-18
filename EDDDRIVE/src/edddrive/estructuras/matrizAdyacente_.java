/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.estructuras;

import javax.swing.JOptionPane;

/**
 *
 * @author David Ventura
 */
public class matrizAdyacente_ {
    
    public class nodo {
        
        nodo masx ;
        nodo menosx;
        
        nodo masy;
        nodo menosy;
        
        int indice;
        String nombre;
        String ruta;
        
        
        
        public nodo  (String nombre, String ruta, int indice) {
            this.nombre = nombre;
            this.ruta = ruta;
            this.indice = indice;
        }
        
    }
    
    
    public nodo raiz ;
    public int tamano = 0;
    
    
    public void crearCarpeta (String nombre, String ruta) {
        
        if (this.existecarpeta(ruta)) {
            // si la ruta existe, ya no la crea
            JOptionPane.showMessageDialog(null, "Esta carpeta ya existe.");
            
        }else {
            // si la carpeta no existe, se crea el nodo
            
        }
        
    }
    
    public void insertarx (nodo nd) {
        
    }
    
    public void insertary () {
        
    }
    
    public boolean existecarpeta (String ruta) {
        
        nodo tmp= this.raiz;
        
        while (tmp != null) {
            
            if ( tmp.ruta.equals(ruta) ) {
                // encontró el ruta dela carpeta
                return true;
            }
            
            tmp = tmp.masx;
        }
        
        return false;
        
    }
    
    
    public nodo crearNodo (String nombre, String ruta) {
        return new nodo(nombre, ruta , this.tamano);
    }
    
    
}

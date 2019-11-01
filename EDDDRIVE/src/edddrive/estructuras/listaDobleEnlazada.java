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
public class listaDobleEnlazada {
    public class nodo {
        nodo siguiente ;
        nodo anterior;
        
        String dato ;
        carpeta dato_;

        public nodo(String dato, carpeta dato_) {
            this.dato = dato;
            this.dato_ = dato_;
            this.dato_.nombre=dato;
            
            this.siguiente =null;
            this.anterior = null;
        }

        public nodo() {}
    }
    
    nodo inicio ;
    nodo final_;
    int largo = 1;
    public void insertarFinal(String dato , carpeta dato_) {
        
        
        // verifica que no exista el dato
        carpeta tmp = this.getDato(dato);
        
        if (tmp != null) {
            JOptionPane.showMessageDialog(null, "Esta carpeta ya existe. :-)", "Soy un título" , JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // crear temporal para recorrer la lista actual
        if (this.inicio == null){
            // si el inicio es nulo, se inserta el nodo en el inicio
            nodo nuevo=new nodo(dato,dato_);
            this.inicio = nuevo;
            this.final_ = nuevo;
            this.final_.anterior = this.inicio;
        }else {
            
            nodo nuevo = new nodo (dato, dato_) ;
            
            // conectar nuevo nodo a la lista
            nuevo.anterior = this.final_;
            
            // conecatar lista a nodo
            this.final_.siguiente = nuevo;
            
            // apuntar final a nuevo
            this.final_  = nuevo;
            
            
            
        }
        
        largo += 1;
    }
    
    public void reemplazarDato (String dato , carpeta dato_) {
        int option = JOptionPane.showConfirmDialog(null, "Esta carpeta ya existe, ¿deseas reemplazarla?");
                            
        if (option == 0) {
            // sí se procede con la inserción
            // eliminar el nodo anterior
            this.eliminarDato(dato);
            this.insertarFinal(dato, dato_);
            return;

        }else {
            // no se procede con la inserción
            return;
        }
        
        
    }
    
    public carpeta getDato (String dato) {
        // crear puntero
        nodo tmp = this.inicio;
        
        while (tmp != null) {
            if (tmp.dato_.nombre.equalsIgnoreCase(dato)) {
                // encontramos la carpeta
                return tmp.dato_;
            }
            
            tmp  = tmp.siguiente;
        }
        
        return null;
    }
    
    public void eliminarDato (String dato) {
        // crear puntero
        nodo tmp = this.inicio;
        
        if (this.inicio.dato.equalsIgnoreCase(dato)) {
            // si el inicio es igual al dato
            // apuntar inicio al siguiente
            this.inicio= this.inicio.siguiente;
            return;
        }
        
        
        while (tmp != null) {
            if (tmp.dato_.nombre.equalsIgnoreCase(dato)) {
                // encontramos la carpeta
                // eliminar nodo
                // estamos en el nodo a eliminar
                
                if (tmp.siguiente != null) {
                    tmp.siguiente.anterior = tmp.anterior;
                }
                
                if (tmp.anterior != null) {
                    tmp.anterior.siguiente = tmp.siguiente;
                }
                return;
            }
            
            tmp = tmp.siguiente;
        }
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edddrive.estructuras;

/**
 *
 * @author dventura
 */


import javax.swing.JOptionPane ;
public class arbolAVL {
    public class nodo  {
        nodo padre;
        nodo derecha;
        nodo izquierda;
        
        String cont;
        Object contenido;
        
        int altura ;
        int equilibrio;
        
        public nodo (String cont_, Object contenido_) {
            this.cont = cont_;
            this.contenido = contenido_ ;
        }
        
        public nodo () {
        }
        
    }
    
    nodo raiz  ;
    int cantidad ;
    
    
    public void insertar(String dato, Object dato_){
        if (this.raiz == null ){
            // arbol es null, se inserta el dato en la raíz
            this.raiz = new nodo(dato, dato_);
            this.raiz.equilibrio = 0;
            
        }else {
            // verificar en qué parte del árbol se insertan los datos 
            this.insertar_(dato, dato_ , this.raiz);
        }
    }
    

    public void insertar_ (String dato , Object dato_ , nodo arbol){
        if (dato.compareToIgnoreCase(arbol.cont) > 0 ){
            // dato es mayor que el contenido actual del nodo
            // insertar a la derecha
            
            if (arbol.derecha == null) {
                // si el hijo derecha es nulo,se inserta en esta posición
                arbol.derecha = new nodo(dato, dato_);
                arbol.derecha.padre = arbol;
                
                // inspeccionar la inserción
                
                
            }else {
                this.insertar_(dato, dato_, arbol.derecha);
            }
            
        }else if (dato.compareToIgnoreCase(arbol.cont) < 0 ) {
            // dato es menor que el contenido actual del nodo
            // insertar a la izquierda
            
            if (arbol.izquierda == null) {
                // si el hijo es nulo, se inserta 
                arbol.izquierda = new nodo (dato , dato_);
                arbol.izquierda.padre = arbol;
                
                // inspeccionar insercion
                
            }else {
                
                this.insertar_(dato, dato_, arbol.izquierda);
            }
            
        }else {
            JOptionPane.showMessageDialog(null, "Este dato ya ha sido insertado. :-)");
        }
        
    }
    
    
    
}

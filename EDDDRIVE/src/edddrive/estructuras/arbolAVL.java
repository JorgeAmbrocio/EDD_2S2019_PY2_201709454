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
    private class nodo  {
        nodo padre;
        nodo derecha;
        nodo izquierda;
        
        String cont;
        Object contenido;
        int equilibrio;
        
        public nodo (String cont_) {
            this.cont = cont_;
        }
        
        public nodo () {
        }
        
    }
    
    nodo raiz = new nodo() ;
    int cantidad ;
    
    
    public void insertar(String dato, Object dato_){
        if (this.raiz != null ){
            // arbol es null, se inserta el dato en la raíz
            this.raiz = new nodo(dato);
            this.raiz.equilibrio = 0;
            
        }else {
            // verificar en qué parte del árbol se insertan los datos 
            this.insertar_(dato, dato_ ,null, this.raiz);
        }
    }
    
    public void insertar_ (String dato , Object dato_ , nodo padre, nodo arbol){
        
        if (dato.compareToIgnoreCase(arbol.cont) > 0 ){
            // dato es mayor que el contenido actual del nodo
            
            
        }else if (dato.compareToIgnoreCase(arbol.cont) < 0 ) {
            // dato es menor que el contenido actual del nodo
            
        }else {
            JOptionPane.showMessageDialog(null, "Este dato ya ha sido insertado. :-)");
        }
        
    }
    
}

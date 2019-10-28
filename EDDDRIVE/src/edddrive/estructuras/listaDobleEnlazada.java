/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.estructuras;

/**
 *
 * @author David Ventura
 */
public class listaDobleEnlazada {
    public class nodo {
        nodo siguiente ;
        nodo anterior;
        
        String dato ;
        Object dato_;

        public nodo(String dato, Object dato_) {
            this.dato = dato;
            this.dato_ = dato_;
            
            this.siguiente =null;
            this.anterior = null;
            
        }

        public nodo() {
        }
        
        
        
        
    }
    
    nodo inicio ;
    int largo = 1;
    public void insertarInicio(String dato , Object dato_) {
        
        // crear temporal para recorrer la lista actual
        if (this.inicio == null){
            // si el inicio es nulo, se inserta el nodo en el inicio
            this.inicio = new nodo(dato, dato_);
            
        }else {
            // recorrer lista hasta que el nodo siguiente sea nulo o que el valor del nodo sea inferior
            if (dato.compareTo(this.inicio.dato) < 0) {
                // dato es antes al dato del inicio
                // insertar en el inicio
                nodo nd = new nodo (dato, dato_);
                
                // conectar el nodo
                nd.siguiente = this.inicio;
                
                // conectar la lista al nodo
                this.inicio.anterior = nd;
                
                // apuntar inicio a nuevo nodo
                this.inicio = nd;
                
            }else {
                // encontrar posición par ainsertar los datos
                nodo tmp = this.inicio;
                
                while (tmp.siguiente != null && tmp.siguiente.dato.compareToIgnoreCase(dato) < 0 ) {
                    
                    tmp = tmp.siguiente;
                }
                
                // ya estamos en el nodo previo a la inserción
                nodo nd = new nodo (dato, dato_);
                // conectar nodo a la lista
                nd.anterior = tmp;
                nd.siguiente =tmp.siguiente;
                
                // conectar lista a nodo
                if (tmp.siguiente == null) {
                    // si el siguiente es nulo, solo se conecta el siguiente del temporal
                    tmp.siguiente =nd;
                    
                }else {
                    // se conecta siguiete de temporal y anteiror de siguiente temporal
                    tmp.siguiente.anterior = nd;
                    tmp.siguiente = nd;
                }
                
                
            }
        }
        
        largo += 1;
    }
    
    
}

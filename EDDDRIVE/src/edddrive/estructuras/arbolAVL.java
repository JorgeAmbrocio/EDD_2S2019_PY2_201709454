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
            
            this.derecha = null;
            this.izquierda = null;
            
        }
        
        public nodo () {
            this.derecha = null;
            this.izquierda = null;
        }
        
    }
    
    nodo raiz  ;
    int cantidad ;
    
    public arbolAVL () {
        this.raiz = null;
    }
    
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
            JOptionPane.showMessageDialog(null, "Este dato ( " +  dato + " ) ya ha sido insertado. :-)");
        }
        
    }
    
    public nodo buscar (String dato , nodo arbol) {
        
        if (arbol == null ) {
            
            return null;
            
        }else if (arbol.cont.equals(dato)) {
            return arbol;
        }else if  (dato.compareToIgnoreCase(arbol.cont) > 0) {
            // dato es mayor al valor del nodo actual
            /// buscar a la derecha
            this.buscar(dato, arbol.derecha);
        }else if (dato.compareToIgnoreCase(arbol.cont) < 0) {
            // dato es menor al valor del nodo actual
            // buscar a la izquierda
            this.buscar(dato, arbol.izquierda);
        }
        
        return null;
    }
    
    // obtener el fator de equilibrio
    public int getFE ( nodo arbol ){
        
        if (arbol == null) {
            return -1;
            
        }else {
            return arbol.equilibrio;
        }
        
    }
    
    public int getAltura (nodo arbol) {
        if (arbol == null) {return 0;}
        
        return arbol.altura;
    }
    
   
    public nodo rotarIzquierda_ (nodo arbol) {
        nodo aux = arbol.izquierda;
        
        // re ordenar nodos
        arbol.izquierda  = aux.derecha;
        aux.derecha = arbol;
        
        // obtener nuevo factor de equilibrrio
        arbol.equilibrio = 1+ Math.max(this.getFE(arbol.derecha), this.getFE(arbol.izquierda));
        aux.equilibrio = 1+ Math.max(this.getFE(arbol.derecha), this.getFE(arbol.izquierda));
        
        return aux;
        
    }
    
    public nodo rotarDerecha_ (nodo arbol) {
        nodo aux = arbol.derecha;
        
        // re ordenar nodos
        arbol.derecha =aux.izquierda;
        aux.izquierda = arbol;
        
        // obtener nuevo factor de equilibrio
        arbol.equilibrio = 1+ Math.max(this.getFE(arbol.derecha), this.getFE(arbol.izquierda));
        aux.equilibrio = 1+ Math.max(this.getFE(arbol.derecha), this.getFE(arbol.izquierda));
        
    
        return aux;
    }
    
    
    public nodo rotarDobleIzquierda_ (nodo arbol ) {
        
        nodo aux ;
        
        arbol.izquierda = this.rotarDerecha_(arbol.izquierda);
        aux = this.rotarIzquierda_(arbol);
        
        return aux;
    }
    
    public nodo rotarDobleDerecha_ (nodo arbol) {
        
        nodo aux;
        
        arbol.derecha  = this.rotarIzquierda_(arbol.derecha);
        aux = this.rotarDerecha_(arbol);
        
        return aux;
    }
    
    public nodo insertarAVL (nodo nuevo, nodo padre) {
        nodo nuevoPadre = padre;
        
        if (nuevo.cont.compareToIgnoreCase(padre.cont) < 0) {
            
            // nuevo menor que el padre, se inserta a la izquierda
            if (padre.izquierda == null) {
                // si padre izquierda está vacío, insertar en esa posción
                padre.izquierda = nuevo;
            }else {
                // si padre.izquierda no es vacío
                padre.izquierda = this.insertarAVL(nuevo, padre.izquierda);
                
                int iz = this.getFE(padre.izquierda);
                int der  = this.getFE(padre.derecha);
                
                
                if ((iz - der )  == 2) {
                    
                    if (nuevo.cont.compareToIgnoreCase(padre.izquierda.cont) < 0) {
                        // rotación izquierda
                        nuevoPadre = this.rotarIzquierda_(padre);
                    }else {
                        nuevoPadre = this.rotarDobleIzquierda_(padre);
                    }
                    
                }
                
            }
            
        }else if (nuevo.cont.compareToIgnoreCase(padre.cont)>0) {
            
            // nuevo nodo es mayor que el padre, se inserta a la derecha
            if (padre.derecha == null) {
                // nodo vacío, insertar en esta posición
                padre.derecha = nuevo;
            }else {
                // nodo no es vacío, imandar a insertar en los hijos de padre.deracha
                padre.derecha = this.insertarAVL(nuevo, padre.derecha);
                
                int iz = this.getFE(padre.izquierda);
                int der = this.getFE(padre.derecha);
                
                if ((der - iz) == 2) {
                    // si el árbol está desbalanceado
                    if (nuevo.cont.compareToIgnoreCase(padre.derecha.cont) > 0) {
                        nuevoPadre = this.rotarDerecha_(padre);
                    }else {
                        nuevoPadre = this.rotarDobleDerecha_(padre);
                    }
                }
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Este dato ya ha sido insertado: " + nuevo.cont );
        }
        
        // actualizar altura
        if (  (padre.izquierda == null) &&  (padre.derecha != null)   ) {
            padre.equilibrio = padre.derecha.equilibrio +1;
        }else if (  (padre.derecha == null) && (padre.izquierda != null)  ) {
            padre.equilibrio = padre.izquierda.equilibrio +1;
        }else {
            padre.equilibrio = 1+ Math.max(this.getFE(padre.derecha), this.getFE(padre.izquierda));
        }
        
        return nuevoPadre;
        
    }
    
    
    
    
    
    
}


    /* // rotar simple izquieda 
    public void rotarIzquierda (nodo arbol) {
        
        // apuntar los auxiliares a las partes del árbol
        nodo aux1 = arbol.padre;
        nodo aux2 = arbol.izquierda;
        nodo aux3 = arbol.izquierda.derecha;
        
        // apuntar arbol.izquierda.derecha a arbol
        aux2.derecha = arbol;
        
        // apuntar arbol.izquirda a arbol.izquirda.derecha
        arbol.izquierda = aux3;
        
        // apuntuntar el arbol.izquierda.derecha a arbol
        if (aux3 != null) { // si es nulo, no se debe apuntar a su padre
            // si el nodo existe, apuntar a su nuevo padre
            aux3.padre = arbol;
        }
        
        // apuntar el arbol.izquierda (aux2) a su nueo padre
        aux2.padre = aux1;
        
        // verificar si el nodo padre del aux2 es null, si es null, entonces se convierte en la nueva raíz
        if (aux2.padre == null) {
            this.raiz = aux2;
        }else {
            // apuntar el padre (antiguo padre del nodo arbol) del aux2  al aux2
            if (aux2.padre.izquierda == arbol) {
                aux2.padre.izquierda = aux2;
            }else {
                aux2.padre.derecha = aux2;
            }
        }
        
        // obtener nueva altura de los nodos
        
        //altura del nodo arbol
        arbol.altura =  1 + Math.max(this.getAltura(arbol.izquierda), this.getAltura(arbol.derecha));
        
        // altura del nodo aux2
        aux2.altura = 1 + Math.max(this.getAltura(arbol.izquierda), this.getAltura(arbol.derecha));
        
    }
    
    // rotar simple derecha
    public void rotarDerecha (nodo arbol) {
        // apuntar los auxiliares a las partes del árbol
        nodo aux1 = arbol.padre;
        nodo aux2 = arbol.derecha;
        nodo aux3  = arbol.derecha.izquierda;
        
        // apuntar la izquierda del arbol.derecha al arbol
        aux2.izquierda = arbol;
        
        // apuntar la derecha del arbol al arbol.derecha.izquierda
        arbol.derecha = aux3;
        
        // apuntar el aux3 a su nuevo padre
        if (aux3 != null) {
            aux3.padre =  arbol;
        }
        
        // apuntar el aux2 a su nuevo padre
        aux2.padre = aux1;
        
        // apuntar el aux1 a su nuevo hijo
        if (aux2.padre == null) {
            // si el padre del aux2 es null, significa que el sux2 es la nueva raíz
            this.raiz = aux2;
        }else {
            // apuntar el aux1 a su nuevo hijo
            if  (aux2.padre.izquierda == arbol) {
                aux2.padre.izquierda = aux2;
            }else {
                aux2.padre.derecha  = aux2;
            }
        }
        
        // calcular la nueva altura 
        arbol.altura =  1+ Math.max(this.getAltura(arbol.derecha), this.getAltura(arbol.izquierda));
        
    }
    
    */

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
public class matrizAdyacente {
    
    public class nodo {
        
        public nodo menosX ;
        public nodo masX ;
        public nodo menosY ;
        public nodo masY;
        
        
        int x, y;
        
        public String llave ;
        public Object contenido;
        
        
        public nodo () {}
        
        public nodo (int x , int y){
            this.x =x ; this.y = y;
        } 
        
        public nodo (Object dato , int x, int y) {
            this.contenido = dato; this.x = x ; this.y = y;
        }
    }
    
    public nodo raiz;
    
    public matrizAdyacente () {
        this.raiz = new nodo(-1 , -1);
    }
    
    
    public void crearX (int x) {
        // apunta a la raíz
        nodo tmp = this.raiz;
        
        while (tmp.masX != null && tmp.masX.x <  x) {
            tmp = tmp.masX;
        }
        
        // estamos en el nodo previo a la insercion
        // crear el nodo 
        nodo nuevo = new nodo (x, -1);
        // conectar nodo a matriz
        nuevo.menosX = tmp;
        nuevo.masX = tmp.masX;
        
        if (tmp.masX ==null){
            tmp.masX = nuevo;
        }else {
            tmp.masX.menosX = nuevo;
            tmp.masX = nuevo;
        }
    }
    
    public void crearY (int y) {
        // apunta a la raiz
        nodo tmp = this.raiz;
        
        while (tmp.masY != null && tmp.masY.y < y) {
            tmp = tmp.masY;
        }
        
        // estamos en el nodo previo a la insercion
        nodo nuevo = new nodo (-1 , y);
        nuevo.menosY = tmp;
        nuevo.masY = tmp.masY;
        
        if (tmp.masY == null) {
            tmp.masY = nuevo;
        }else {
            tmp.masY.menosY = nuevo;
            tmp.masY = nuevo;
        }
        
    }
    
    public boolean existeX (int x) {
        
        // apuntar al inicio
        nodo tmp  = this.raiz;
        
        // validar el primer nodo
        while (tmp != null){
            
            // verifica si la coordenada del nodo temporal es iguala a la coordenada del argumento
            if (tmp.x == x) {
                return true;
            }
            
            tmp = tmp.masX;
        }
        
        return false;
    }
    
    public boolean existeY (int y) {
        
        // apuntar al inicio
        nodo tmp = this.raiz;
        
        while (tmp != null) {
            
            if (tmp.y == y) {
                return true;
            }
            
            tmp = tmp.masY;
        }
        
        return false;
    }
    
    public void insertarX (nodo nd) {
        
        // apuntar a la raiz
        nodo tmp = this.raiz;
        
        // encontrar la posición en y
        while (tmp.y != nd.y) {
            tmp = tmp.masY;
        }
        
        // recorrer y hasta encontrar la posición en x
        while (tmp.masX != null && tmp.masX.x < nd.x) {
            tmp = tmp.masX;
        }
        
        // estamos en la posición previa a la inserción
        // conectar nodo a la matriz
        nd.menosX = tmp;
        nd.masX = tmp.masX;
        
        // conecar matriz a nodo
        if (nd.masX == null) {
            tmp.masX = nd;
        }else {
            tmp.masX.menosX = nd;
            tmp.masX = nd;
        }
    }
    
    public void insertarY (nodo nd) {
        
        // apuntar raiz
        nodo tmp = this.raiz;
        
        // encontrar la pocisión x en la que se desea insertar en y
        while (tmp.x != nd.x) {
            tmp = tmp.masX;
        }
        
        // encontrar la posición adecuada en y
        while (tmp.masY != null && tmp.masY.y < nd.y) {
            tmp = tmp.masY;
        }
        
        // estamos en la pocision previa a la insercion
        // conecat nodo a la matriz
        nd.menosY = tmp;
        nd.masY = tmp.masY;
        
        // conectar matriz a nodo
        if (tmp.masY == null ) {
            tmp.masY = nd;
        }else {
            tmp.masY.menosY = nd;
            tmp.masY = nd;
        }
        
    }
    
    public void insertar (Object dato, int x, int y) {
        
        // verifica si existen los datos
        boolean existeX_ = this.existeX(x);
        boolean existeY_ = this.existeY(y);
        
        if (!existeX_ && !existeY_) {
            this.crearX(x); this.crearY(y);
            
        }else if (!existeX_ && existeY_) {
            this.crearX(x);
            
        }else if (existeX_ && !existeY_) {
            this.crearY(y);
            
        }else if (existeX_ && existeY_) {
            
        }
        
        // crear nodo e insertarlo
        nodo nd = new nodo(dato, x,y) ;
        
        this.insertarX(nd);
        this.insertarY(nd);
        
    }
    
}

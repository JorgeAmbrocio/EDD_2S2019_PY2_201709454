/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.estructuras;

import java.security.MessageDigest;
import edddrive.classes.usuario;
import java.util.ArrayList;
/**
 *
 * @author dventura
 */
public class tablaHash {
    
    public enum estado { vacio, ocupado, borrado }
    
    public class celda {
        public int llave_ = 0;
        public usuario usuario_ = null;
        public estado estado_ = estado.vacio;
    }
    
    int tMax, usado;
    double factorCarga ;
    celda tabla[];
    
    // constructor
    public tablaHash () {
        this.tMax = 7 ;
        this.usado = 0;
        this.factorCarga  = 0;
        this.tabla = new celda[this.tMax];
        
        for  (int i = 0 ; i < this.tMax ; i++) { this.tabla[i] = new celda() ;}
        
    }
    
    
    public void insertar (String usuario_, String contrasena_) {
        
        // verifica si aún tenemos espacio para insertar el dato
        if (this.factorCarga  > 0.75) {
            this.redimencionarTabla();
        }
        
        // llave 
        int llave = this.getSumaASCII(usuario_);
        
        // calcular indice
        int indice = this.getHash(llave);
        
        // insertar el dato
        this.tabla[indice].usuario_ = new usuario (usuario_ , contrasena_) ;
        this.tabla[indice].llave_ = llave;
        this.tabla[indice].estado_ = estado.ocupado;
        
        this.usado += 1;
        this.factorCarga = (double) ( (double) this.usado /  (double) this.tMax);
    }
    
    public usuario buscar (String usuario_) {
        
        // obtiene el índice del elemento a buscar
        int indice = (( this.getSumaASCII(usuario_) ) % this.tMax);
        
        int intento = 0;
        while (this.tabla[indice].estado_ != estado.vacio) {
            
            if  ( this.tabla[indice].estado_ == estado.ocupado ) {
                
                if (this.tabla[indice].usuario_.getUsuario_().equalsIgnoreCase(usuario_) ) {
                    return this.tabla[indice].usuario_;
                }else {
                    if (indice == 0 || indice == 1 ) { indice = 1; }
                
                    indice = ( indice + (indice * indice ) + intento ) % this.tMax;
                    intento ++;
                }
            }
        }
        
        return null;
    }
    
    
    
    public void redimencionarTabla () {
        
        // guardar copias del estado actual 
        int aux = this.tMax;
        celda [] aux_celda = this.tabla;
        
        // generar nuevos valores
        this.tMax = this.buscarProximoPrimo(tMax + 1 );
        this.tabla = new celda[this.tMax];
        this.factorCarga = 0.0;
        this.usado = 0;
        
        // iniciar los valores
        for (int i = 0 ; i  < this.tMax ; i++) { this.tabla[i] = new celda(); }
        
        // insertar todos los valores anteriores en la nueva tabla
        for (int i = 0 ; i < aux ; i ++ ) { 
            
            
            if (aux_celda[i].estado_ == estado.ocupado) {
                usuario us = aux_celda[i].usuario_;
                this.insertar(us.getUsuario_(), us.getContrasena_());
            }
            
        }
    }
    
    public int buscarProximoPrimo (int i) {
        i += 1;
        
        while (!this.esPrimo(i)) {
            i += 1;
        }
        
        System.out.println(i);
        
        return i;
    }
    
    
    public boolean esPrimo(int numero){
        int contador = 2;
        boolean primo=true;
        
        while ((primo) && (contador!=numero)){
          if (numero % contador == 0){
            primo = false;
          }
          contador++;
        }
        return primo;
    }

    public String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
    }

    public int getSumaASCII (String cadena) {
        int suma  = 0;
        
        
        for (int x=0;x<cadena.length();x++){
            suma += cadena.codePointAt(x);
            System.out.println(cadena.charAt(x) + " = " + cadena.codePointAt(x));;
        }
        
        System.out.println(cadena + " = " + suma);
        return suma;
    }
    
    public int getHash (int llave) {
        
        // obtiene el índice del elemento a buscar
        int indice = ((llave ) % this.tMax);
        
        int intento = 0;
        while (true) {
            
            if (this.tabla[indice].estado_ == estado.vacio || this.tabla[indice].estado_ == estado.borrado ) {
                return indice;
            }else{
                
                if (indice == 0 || indice == 1 ) { indice = 1; }
                
                indice = ( indice + (indice * indice ) + intento ) % this.tMax;
                intento ++;
            }
        }
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.classes;

/**
 *
 * @author dventura
 */

import edddrive.estructuras.*;
public class usuario {
    
    private String usuario_;
    private String contrasena_;
    public carpeta directorio;
    public usuario (String usuario__ , String contrasena__) {
        this.usuario_ = usuario__;
        this.contrasena_ = contrasena__;
        this.directorio  = new carpeta();
        this.directorio.nombre = "/";
        
        
    }

        
    // gets
    public String getUsuario_() {
        return usuario_;
    }

    public String getContrasena_() {
        return contrasena_;
    }

    // sets
    public void setUsuario_(String usuario_) {
        this.usuario_ = usuario_;
    }

    public void setContrasena_(String contrasena_) {
        this.contrasena_ = contrasena_;
    }
    
    
    
}

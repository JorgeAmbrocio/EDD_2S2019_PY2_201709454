/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive;

/**
 *
 * @author David Ventura
 */
import edddrive.EDDDRIVE.*;
import edddrive.EDDDRIVE;
import edddrive.classes.*;
import edddrive.estructuras.*;
import edddrive.formularios.*;
import javax.swing.JOptionPane;

public class funciones {
    
    public void cargaMasivaUsuarios() {
        
        
        
    }
    
    public boolean Ingresar (String usuario , String contrasena) {
        
        contrasena = EDDDRIVE.usuarios.sha256(contrasena);
        
        usuario us = EDDDRIVE.usuarios.buscar(usuario);
        
        if (us == null) {
            // no existe el usuario
            JOptionPane.showMessageDialog(null, "El usuario no existe.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else {
            // verificar la contrasena
            if (us.getContrasena_().equalsIgnoreCase(contrasena)) {
                // con iguales
                return true;
            }else {
                // no son iguales
                JOptionPane.showMessageDialog(null, "La contraseña no coincide.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        return false;
    }
    
    
}

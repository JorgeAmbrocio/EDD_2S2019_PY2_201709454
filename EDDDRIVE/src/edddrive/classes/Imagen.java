/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edddrive.classes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author David Ventura
 */
public class Imagen extends javax.swing.JPanel {
    public String ruta; 

    public Imagen(String ruta) {
        this.setSize(900, 900) ; 
        this.setPreferredSize(new Dimension (10000,10000)) ; 
        this.ruta = ruta; 
    }
    
    
    public void paintComponent (Graphics grafico) {
        
        Image image ;
        
        super.paintComponent(grafico);
        
        File rutaImagen = new File (this.ruta );
        
        try {
            image = ImageIO.read(rutaImagen);
            grafico.drawImage(image,0, 0, null);
            
            
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "No se puede encontrar la imagen", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        this.setOpaque(false);
        
        
    }
    
}

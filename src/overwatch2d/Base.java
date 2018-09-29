/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overwatch2d;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Ariel Vitor Molina
 */
public abstract class Base {
    //int coord_x, coord_y;
    float coord_x, coord_y;
    boolean right, left, up, down;
    boolean isPlayable;
    Rectangle hitbox;
    BufferedImage img;
    
    public Base(){
        
    }
    
    public Base(String url){
       try{
           img = ImageIO.read(getClass().getResourceAsStream(url));
       } catch(IOException e){
           
       }
       
       hitbox =  new Rectangle();
       hitbox.width = img.getWidth();
       hitbox.height  = img.getHeight();
    }
    
    
    public abstract void draw(Graphics g);
    public abstract void move(int x_limit, int y_limit);
}

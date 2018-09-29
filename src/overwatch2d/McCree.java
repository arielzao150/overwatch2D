/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overwatch2d;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Ariel Vitor Molina
 */
public class McCree extends Character{
    
    public McCree(String url){
        super(url);
        move_speed = 0.15f;
        bullet_speed = .5f;
        health = 100;
        reload_speed = 1500;
        ammo = maxAmmo = 6;
        fire_rate = 500f;
        isPlayable = true;
        ult_charge = 0;
    }

    @Override
    public void Ultimate(ArrayList<Bullet> shots) {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SecondFire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Ability() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Fire(Bullet tiro) {
        tiro.playerShot = true;
        tiro.coord_x = coord_x+100;
        tiro.coord_y = coord_y+45;
        tiro.speed = bullet_speed;
        tiro.right = true;
        tiro.hitbox = new Rectangle((int)coord_x, (int)coord_y, 10, 10);
        lastShot = System.currentTimeMillis();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, (int)coord_x, (int)coord_y, 100, 100, null);
    }
}

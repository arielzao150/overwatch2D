/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overwatch2d;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ariel Vitor Molina
 */
public class Enemy extends Base{
    float time_to_appear;
    float move_speed = 0.12f;
    float bullet_speed = .5f;
    int health = 100;
    float fire_rate;
        
    public Enemy(String url){
        super(url);
        move_speed = 0.12f;
        bullet_speed = .5f;
        health = 100;
        fire_rate = 500f;
        hitbox = new Rectangle((int)coord_x, (int)coord_y, 80, 80);
    }

    public void Fire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, (int)coord_x, (int)coord_y, 100, 100, null);
        //g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    @Override
    public void move(int x_limit, int y_limit) {
        coord_x -= move_speed;
        hitbox.x = (int)coord_x+30;
        hitbox.y = (int)coord_y+10;
    }
}

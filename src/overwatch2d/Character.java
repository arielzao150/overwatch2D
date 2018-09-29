/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overwatch2d;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Ariel Vitor Molina
 */
public abstract class Character extends Base{
    float move_speed;
    float bullet_speed;
    int reload_speed;
    int ult_charge;
    float fire_rate;
    long lastShot;
    private int scnd_fire_rate;
    private int scnd_fire_speed;
    private int ability_cooldown;
    int health;
    int ammo;
    int maxAmmo;
    boolean isFiring;
    boolean isReloading;
    boolean hasAmmo;
    
    
    ImageIcon ult;
    
    public abstract void Ultimate(ArrayList<Bullet> shots);
    
    public abstract void SecondFire();
    public abstract void Ability();
    
    public Character(){
    }
    public Character(String url){
        super(url);
    }
    
    
    @Override
    public void move(int x_limit, int y_limit) {
        if(right && coord_x < x_limit)
            coord_x += move_speed;
        if(left && coord_x > 0)
            coord_x -= move_speed;
        if(up && coord_y > 20)
            coord_y -= move_speed;
        if(down && coord_y+100 < y_limit)
            coord_y += move_speed;
    }
}

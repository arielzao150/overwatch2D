/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overwatch2d;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Ariel Vitor Molina
 */
public class Bullet extends Base{
    boolean playerShot;
    float speed;
    
    public boolean isCollidingWithEnemy(ArrayList<Enemy> inimigos) {
        for(int i = 0; i < inimigos.size(); i++) {
            Enemy busca = inimigos.get(i);
            if(busca.hitbox.contains(hitbox.x, hitbox.y)){
                
                CollideWithEnemy(inimigos, busca);
                return true;
            }
        }
        return false;
    }

    public void Die(ArrayList<Bullet> shots){
        shots.remove(this);
        coord_x = -10;
        coord_y = -10;
        hitbox.x = -10;
        hitbox.y = -10;
    }
    
    public void CollideWithEnemy(ArrayList<Enemy> inimigos, Enemy inimigo) {
        inimigos.remove(inimigo);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)coord_x, (int)coord_y, 10, 10);
    }

    @Override
    public void move(int x_limit, int y_limit) {
        if(right){
            coord_x += speed;
            //go right
        }
        else{
            coord_x -= speed;
            //go left
        }
        hitbox.x = (int)coord_x;
        hitbox.y = (int)coord_y;
    }
    
}

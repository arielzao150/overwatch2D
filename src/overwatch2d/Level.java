/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overwatch2d;

/**
 *
 * @author Ariel Vitor Molina
 */
public class Level {
    int enemy_count;
    int level;
    public Level(int i){
        level = i;
        enemy_count = 100*i;
    }
}

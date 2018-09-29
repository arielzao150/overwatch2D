/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overwatch2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 2D character shooter based on Overwatch
 * Character rendered on the left, moves with AWSD, shoots with left mouse,
 * alternate fire with right mouse, ability SHIFT and ultimate Q
 * Enemies come from the right (purple omnics) that can have different health
 * levels
 * Player gathers points, has a limited ammount of life and can move and shoot
 * @author Ariel Vitor Molina
 */
public class Screen extends javax.swing.JFrame implements Runnable {

    McCree player;
    ArrayList<Bullet> shots;
    ArrayList<Enemy> enemies;
    /**
     * Creates new form Screen
     */
    public Screen() {
        initComponents();
        createBufferStrategy(2);
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(720, 480));
        setPreferredSize(new java.awt.Dimension(720, 480));
        setSize(new java.awt.Dimension(720, 480));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        
        switch (evt.getKeyCode()) {
            case 'A':
            case 'a':            
                // move o PC para a esquerda
                player.left = true;
                break;
            case 'S':
            case 's':
                // move o PC para baixo
                player.down = true;
                break;
            case 'D':
            case 'd':
                // move o PC para a direita
                player.right = true;
                break;
            case 'W':
            case 'w':
                // move o PC para cima
                player.up = true;
                break;
            case 'Q':
            case 'q':
                // ativa Ultimate se disponivel
                //if(player.ult_charge == 100)
                    for(int i=0;i < getHeight();i += 15){
                        Bullet tiro = new Bullet();
                        player.Fire(tiro);
                        tiro.coord_y = i;
                        shots.add(tiro);
                    }
                break;
            case 'E':
            case 'e':
                // 
                break;
            case 'R':
            case 'r':
                // recarrega munição
                if(player.ammo != player.maxAmmo){
                    player.isReloading = true;
                    Timer reload = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            player.ammo = player.maxAmmo;
                            player.isReloading = false;
                        }
                    };
                    reload.schedule(task, player.reload_speed);
                }
                break;
            case KeyEvent.SHIFT_DOWN_MASK:
                // ativa habilidade se disponivel
                break;
            default:
                break;
        }
    }//GEN-LAST:event_formKeyPressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        switch(evt.getButton()){
            case 1:
                // tiro primario se tiver munição
                if(player.ammo > 0 // tem munição
                   && !player.isFiring // não está atirando
                   && !player.isReloading // não está carregando
                   && System.currentTimeMillis() - player.lastShot > player.fire_rate
                        ){
                    Bullet tiro = new Bullet();
                    player.Fire(tiro);
                    shots.add(tiro);
                    
                    player.ammo--;
                }
                break;
            case 3:
                // tiro secundário se disponivel
                if(player.ammo > 0 // tem munição
                   && !player.isFiring // não está atirando
                   && !player.isReloading // não está carregando
                        ){
                    for(int i = 0; i < player.ammo; i++){
                    Bullet tiro = new Bullet();
                    player.Fire(tiro);
                    shots.add(tiro);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    player.ammo = 0;
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        switch(evt.getButton()){
            case 1:
                // tiro primario enquanto tiver munição
                break;
            case 2:
                // tiro secundário se disponivel
                break;
        }
    }//GEN-LAST:event_formMousePressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case 'A':
            case 'a':
                // move o PC para a esquerda
                player.left = false;
                break;
            case 'S':
            case 's':
                // move o PC para baixo
                player.down = false;
                break;
            case 'D':
            case 'd':
                // move o PC para a direita
                player.right = false;
                break;
            case 'W':
            case 'w':
                // move o PC para cima
                player.up = false;
                break;
            case 'Q':
            case 'q':
                // ativa Ultimate se disponivel
                break;
            case 'E':
            case 'e':
                // 
                break;
            case 'R':
            case 'r':
                // recarrega munição
                break;
            case KeyEvent.SHIFT_DOWN_MASK:
                // ativa habilidade se disponivel
                break;
            default:
                break;
        }
    }//GEN-LAST:event_formKeyReleased

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        switch(evt.getButton()){
            case 1:
                // tiro primario enquanto tiver munição
                break;
            case 2:
                // tiro secundário se disponivel
                break;
            default:
                break;
        }
    }//GEN-LAST:event_formMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Screen().setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        Random random = new Random();
        int score = 0;
        Graphics g;
        long lastEnemySpawn = 0;
        long lastUltGain = 0;
        // run the game
        
        // run bkg music
        player = new McCree("/img/Spray_McCree_Pixel.png");
        player.coord_x = 20;
        player.coord_y = 20;
        
        enemies = new ArrayList<Enemy>();
        shots = new ArrayList<Bullet>();
        /*for(int i = 0; i < current_Level.enemy_count; i++){
            enemies.add(new Enemy());
        }*/
        // draw background
        
        while(true){
            g = getBufferStrategy().getDrawGraphics();
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            
            // draw GUI
            g.setColor(Color.BLACK);
            g.drawString("Life: " + player.health, 10, 50);
            g.drawString("Ammo: " + player.ammo, 10, 60);
            g.drawString("Score: " + score, 10, 70);
            if(player.isReloading)
                g.drawString("Reloading", (int)player.coord_x+20, (int)player.coord_y);
            
            if(player.health <= 0){
                // GAME OVER
            }
            
            // update player
            player.move(getWidth(), getHeight());
            player.draw(g);
            
            // update enemies
            for(int i = 0; i < enemies.size(); i ++){
                Enemy enemy = enemies.get(i);
                enemy.move(getWidth(), getHeight());
                if(enemy.coord_x+50 < 0)
                    enemies.remove(enemy);
                else
                    enemy.draw(g);
            }
            
            // update shots
            for(int i = 0; i < shots.size(); i++){
                Bullet shot = shots.get(i);
                
                if(shot.playerShot){
                    if(shot.isCollidingWithEnemy(enemies)){
                    // COLIDE
                    // ou tira vida do inimigo ou do player
                    shot.Die(shots);
                    }
                    if(shot.coord_x > getWidth())
                        shot.Die(shots);
                    else {
                        shot.move(getWidth(), getHeight());
                        shot.draw(g);
                    }
                }
                else {
                    if(shot.coord_x < 0)
                    shot.Die(shots);
                     {
                        shot.move(getWidth(), getHeight());
                        shot.draw(g);
                    }
                }
            }
            
            // spawn enemies
            long tempoAtual = System.currentTimeMillis();
            if(tempoAtual - lastUltGain > 500)
                player.ult_charge++;
            if(tempoAtual - lastEnemySpawn > 2000){
                lastEnemySpawn = tempoAtual;
                Enemy novoInimigo = new Enemy("/img/Spray_Talon_Trooper.png");
                novoInimigo.coord_x = getWidth();
                novoInimigo.coord_y = getHeight() - random.nextInt(getHeight() - 100) - 100;
                enemies.add(novoInimigo);
            }
            g.dispose();
            getBufferStrategy().show();
            /*try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
            }*/
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

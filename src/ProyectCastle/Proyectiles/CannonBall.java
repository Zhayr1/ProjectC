/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle.Proyectiles;

import ProyectCastle.Castles.PlayerCastle;

/**
 *
 * @author Jesus
 */
public interface CannonBall {
    //Direction
    //1: UP - 2: Down - 3: Left - 4: Right
    
    public void shot(int direction,PlayerCastle castle);
    public void disable();
    public void updatePosition();
    
    //Getters
    public int getVel();
    public boolean isActive();
    //Setters
    public void setVel(int v);
    public void setInactive();
    
    
}

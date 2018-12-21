/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle.Castles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Jesus
 */
public class EnemyCastle extends PlayerCastle{
    
    public EnemyCastle(float x, float y, float width, float height) throws SlickException {
        super(x, y, width, height);
    }
    
    @Override
        public void drawComponents(Graphics g){
        g.drawString(String.valueOf(super.getHp()), this.getX() + super.getCastleWidth()/2 , this.getY() + super.getCastleHeight() * 0.4f);
    }
    
}

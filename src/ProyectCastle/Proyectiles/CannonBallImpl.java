/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle.Proyectiles;

import ProyectCastle.Castles.PlayerCastle;
import ProyectCastle.Game;
import org.newdawn.slick.geom.Circle;
/**
 *
 * @author Jesus
 */
public class CannonBallImpl extends Circle implements CannonBall{
    
    private final int radio = 10;
    private final int diametro = radio *2;
    private int vel = 5;    
    public boolean activeStatus = false;    
    public boolean exploited = false;
    private boolean UP = false;
    private boolean DOWN = false;
    private boolean LEFT = false;
    private boolean RIGHT = false;
    private float timer = 1;
    
    private Game main;

    public CannonBallImpl(float x1, float y1) {
        super(x1, y1, 10);
    }

    @Override
    public void shot(int direction,PlayerCastle castle) {
        setNotExploited();
        setActive();
        switch(direction){
            case 1:
                UP = true;
                setXY(castle.getX() + castle.getWidth()/2 + this.radio , castle.getY());
                break;
            case 2:
                DOWN = true;
                setXY(castle.getX() + castle.getWidth()/2 + this.radio, castle.getY() + castle.getHeight());                
                break;
            case 3:
                LEFT = true;
                setXY(castle.getX(), castle.getY() + castle.getHeight()/2 - this.radio);                
                break;
            case 4:
                RIGHT = true;
                setXY(castle.getX() + castle.getWidth() , castle.getY() + castle.getHeight()/2 - this.radio);                
                break;
        }
    }

    @Override
    public void disable() {
        setExploited();
        if(UP){
            if(this.intersects(Game.EnemyCastle2)){
                Game.EnemyCastle2.setDamage(1);
                setInactive();
                resetDirs();
            }
        }
        if(DOWN){
            if(this.intersects(Game.EnemyCastle3)){
                Game.EnemyCastle3.setDamage(1);
                setInactive();
                resetDirs();
            }
        }
        if(LEFT){
            if(this.intersects(Game.EnemyCastle3)){
                Game.EnemyCastle3.setDamage(1);
                setInactive();
                resetDirs();
            }
        }
        if(RIGHT){
            if(this.getCenterX() >= Game.SCREEN_X - 200 + radio){
                Game.EnemyCastle2.setDamage(1);
                setInactive();
                resetDirs();
            }
        }
    }

    @Override
    public int getVel() {
        return vel;
    }

    @Override
    public boolean isActive() {
        return activeStatus;
    }
    
    @Override
    public void updatePosition(){
        if(UP)    super.setY(y - vel);
        if(DOWN)  super.setY(y + vel);
        if(LEFT)  super.setX(x - vel);
        if(RIGHT) super.setX(x + vel);
        disable();
    }

    @Override
    public void setVel(int v) {
        vel = v;
    }

    @Override
    public void setInactive() {
        activeStatus = false;
    }
    public void setActive(){
        activeStatus = true;
    }
    public void setExploited(){
        exploited = true;
    }
    public void setNotExploited(){
        exploited = false;
    }
    private void setXY(float x1, float y1){
        super.setX(x1);
        super.setY(y1);
    }
    private void resetDirs(){
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = false;
    }
    public boolean isExploited(){
        return exploited;
    }
    public void decreaseTimer(float i){
        timer -= i;
    }
    public void resetTimer(){
        timer = 1;
    }
    public float getTimer(){
        return timer;
    }
}

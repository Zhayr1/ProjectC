/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle.Proyectiles;

import ProyectCastle.Castles.PlayerCastle;
import ProyectCastle.Game;
import ProyectCastle.MainApplication;
import org.newdawn.slick.geom.Circle;
/**
 *
 * @author Jesus
 */
public class CannonBallImpl extends Circle implements CannonBall{
    
    public static final int UP = 1;
    public static final int UPLEFT = 5;
    public static final int UPRIGHT = 6;
    public static final int DOWN = 2;
    public static final int DOWNLEFT = 7;
    public static final int DOWNRIGHT = 8;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    
    private final int radio = 10;
    private int vel = 5;    
    public boolean activeStatus = false;    
    public boolean exploited = false;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean upleft = false;
    private boolean upright = false;
    private boolean downleft = false;
    private boolean downright = false;
    private float timer = 1;
    
    public CannonBallImpl(float x1, float y1) {
        super(x1, y1, 10);
    }

    @Override
    public void shot(int direction,PlayerCastle castle) {
        setNotExploited();
        setActive();
        switch(direction){
            case UP:
                up = true;
                setXY(castle.getX() + castle.getWidth()/2 - this.radio , castle.getY());
                break;
            case DOWN:
                down = true;
                setXY(castle.getX() + castle.getWidth()/2 - this.radio, castle.getY() + castle.getHeight());                
                break;
            case LEFT:
                left = true;
                setXY(castle.getX(), castle.getY() + castle.getHeight()/2 - this.radio);                
                break;
            case RIGHT:
                right = true;
                setXY(castle.getX() + castle.getWidth() , castle.getY() + castle.getHeight()/2 - this.radio);                
                break;
            case UPLEFT:
                upleft = true;
                setXY(castle.getX() + this.radio, castle.getY() + this.radio);
                break;
            case UPRIGHT:
                upright = true;
                setXY(castle.getX() + castle.getWidth() - this.radio, castle.getY() + this.radio);
                break;
            case DOWNLEFT:
                downleft = true;
                setXY(castle.getX() + this.radio, castle.getY() + castle.getHeight() - this.radio);
                break;
            case DOWNRIGHT:
                downright = true;
                setXY(castle.getX() + castle.getWidth() - this.radio, castle.getY() + castle.getHeight() + this.radio);
                break;
                
        }
    }

    @Override
    public void disable() {
        setExploited();
        if(up){
            if(this.intersects(Game.redCastle) || this.intersects(Game.blueCastle) ){
                if(this.intersects(Game.redCastle)) Game.redCastle.setDamage(1);
                if(this.intersects(Game.blueCastle)) Game.blueCastle.setDamage(1);
                setInactive();
                resetDirs();
            }
        }
        if(down){
            if(this.intersects(Game.yellowCastle) || this.intersects(Game.greenCastle) ){
                if(this.intersects(Game.yellowCastle)) Game.yellowCastle.setDamage(1);
                if(this.intersects(Game.greenCastle)) Game.greenCastle.setDamage(1);
                setInactive();
                resetDirs();
            }
        }
        if(left){
            if(this.intersects(Game.yellowCastle) || this.intersects(Game.redCastle) ){
                if(this.intersects(Game.yellowCastle)) Game.yellowCastle.setDamage(1);
                if(this.intersects(Game.redCastle)) Game.redCastle.setDamage(1);
                    setInactive();
                    resetDirs();
            }
        }
        if(right){
            if(this.intersects(Game.blueCastle) || this.intersects(Game.greenCastle) ){
                if(this.intersects(Game.blueCastle))  Game.blueCastle.setDamage(1);
                if(this.intersects(Game.greenCastle)) Game.greenCastle.setDamage(1);
                    setInactive();
                    resetDirs();
            }
        }
        if(downleft){
            if(this.intersects(Game.yellowCastle)){
                if(this.intersects(Game.yellowCastle)) Game.yellowCastle.setDamage(1);
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
        if(up){
            super.setY(y - vel);
        }else if(down){
            super.setY(y + vel);
        }else if(left){
            super.setX(x - vel);
        }else if(right){
            super.setX(x + vel);
        }else if(upleft){
            setXY( x - vel , y - vel );
        }else if(upright){
            setXY( x - vel , y - vel );
        }else if(downleft){
            setXY( x - vel*1.5f , y + vel*0.9f );
        }else if(downright){
            setXY( x - vel , y - vel );
        }
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
        up = false;
        down = false;
        left = false;
        right = false;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle.Castles;


import ProyectCastle.Game;
import ProyectCastle.Proyectiles.CannonBallImpl;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Jesus
 */
public class PlayerCastle extends Rectangle implements Castle{
    
    private final float castleWidth;
    private final float castleHeight;
    private int hp,energy,nCballs;
    private CannonBallImpl cannonBalls[];
    private Image arrowUp,arrowDown,arrowLeft,arrowRight;
    private String position;
    private boolean bolUp,bolDown,bolLeft,bolRight;

    public PlayerCastle(float x, float y, float width, float height,String pos) throws SlickException {
        super(x, y, width, height);
        this.castleWidth = (float) (Game.SCREEN_X * 0.22);
        this.castleHeight = (float) (Game.SCREEN_Y * 0.25);
        super.setWidth(castleWidth);
        super.setHeight(castleHeight);
        bolUp = bolDown = bolLeft = bolRight = false;
        position = pos;
        hp = 10;
        energy = 0;
        nCballs = 3;
        cannonBalls = new CannonBallImpl[10];
        arrowUp = new Image("Assets/ArrowUp.png");
        arrowDown = new Image("Assets/ArrowDown.png");
        arrowLeft = new Image("Assets/ArrowLeft.png");
        arrowRight = new Image("Assets/ArrowRight.png");
        for (int i = 0; i < 10; i++) {
            cannonBalls[i] = new CannonBallImpl(super.getX(), super.getY());
        }        
    }

    @Override
    public void setDamage(int dmg) {
        hp -= dmg;
    }

    @Override
    public void updateCannonBallsDisp(int num_cBalls) {
        if(nCballs <= 10){
            nCballs++;
        }
    }
    
    public void shotCBall(int dir){
        for (int i = 0; i < nCballs; i++) {
            if(!cannonBalls[i].isActive()){
                cannonBalls[i].shot(dir,this);
                return;
            }
        }
    }
    public CannonBallImpl[] getCBalls(){
        return cannonBalls;
    }
    public int getNumCBalls(){
        return nCballs;
    }
    public void drawComponents(Graphics g){
        if(position == "TL" && bolDown)  arrowDown.draw(castleWidth * 0.375f,castleHeight + castleHeight * 0.05f);
        if(position == "TL" && bolRight) arrowRight.draw(this.getX() + castleWidth + 20, this.getY() + castleHeight * 0.3f);
        if(position == "TR" && bolDown) arrowDown.draw(this.getX() + castleWidth/2, this.getY() + castleHeight + 5);
        if(position == "TR" && bolLeft) arrowLeft.draw(this.getX() - 40,this.getY() + castleHeight * 0.30f);
        if(position == "BR" && bolUp) arrowUp.draw(this.getX() + castleWidth/2, this.getY() - 60);
        if(position == "BR" && bolLeft) arrowLeft.draw(this.getX() - 40,this.getY() + castleHeight * 0.30f);
        if(position == "BL" && bolUp) arrowUp.draw(this.getX() + castleWidth * 0.375f, this.getY() - 60);
        if(position == "BL" && bolRight) arrowRight.draw(this.getX() + castleWidth + 20, this.getY() + castleHeight * 0.3f);
        g.drawString(String.valueOf(hp), this.getX() + castleWidth/2 , this.getY() + castleHeight * 0.4f);
        if(position.equals("BR")){
            g.drawString("UP: "+ String.valueOf(this.getUp()), 300, 300);
            g.drawString("DOWN: "+ String.valueOf(this.getDown()), 300, 350);
            g.drawString("LEFT: "+ String.valueOf(this.getLeft()), 300, 400);
            g.drawString("RIGHT: "+ String.valueOf(this.getRight()), 300, 450);
        }
    }
    public int getHp(){
        return hp;
    }
    public float getCastleWidth(){
        return castleWidth;
    }
    public float getCastleHeight(){
        return castleHeight;
    }
    public void setUp(){
        bolUp = true;
    }
    public void setDown(){
        bolDown = true;
    }
    public void setLeft(){
        bolLeft = true;
    }
    public void setRight(){
        bolRight = true;
    }
    public void unsetUp(){
        bolUp = false;
    }
    public void unsetDown(){
        bolDown = false;
    }
    public void unsetLeft(){
        bolLeft = false;
    }
    public void unsetRight(){
        bolRight = false;
    }
    public boolean getUp(){
        return bolUp;
    }
    public boolean getDown(){
        return bolDown;
    }
    public boolean getLeft(){
        return bolLeft;
    }
    public boolean getRight(){
        return bolRight;
    }
    public void setPosition(String pos){
        position = pos;
    }
    public void reInitCastle(float x1,float y1){
        this.setX(x1);
        this.setY(y1);
        super.setX(x1);
        super.setY(y1);
    }
}

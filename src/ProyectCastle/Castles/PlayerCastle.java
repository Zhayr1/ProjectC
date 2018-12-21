/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle.Castles;


import Player.Player;
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
    
    private final int castleWidth = 150;
    private final int castleHeight = 150;
    private static int GameRound = 3;    
    private int hp,energy;
    private CannonBallImpl cannonBalls[];
    private Player player;
    private Image arrowUp,arrowDown,arrowLeft,arrowRight,castleImg;
    private int position;

    public PlayerCastle(float x, float y, float width, float height,String imageSrc) throws SlickException {
        super(x, y, width, height);
        super.setWidth(castleWidth);
        super.setHeight(castleHeight);
        this.reInitCastle(x, y);
        castleImg = new Image(imageSrc);
        position = 0;
        hp = 10;
        energy = 0;
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

    public void nextRound() {
        if(GameRound <= 10){
            GameRound++;
            //this.reInitCannonBalls();
        }
    }
    
    public void shotCBall(int dir){
        for (int i = 0; i < GameRound; i++) {
            if(!cannonBalls[i].isActive()){
                cannonBalls[i].shot(dir,this);
                return;
            }
        }
    }
    public CannonBallImpl[] getCBalls(){
        return cannonBalls;
    }
    public void drawComponents(Graphics g){
        castleImg.draw(this.getX(),this.getY());
        //Dibujar las flechas
        
        //
        g.drawString(String.valueOf(hp), this.getX() + castleWidth* 0.45f , this.getY() + castleHeight * 0.45f);
        if(position == Player.TL){
            g.drawString("UP: "+ String.valueOf(player.getUp()), 300, 300);
            g.drawString("DOWN: "+ String.valueOf(player.getDown()), 300, 350);
            g.drawString("LEFT: "+ String.valueOf(player.getLeft()), 300, 400);
            g.drawString("RIGHT: "+ String.valueOf(player.getRight()), 300, 450);
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

    private void reInitCastle(float x1,float y1){
        this.setX(x1);
        this.setY(y1);
        super.setX(x1);
        super.setY(y1);
    }
    public void setPlayer(Player p){
        player = p;
        position = p.getPosition();
    }
    public Player getPlayer(){
        return player;
    }
}

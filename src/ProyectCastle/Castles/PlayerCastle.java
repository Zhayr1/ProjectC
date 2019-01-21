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
    private boolean destroyed;
    private CannonBallImpl cannonBalls[];
    private Player player;
    private final Image arrowUp,arrowDown,arrowLeft,arrowRight,castleImg;
    private final Image arrowUpLeft,arrowUpRight,arrowDownLeft,arrowDownRight;
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
        destroyed = false;
        cannonBalls = new CannonBallImpl[10];
        arrowUp = new Image("Assets/ArrowUp.png");
        arrowUpLeft = new Image("Assets/ArrowUp.png");
        arrowUpLeft.rotate(310);
        arrowUpRight = new Image("Assets/ArrowUp.png");
        arrowUpRight.rotate(30);
        arrowDown = new Image("Assets/ArrowDown.png");
        arrowDownLeft = new Image("Assets/ArrowUp.png");
        arrowDownLeft.rotate(235);
        arrowDownRight = new Image("Assets/ArrowUp.png");
        arrowDownRight.rotate(120);
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

    public static void nextRound() {
        if(GameRound <= 10){
            GameRound++;
            //this.reInitCannonBalls();
        }
    }
    public static int getRound(){
        return GameRound;
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
        g.drawString(String.valueOf(hp), this.getX() + castleWidth* 0.45f , this.getY() + castleHeight * 0.45f);
        if(player != null){
            //Dibujar las flechas
            if(!player.isBot()) this.drawDirectionArrows(g);
            //
        }
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public void setDestroyed(){
        destroyed = true;
    }
    public boolean isDestroyed(){
        return destroyed;
    }
    public Image getImage(){
        return castleImg;
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
        destroyed = false;
    }
     private void drawDirectionArrows(Graphics g){
        //UP
        if(player.getPosition() == Player.BR || player.getPosition() == Player.BL){
            if(player.getUp()){
                arrowUp.draw(this.getX() + 50 , this.getY() - 40 );
                arrowUp.setAlpha(1f);
            }else{
                arrowUp.draw(this.getX() + 50, this.getY() - 40);
                arrowUp.setAlpha(0.3f);
            }
        }
        //UP-LEFT
        if(player.getPosition() == Player.BR){
            if(player.getUpLeft()){
                arrowUpLeft.draw(this.getX() - 40, this.getY() -30);
                arrowUpLeft.setAlpha(1f);
            }else{
                arrowUpLeft.draw(this.getX() - 40, this.getY() -30);
                arrowUpLeft.setAlpha(0.3f);
            }
        }
        //UP-RIGHT
        if(player.getPosition() == Player.BL){
            if(player.getUpRight()){
                arrowUpRight.draw(this.getX() + 190, this.getY() -30);
                arrowUpRight.setAlpha(1f);
            }else{
                arrowUpRight.draw(this.getX() + 190, this.getY() -30);
                arrowUpRight.setAlpha(0.3f);
            }        
        }
        //DOWN
        if(player.getPosition() == Player.TL || player.getPosition() == Player.TR){
            if(player.getDown()){
                arrowDown.draw(this.getX() + 50 , this.getY() + 160 );
                arrowDown.setAlpha(1f);
            }else{
                arrowDown.draw(this.getX() + 50 , this.getY() + 160 );
                arrowDown.setAlpha(0.3f);
            }
        }
        //DOWN-LEFT
        if(player.getPosition() == Player.TR){
            if(player.getDownLeft()){
                arrowDownLeft.draw(this.getX() - 50 , this.getY() + 140);
                arrowDownLeft.setAlpha(1f);
            }else{
                arrowDownLeft.draw(this.getX() - 50, this.getY() + 140);
                arrowDownLeft.setAlpha(0.3f);
            }
        }
        //DOWN-RIGHT
        if(player.getPosition() == Player.TL){
            if(player.getDownRight()){
                arrowDownRight.draw(this.getX() + 150 , this.getY() + 140);
                arrowDownRight.setAlpha(1f);
            }else{
                arrowDownRight.draw(this.getX() + 150, this.getY() + 140);
                arrowDownRight.setAlpha(0.3f);
            }
        }
        //LEFT
        if(player.getPosition() == Player.BR || player.getPosition() == Player.TR){
            if(player.getLeft()){
                arrowLeft.draw(this.getX() - 40 , this.getY() + 50 );
                arrowLeft.setAlpha(1f);
            }else{
                arrowLeft.draw(this.getX() - 40 , this.getY() + 50 );
                arrowLeft.setAlpha(0.3f);
            }
        }
        //RIGHT
        if(player.getPosition() == Player.BL || player.getPosition() == Player.TL){
            if(player.getRight()){
                arrowRight.draw(this.getX() + 160 , this.getY() + 50 );
                arrowRight.setAlpha(1f);
            }else{
                arrowRight.draw(this.getX() + 160 , this.getY() + 50 );
                arrowRight.setAlpha(0.3f);
            }
        }
    }
    public void setPlayer(Player p){
        player = p;
        position = p.getPosition();
    }
    public Player getPlayer(){
        return player;
    }
}

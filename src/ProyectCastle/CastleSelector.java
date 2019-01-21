/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

import Player.Player;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Jesus
 */
public class CastleSelector{
    
    private Image[] selectArea,selectCastle;
    private Image selectArrowRight,selectArrowLeft,readyBtn;
    private String playerName;
    private int indice;
    private float x,y;
    private final int arrowLeftX = 345,arrowRightX = 445;
    private Player player;
    
    public CastleSelector(float x, float y,int castleColor,Player p) throws SlickException {
        this.x = x;
        this.y = y;
        player = p;
        
        selectArea = new Image[4];
        selectArea[0] = new Image("Assets/SelectFieldRed.png");
        selectArea[1] = new Image("Assets/SelectFieldBlue.png");
        selectArea[2] = new Image("Assets/SelectFieldYellow.png");
        selectArea[3] = new Image("Assets/SelectFieldGreen.png");
        //
        selectArrowRight = new Image("Assets/ArrowRight.png");
        selectArrowLeft = new Image("Assets/ArrowLeft.png");
        //
        selectCastle = new Image[4];
        selectCastle[0] = new Image("Assets/RedCastle.png");
        selectCastle[1] = new Image("Assets/BlueCastle.png");
        selectCastle[2] = new Image("Assets/YellowCastle.png");
        selectCastle[3] = new Image("Assets/GreenCastle.png");
        playerName = "Zhayr";
        indice = castleColor;
        player.setPosition(castleColor);

    }
    
    public void draw(float x , float y,Graphics g,int id){
        selectArea[indice].draw(x, y);
        g.drawString(player.getName(), x + 25, y + 25);
        selectCastle[indice].draw(x + 386, y + 40, 50,50);
    }
    public void drawArrows(){
        selectArrowLeft.draw(x + arrowLeftX , y + 40);
        selectArrowRight.draw(x + arrowRightX , y + 40);
    }
    public void leftArrowClick(){
        if(indice >= 1){
            indice--;
        }
    }
    public int getLeftX(){
        return arrowLeftX;
    }
    public int getRightX(){
        return arrowRightX;
    }
    public void rightArrowClick(){
        if(indice < 3){
            indice++;
        }
    }
    public Image getLeftArrow(){
        return selectArrowLeft;
    }
    public Image getRightArrow(){
        return selectArrowRight;
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public Player getPlayer(){
        return player;
    }
    public int getIndex(){
        return indice;
    }
    public void setIndex(int i){
        indice = i;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

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
    
    public CastleSelector(float x, float y,int castleColor) throws SlickException {
        this.x = x;
        this.y = y;
        
        selectArea = new Image[4];
        selectArea[0] = new Image("Assets/SelectFieldRed.png");
        selectArea[1] = new Image("Assets/SelectFieldBlue.png");
        selectArea[2] = new Image("Assets/SelectFieldYellow.png");
        selectArea[3] = new Image("Assets/SelectFieldGreen.png");
        //
        selectArrowRight = new Image("Assets/ArrowUp.png");
        selectArrowRight.rotate(90);
        selectArrowLeft = new Image("Assets/ArrowUp.png");
        selectArrowLeft.rotate(270);
        //
        selectCastle = new Image[4];
        selectCastle[0] = new Image("Assets/RedCastle.png");
        selectCastle[1] = new Image("Assets/BlueCastle.png");
        selectCastle[2] = new Image("Assets/YellowCastle.png");
        selectCastle[3] = new Image("Assets/GreenCastle.png");
        playerName = "Zhayr";
        indice = castleColor;
    }
    
    public void draw(float x , float y,Graphics g){
        selectArea[indice].draw(x, y);
        g.drawString(playerName, x + 25, y + 25);
        selectArrowLeft.draw(x + 345 , y + 49);
        selectCastle[indice].draw(x + 395, y + 40, 50,50);
        selectArrowRight.draw(x + 445 , y + 49);
    }
    public void leftArrowClick(){
        if(indice >= 1){
            indice--;
        }
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

/**
 *
 * @author Jesus
 */
public class Player {
    
    public static final int TL = 1;
    public static final int TR = 2;
    public static final int BL = 3;
    public static final int BR = 4;
    
    private int position;
    //1 - Top Left
    //2 - Top Right
    //3 - Bot Left
    //4 - bot Right
    private boolean Up,UpLeft,Down,Left,Right;
    
    public Player(){
        position = 0;
        Up  = UpLeft = Down = Left = Right = false;
    }
    
    public int getPosition(){
        return position;
    }
    public void setPosition(int pos){
        position = pos;
    }
    
    
    
    //Setters Booleanos
    public void setUp(){
        Up = true;
    }
    public void setUpLeft(){
        UpLeft = true;
    }
    public void setDown(){
        Down = true;
    }
    public void setLeft(){
        Left = true;
    }
    public void setRight(){
        Right = true;
    }
    public void unsetUp(){
        Up = false;
    }
    public void unsetDown(){
        Down = false;
    }
    public void unsetLeft(){
        Left = false;
    }
    public void unsetRight(){
        Right = false;
    }
    //Getters booleanos
    public boolean getUp(){
        return Up;
    }
    public boolean getUpLeft(){
        return UpLeft;
    }
    public boolean getDown(){
        return Down;
    }
    public boolean getLeft(){
        return Left;
    }
    public boolean getRight(){
        return Right;
    }
    
}

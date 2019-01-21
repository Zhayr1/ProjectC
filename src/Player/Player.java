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
    
    public static final int TL = 0;
    public static final int TR = 1;
    public static final int BL = 2;
    public static final int BR = 3;
    public static int totalPlayers = 0;
    private int id;
    private String name;
    private int position;
    protected boolean boolBot;
    //1 - Top Left
    //2 - Top Right
    //3 - Bot Left
    //4 - bot Right
    private boolean Up,Down,Left,Right;
    private boolean UpLeft,UpRight,DownLeft,DownRight;
    
    public Player(String name){
        position = 0;
        Up  = UpLeft = Down = Left = Right = false;
        totalPlayers++;
        id = totalPlayers;   
        this.name = name;
        boolBot = false;
    }
    
    public int getPosition(){
        return position;
    }
    public void setPosition(int pos){
        position = pos;
    }
    public int getID(){
        return id;
    }
    
    
    //Setters Booleanos
    public void setUp(){
        Up = true;
    }
    public void setUpLeft(){
        UpLeft = true;
    }
    public void setUpRight(){
        UpRight = true;
    }
    public void setDown(){
        Down = true;
    }
    public void setDownLeft(){
        DownLeft = true;
    }
    public void setDownRight(){
        DownRight = true;
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
    public void unsetUpLeft(){
        UpLeft = false;
    }
    public void unsetUpRight(){
        UpRight = true;
    }
    public void unsetDown(){
        Down = false;
    }
    public void unsetDownLeft(){
        DownLeft = false;
    }
    public void unsetDownRight(){
        DownRight = false;
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
    public boolean getUpRight(){
        return UpRight;
    }
    public boolean getDown(){
        return Down;
    }
    public boolean getDownLeft(){
        return DownLeft;
    }
    public boolean getDownRight(){
        return DownRight;
    }
    public boolean getLeft(){
        return Left;
    }
    public boolean getRight(){
        return Right;
    }
    public static int getTotalPlayers(){
        return totalPlayers;
    }
    public String getName(){
        return name;
    }
    public void resetDirs(){
        this.unsetDown();
        this.unsetUp();
        this.unsetLeft();
        this.unsetRight();
        this.unsetDownLeft();
        this.unsetDownRight();
        this.unsetUpLeft();
        this.unsetUpRight();
    }
    public boolean isBot(){
        return boolBot;
    }
}

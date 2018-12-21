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
public abstract class Player {
    
    public static final int TL = 1;
    public static final int TR = 2;
    public static final int BL = 3;
    public static final int BR = 4;
    
    private int position;
    //1 - Top Left
    //2 - Top Right
    //3 - Bot Left
    //4 - bot Right
    
    public Player(){
        position = 0;
    }
    
}

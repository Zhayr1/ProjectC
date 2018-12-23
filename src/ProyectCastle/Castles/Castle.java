/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle.Castles;

/**
 *
 * @author Jesus
 */
public interface Castle {

    public static final int RED = 0;    
    public static final int BLUE = 1;
    public static final int YELLOW = 2;
    public static final int GREEN = 3;


    //Funcion para restarle vida al castillo cuando impacte una bala
    public void setDamage(int dmg);
    
}

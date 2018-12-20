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

    //Funcion para restarle vida al castillo cuando impacte una bala
    public void setDamage(int dmg);
    //funcion para incrementar el numero de balas de cañon disponibles max 10
    public void updateCannonBallsDisp(int num_cBalls);
    
}

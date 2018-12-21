/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

import ProyectCastle.Castles.EnemyCastle;
import ProyectCastle.Castles.PlayerCastle;
import ProyectCastle.Proyectiles.CannonBallImpl;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/**
 *
 * @author Jesus
 */
public class Game extends BasicGameState{
    
    //State ID
    public static final int ID = 4;
    
    public static int SCREEN_X = 800;
    public static int SCREEN_Y = 600;
    public static float gameTime;
    public static String mousex = "",mousey = "";
    public static PlayerCastle EnemyCastle1,EnemyCastle2,EnemyCastle3,PlayerCastle;
    private static String hostPosition = "";
    private Image background;
    private Image explt1,explt2,explt3,explt4,explt5,explt6;
    private static boolean bolPause = true; 
    
    
    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            System.exit(0);
	}
        if(key == Input.KEY_UP){
            PlayerCastle.shotCBall(1);
        }
        if(key == Input.KEY_DOWN){
             PlayerCastle.shotCBall(2);
        }
        if(key == Input.KEY_LEFT){
             PlayerCastle.shotCBall(3);
        }
        if(key == Input.KEY_RIGHT){
             PlayerCastle.shotCBall(4);
        }
    }
    @Override
    public void keyReleased(int key,char c){
        if(key == Input.KEY_UP){
        }
    }
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if(PlayerCastle.getUp())   PlayerCastle.shotCBall(1);
        if(PlayerCastle.getLeft()) PlayerCastle.shotCBall(3);
    }
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if(!bolPause){
            if(hostPosition == "TL"){
                if(newx > 170 && newx < SCREEN_X && newy > 0 && newy < 160){
                    PlayerCastle.setRight();
                    System.out.println("SetRight");
                }else{
                    PlayerCastle.unsetRight();
                    System.out.println("UnsetRight");
                }
                if(newx > 0 && newx < 200 && newy > 150 && newy < SCREEN_Y){
                    PlayerCastle.setDown();
                    System.out.println("Down");
                }else{
                    PlayerCastle.unsetDown();
                }
            }
            if(hostPosition == "TR"){
                System.out.println("-.-");
                if(newx > 0 && newx < SCREEN_X - 170 && newy > 450 && newy < 600){
                    PlayerCastle.setLeft();
                }else{
                    PlayerCastle.unsetLeft();
                }
                if(newx > 600 && newx < 800 && newy > 0 && newy < SCREEN_Y - 170){
                    PlayerCastle.setUp();
                }else{
                    PlayerCastle.unsetUp();
                }
            }
            if(hostPosition == "BL"){
                System.out.println("-.-");
                if(newx > 0 && newx < SCREEN_X - 170 && newy > 450 && newy < 600){
                    PlayerCastle.setLeft();
                }else{
                    PlayerCastle.unsetLeft();
                }
                if(newx > 600 && newx < 800 && newy > 0 && newy < SCREEN_Y - 170){
                    PlayerCastle.setUp();
                }else{
                    PlayerCastle.unsetUp();
                }
            }
            if(hostPosition == "BR"){
                System.out.println("-.-");
                if(newx > 0 && newx < SCREEN_X - 170 && newy > 450 && newy < 600){
                    PlayerCastle.setLeft();
                }else{
                    PlayerCastle.unsetLeft();
                }
                if(newx > 600 && newx < 800 && newy > 0 && newy < SCREEN_Y - 170){
                    PlayerCastle.setUp();
                }else{
                    PlayerCastle.unsetUp();
                }
            }
            //Mouse position
            if(newx > 0 && newx < SCREEN_X) mousex = String.valueOf(newx);
            if(newy > 0 && newy < SCREEN_Y) mousey = String.valueOf(newy);
        }
    }    
    private void renderCannonBalls(Graphics g){
        /*
        CannonBallImpl[] aux = PlayerCastle.getCBalls();
        for (int i = 0; i < PlayerCastle.getNumCBalls(); i++) {
            if(aux[i].isActive()){
                g.draw(aux[i]);
                g.fill(aux[i]);
            }else if(!aux[i].isActive() && aux[i].isExploited() ){
                explt1.draw(aux[i].getCenterX() - aux[i].getRadius(),aux[i].getCenterY() - aux[i].getRadius());
                aux[i].decreaseTimer(0.033f);
                if(aux[i].getTimer() <= 0) {
                    aux[i].setNotExploited();
                    aux[i].resetTimer();
                }
            }
        }
        */
    }
    private void updateCBalls(){
        //CannonBallImpl[] aux = PlayerCastle.getCBalls();    
        //for (int i = 0; i < PlayerCastle.getNumCBalls(); i++) {
        //    if (aux[i].isActive()) {
        //       aux[i].updatePosition();
        //    }
        //}
    }
    private void initExplotionsImg() throws SlickException{
        explt1 = explt2 = explt3 = explt4 = explt5 = explt6 = new Image("Assets/Explosion.png");
                
    }
    @Override
    public int getID() {
        return ID;
    }
    @Override
    public void enter(GameContainer container,StateBasedGame game)throws SlickException{
        this.initCastles(hostPosition);
        //inicializacion de los 4 castillos en funcion de la posicion que elija el jugador
        //Background
        background = new Image("Assets/BackGround.png");
        this.initExplotionsImg();   
        bolPause = false;
    }
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        System.out.println("init");
    }
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if(!bolPause){
            g.setColor(Color.white);
            background.draw(0,0);
            PlayerCastle.drawComponents(g);
            EnemyCastle3.drawComponents(g);
            EnemyCastle1.drawComponents(g);
            EnemyCastle2.drawComponents(g);
            g.drawString("Mouse X: "+mousex + "\nMouse Y: " + mousey, 5 , 25);
            renderCannonBalls(g);   
        }
    }
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(!bolPause){
            updateCBalls();
        }
    }
    
    private void initCastles(String pos) throws SlickException{

    }
    public static void setHostPosition(String pos){
        hostPosition = pos;
    }
}

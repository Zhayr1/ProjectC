/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

import Player.ComputerPlayer;
import Player.Player;
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
    public static PlayerCastle redCastle,blueCastle,greenCastle,yellowCastle;
    public static CannonBallImpl[] redCastleBalls,
                                   blueCastleBalls,
                                   greenCastleBalls,
                                   yellowCastleBalls;
    public static Player player1,player2,player3,player4;
    private static String hostPosition = "";
    private Image background;
    private Image explt1;
    private static boolean bolPause = true; 
    
    
    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            System.exit(0);
	}
        if(key == Input.KEY_UP){
            //PlayerCastle.shotCBall(1);
        }
        if(key == Input.KEY_DOWN){
             //PlayerCastle.shotCBall(2);
        }
        if(key == Input.KEY_LEFT){
             //PlayerCastle.shotCBall(3);
        }
        if(key == Input.KEY_RIGHT){
             //PlayerCastle.shotCBall(4);
        }
    }
    @Override
    public void keyReleased(int key,char c){
        if(key == Input.KEY_UP){
        }
    }
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        castleShot(redCastle);
        castleShot(blueCastle);
        castleShot(yellowCastle);
        castleShot(greenCastle);
        if(player1.getUp())   greenCastle.shotCBall(1);
        //if(PlayerCastle.getLeft()) PlayerCastle.shotCBall(3);
    }
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if(!bolPause){
            renderDirectionArrowsBasedOnMouse(newx , newy , redCastle.getPlayer());
            renderDirectionArrowsBasedOnMouse(newx , newy , blueCastle.getPlayer());
            renderDirectionArrowsBasedOnMouse(newx , newy , yellowCastle.getPlayer());
            renderDirectionArrowsBasedOnMouse(newx , newy , greenCastle.getPlayer());
            //Mouse position
            if(newx > 0 && newx < SCREEN_X) mousex = String.valueOf(newx);
            if(newy > 0 && newy < SCREEN_Y) mousey = String.valueOf(newy);
        }
    }
    private void renderDirectionArrowsBasedOnMouse(int newx , int newy , Player p){
        if(p != null){    
            switch(p.getPosition()){
                    case Player.TL:
                        if(newx > 150 && newx < SCREEN_X && newy > 0 && newy < 150){
                            p.setRight();
                            System.out.println("Set Right");
                        }else{
                            p.unsetRight();
                            System.out.println("Unset Right");
                        }
                        if(newx > 0 && newx < 200 && newy > 150 && newy < SCREEN_Y){
                            p.setDown();
                            System.out.println("Set Down");
                        }else{
                            p.unsetDown();
                            System.out.println("Unset Down");
                        }
                        break;
                    case Player.TR:
                        if(newx > 0 && newx < SCREEN_X - 150 && newy > 0 && newy < 150){
                            p.setLeft();
                        }else{
                            p.unsetLeft();
                        }
                        if(newx > SCREEN_X - 150 && newx < SCREEN_X && newy > 150 && newy < SCREEN_Y){
                            p.setDown();
                        }else{
                            p.unsetDown();
                        }
                        break;
                    case Player.BL:
                        if(newx > 0 && newx < 150 && newy > 0 && newy < SCREEN_Y - 150){
                            p.setUp();
                        }else{
                            p.unsetUp();
                        }
                        if(newx > 150 && newx < SCREEN_X && newy > SCREEN_Y - 150 && newy < SCREEN_Y){
                            p.setRight();
                        }else{
                            p.unsetRight();
                        }                    
                        break;
                    case Player.BR:
                        if(newx > 0 && newx < SCREEN_X - 150 && newy > 450 && newy < SCREEN_Y){
                            p.setLeft();
                        }else{
                            p.unsetLeft();
                        }
                        if(newx > SCREEN_X - 150 && newx < SCREEN_X && newy > 0 && newy < SCREEN_Y - 150){
                            p.setUp();
                        }else{
                            p.unsetUp();
                        }
                        break;
            }      
        }
    }
    private void renderCannonBalls(Graphics g){
        calculateBallStatusForRender(g,redCastleBalls);
        calculateBallStatusForRender(g,blueCastleBalls);
        calculateBallStatusForRender(g,yellowCastleBalls);
        calculateBallStatusForRender(g,greenCastleBalls);
    }
    private void calculateBallStatusForRender(Graphics g,CannonBallImpl[] castleBalls){
        for (int i = 0; i < PlayerCastle.getRound(); i++) {
            if(castleBalls[i].isActive()){
                g.draw(castleBalls[i]);
                g.fill(castleBalls[i]);
            }else if(!castleBalls[i].isActive() && castleBalls[i].isExploited() ){
                explt1.draw(castleBalls[i].getCenterX() - castleBalls[i].getRadius(),
                castleBalls[i].getCenterY() - castleBalls[i].getRadius());
                //Tiempo de la imagen de explosion
                castleBalls[i].decreaseTimer(0.033f);
                if(castleBalls[i].getTimer() <= 0) {
                    castleBalls[i].setNotExploited();
                    castleBalls[i].resetTimer();
                }
            }
        }
    }
    private void updateCBalls(CannonBallImpl[] castleBalls){ 
        for (int i = 0; i < PlayerCastle.getRound(); i++) {
            if(castleBalls[i].isActive()){
                castleBalls[i].updatePosition();
            }
        }
    }
    private void updateCastleBalls(){
            updateCBalls(redCastleBalls);
            updateCBalls(blueCastleBalls);
            updateCBalls(yellowCastleBalls);
            updateCBalls(greenCastleBalls);
    }
    private void initExplotionsImg() throws SlickException{
        explt1 = new Image("Assets/Explosion.png");
                
    }
    @Override
    public int getID() {
        return ID;
    }
    @Override
    public void enter(GameContainer container,StateBasedGame game)throws SlickException{
        System.out.println("Enter stage Game");
        player1 = new Player();
        player1.setPosition(Player.BR);
        greenCastle.setPlayer(player1);
        System.out.println(player1.getPosition());
        /*
        player2 = new ComputerPlayer(Player.TR);
        player3 = new ComputerPlayer(Player.BL);
        player4 = new ComputerPlayer(Player.BR);
        player1.setPosition(Integer.valueOf(hostPosition));
        redCastle.setPlayer(player1);
        blueCastle.setPlayer(player2);
        yellowCastle.setPlayer(player3);
        greenCastle.setPlayer(player4);*/
    }
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        System.out.println("initGameHere!!");
        //inicializacion de los 4 castillos
        this.initCastles();
        //inicializacion de las balas de cañon de los 4 castillos
        this.initCastleBalls();
        //Background
        background = new Image("Assets/BackGround.png");
        this.initExplotionsImg();   
        bolPause = false;        
    }
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
            g.setColor(Color.white);
            background.draw(0,0);
            redCastle.drawComponents(g);
            blueCastle.drawComponents(g);
            yellowCastle.drawComponents(g);
            greenCastle.drawComponents(g);
            g.drawString("Mouse X: "+mousex + "\nMouse Y: " + mousey, 5 , 25);
            renderCannonBalls(g);   
    }
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(!bolPause){
            this.updateCastleBalls();
        }
    }
    private void castleShot(PlayerCastle castle){
        if(castle.getPlayer().getUp())     castle.shotCBall(1);        
        if(castle.getPlayer().getDown())   castle.shotCBall(1);
        if(castle.getPlayer().getLeft())   castle.shotCBall(1);
        if(castle.getPlayer().getRight())  castle.shotCBall(1);
    }
    private void initCastles() throws SlickException{
        redCastle = new PlayerCastle(0, 0, 150, 150, "Assets/RedCastle.png");
        blueCastle = new PlayerCastle(SCREEN_X - 150, 0, 150, 150, "Assets/BlueCastle.png");
        yellowCastle = new PlayerCastle(0, SCREEN_Y - 150, 150, 150, "Assets/YellowCastle.png");
        greenCastle = new PlayerCastle(SCREEN_X - 150, SCREEN_Y - 150, 150, 150, "Assets/GreenCastle.png");
    }
    private void initCastleBalls(){
        redCastleBalls    = redCastle.getCBalls();
        blueCastleBalls   = blueCastle.getCBalls();
        greenCastleBalls  = greenCastle.getCBalls();
        yellowCastleBalls = yellowCastle.getCBalls();
    }
    public static void setHostPosition(String pos){
        hostPosition = pos;
    }
}

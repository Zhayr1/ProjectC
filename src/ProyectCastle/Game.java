/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

import Player.Bot;
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
    public static PlayerCastle[] castles;
    public static CannonBallImpl[] redCastleBalls,
                                   blueCastleBalls,
                                   greenCastleBalls,
                                   yellowCastleBalls;
    public static Player[] players;
    public static int hostPosition = -1;
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
        castleShot(castles[0]);
            }
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if(!bolPause){
            if(!castles[0].getPlayer().isBot()) renderDirectionArrowsBasedOnMouse(newx , newy , castles[0].getPlayer());
            if(!castles[1].getPlayer().isBot()) renderDirectionArrowsBasedOnMouse(newx , newy , castles[1].getPlayer());
            if(!castles[2].getPlayer().isBot()) renderDirectionArrowsBasedOnMouse(newx , newy , castles[2].getPlayer());
            if(!castles[3].getPlayer().isBot()) renderDirectionArrowsBasedOnMouse(newx , newy , castles[3].getPlayer());
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
                        }else{
                            p.unsetRight();
                        }
                        if(newx > 0 && newx < 150 && newy > 150 && newy < SCREEN_Y){
                            p.setDown();
                        }else{
                            p.unsetDown();
                        }
                        if(newx > 150 && newx < SCREEN_X  && newy > 150 && newy < SCREEN_Y ){
                            p.setDownRight();
                        }else{
                            p.unsetDownRight();
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
                        if(newx > 0 && newx < SCREEN_X - 150 && newy > 150 && newy < SCREEN_Y){
                            p.setDownLeft();
                        }else{
                            p.unsetDownLeft();
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
                        if(newx > 150 && newx < SCREEN_X && newy > 0 && newy < SCREEN_Y - 150){
                            p.setUpRight();
                        }else{
                            p.unsetUpRight();
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
                        if(newx > 0 && newx < SCREEN_X - 150 && newy > 0 && newy < SCREEN_Y - 150){
                            p.setUpLeft();
                        }else{
                            p.unsetUpLeft();
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
        this.initPlayers();
        for (int i = 1; i <= 3; i++) {
            Bot b = (Bot) players[i];
            b.initBot();
        }
    }
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //inicializacion de los 4 castillos
        castles = new PlayerCastle[4];
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
            castles[0].drawComponents(g);
            castles[1].drawComponents(g);
            castles[2].drawComponents(g);
            castles[3].drawComponents(g);
            g.drawString("Mouse X: "+mousex + "\nMouse Y: " + mousey, 5 , 25);
            renderCannonBalls(g);   
    }
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(!bolPause){
            this.updateCastleBalls();
            this.destroyCastles(castles[0]);
            this.destroyCastles(castles[1]);
            this.destroyCastles(castles[2]);
            this.destroyCastles(castles[3]);
            castleShot(castles[1]);
            castleShot(castles[2]);
            castleShot(castles[3]);

        }
    }
    private void castleShot(PlayerCastle castle){
        if(castle.getPlayer() != null /*&& !castle.getPlayer().isBot()*/){
            if(castle.getPlayer().getUp())     castle.shotCBall(1);        
            if(castle.getPlayer().getDown())   castle.shotCBall(2);
            if(castle.getPlayer().getLeft())   castle.shotCBall(3);
            if(castle.getPlayer().getRight())  castle.shotCBall(4);
            if(castle.getPlayer().getUpLeft()) castle.shotCBall(CannonBallImpl.UPLEFT);
            if(castle.getPlayer().getUpRight()) castle.shotCBall(CannonBallImpl.UPRIGHT);
            if(castle.getPlayer().getDownLeft()) castle.shotCBall(CannonBallImpl.DOWNLEFT);
            if(castle.getPlayer().getDownRight()) castle.shotCBall(CannonBallImpl.DOWNRIGHT);
        }
    }
    private void initCastles() throws SlickException{
        castles[0] = new PlayerCastle(0, 0, 150, 150, "Assets/RedCastle.png");
        castles[1] = new PlayerCastle(SCREEN_X - 150, 0, 150, 150, "Assets/BlueCastle.png");
        castles[2] = new PlayerCastle(0, SCREEN_Y - 150, 150, 150, "Assets/YellowCastle.png");
        castles[3] = new PlayerCastle(SCREEN_X - 150, SCREEN_Y - 150, 150, 150, "Assets/GreenCastle.png");
    }
    private void initCastleBalls(){
        redCastleBalls    = castles[0].getCBalls();
        blueCastleBalls   = castles[1].getCBalls();
        greenCastleBalls  = castles[2].getCBalls();
        yellowCastleBalls = castles[3].getCBalls();
    }
    private void destroyCastles(PlayerCastle castle){
        if(castle.getHp() <= 0 && !castle.isDestroyed()){
            castle.setDestroyed();
            castle.getImage().setAlpha(0.6f);
        }
        if(castle.getHp() < 0) castle.setHp(0);
    }
    public static void setPlayers(Player[] p){
        players = p;
    }
    private void initPlayers(){
        for (int i = 0; i < 4; i++) {
            if(players[i] != null){
                switch(players[0].getPosition()){
                    case Player.TL:
                        castles[0].setPlayer(players[0]);
                        castles[1].setPlayer(players[1]);
                        castles[2].setPlayer(players[2]);
                        castles[3].setPlayer(players[3]);
                        break;
                    case Player.TR:
                        castles[0].setPlayer(players[1]);
                        castles[1].setPlayer(players[0]);
                        castles[2].setPlayer(players[2]);
                        castles[3].setPlayer(players[3]);
                        break;
                    case Player.BL:
                        castles[0].setPlayer(players[2]);
                        castles[1].setPlayer(players[1]);
                        castles[2].setPlayer(players[0]);
                        castles[3].setPlayer(players[3]);
                        break;
                    case Player.BR:
                        castles[0].setPlayer(players[3]);
                        castles[1].setPlayer(players[1]);
                        castles[2].setPlayer(players[2]);
                        castles[3].setPlayer(players[0]);
                        break;
                }
            }
        }
    }
}

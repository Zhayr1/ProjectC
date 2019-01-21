/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

import Player.Bot;
import Player.Player;
import ProyectCastle.Castles.Castle;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Jesus
 */
public class InitGame extends BasicGameState {
    //ID
    public static final int ID = 1;
    
    //Elementos visuales
    private Image background,selectImg,chooseText,startButton;
    private CastleSelector[] selectors;
    //Selector esquina
    private int hostID;
    //Players
    private Player[] players;
    //colores
    private Color on,off;
    //Strings
    private boolean bolReady;
    //Variables x e y del mouse
    private float mx,my;
    
    
    public InitGame() {
    }
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image("Assets/MenuBackground.png");
        selectImg = new Image("Assets/BlueCastle.png");
        chooseText = new Image("Assets/ChooseText.png");
        startButton = new Image("Assets/StartButton.png");
        startButton.setAlpha(0.8f);
        
        bolReady = false;

        players = new Player[4];
        players[0] = new Player("Zhayr");
        players[1] = new Bot("Loz");
        players[2] = new Bot("Yushu");
        players[3] = new Bot("Synehu");
        
        selectors = new CastleSelector[4];
        selectors[0] = new CastleSelector(20, 50,  Castle.RED,players[0]);
        selectors[1] = new CastleSelector(20, 50, Castle.YELLOW,players[1]);
        selectors[2] = new CastleSelector(20, 50,   Castle.BLUE,players[2]);
        selectors[3] = new CastleSelector(20, 50,    Castle.GREEN,players[3]);
        hostID = players[0].getID();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setAntiAlias(true);
        background.draw(0,0);
        chooseText.draw(100,0);
        startButton.draw(625,500);
        for (int i = 0; i < 4; i++) {
            selectors[i].draw(20, 50 + ((i * 130)), g, players[i].getID());
            if(selectors[i].getPlayer().getID() == hostID){
                selectors[i].drawArrows();
            }
        }
        g.setColor(Color.white);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(bolReady){
            switch(players[0].getPosition()){
                case Player.TL:
                    players[1].setPosition(Player.TL);
                    players[2].setPosition(Player.BL);
                    players[3].setPosition(Player.BR);
                break;
                case Player.TR:
                    players[1].setPosition(Player.TL);
                    players[2].setPosition(Player.BL);
                    players[3].setPosition(Player.BR);
                break;
                case Player.BL:
                    players[1].setPosition(Player.TL);
                    players[2].setPosition(Player.BL);
                    players[3].setPosition(Player.BR);
                break;
                case Player.BR:
                    players[1].setPosition(Player.TL);
                    players[2].setPosition(Player.BL);
                    players[3].setPosition(Player.BR);
                break;
                }
            Game.setPlayers(players);
            game.enterState(MainApplication.Game);
            bolReady = false;
        }
        this.updateMousePosition();
    }
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if( this.isMouseHere(625, 500, 170, 65) ){
            //Nothing
        }
        if(players[0].getID() == hostID){
            this.isSelectorArrowClicked(x, y, selectors[0]);
        }
    }
    @Override
    public void mouseReleased(int button, int x, int y) {
        if( this.isMouseHere(625, 500, 170, 65) ){
            startButton.setAlpha(0.9f);
                for (int i = 0; i < 4; i++) {
                    players[i].setPosition(selectors[i].getIndex());
            }
                Game.hostPosition = selectors[0].getIndex();
                bolReady = true;
        }
    }
    @Override
    public void mousePressed(int button, int x, int y) {
        if( this.isMouseHere(625, 500, 170, 65) ){
            startButton.setAlpha(1f);
        }
    }    
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if( this.isMouseHere(625, 500, 170, 65) ){
            startButton.setAlpha(0.9f);
        }else{
            startButton.setAlpha(0.8f);
        }
        for (int i = 0; i < 4; i++) {
            this.detectCastleSelectClick(newx,newy,selectors[i]);
        }
        
    }
    private boolean isMouseHere(float x1,float y1,float width,float height){
        return mx > x1 && mx < x1+width && my > y1 && my < y1+height;
    }
    private void updateMousePosition(){
        mx = Mouse.getX();
        my = Mouse.getY();
        my = Math.abs( my - Game.SCREEN_Y);
    }
    private void detectCastleSelectClick(float newx,float newy,CastleSelector cs){
        if(cs.getPlayer().getID() == hostID){        
            if(!this.isMouseHere(cs.getX() + cs.getLeftX(), cs.getY()+40, 35, 55)){
                cs.getLeftArrow().setAlpha(0.3f);
            }
            if(!this.isMouseHere(cs.getX() + cs.getRightX(), cs.getY()+40, 35, 55)){
                cs.getRightArrow().setAlpha(0.3f);
            }
            if(this.isMouseHere(cs.getX() + cs.getLeftX(), cs.getY()+40, 35, 55)){
                cs.getLeftArrow().setAlpha(1f);
            }
            if(this.isMouseHere(cs.getX() + cs.getRightX(), cs.getY()+40, 35, 55)){
                cs.getRightArrow().setAlpha(1f);
            }
        }
    }
    private void isSelectorArrowClicked(float x,float y,CastleSelector cs){
        if(cs.getPlayer().getID() == hostID){
            if(this.isMouseHere(cs.getX() + 345, cs.getY()+50, 50, 50)){
                if(cs.getIndex() >= 1){
                    cs.leftArrowClick();
                }
            }
            if(this.isMouseHere(cs.getX() + 445, cs.getY()+50, 50, 50)){
                if(cs.getIndex() < 3){
                    cs.rightArrowClick();
                }
            }
        }        
    }
}

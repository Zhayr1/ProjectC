/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

import ProyectCastle.Castles.Castle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.MouseOverArea;
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
    private Image[] selectArea;
    private CastleSelector select1;
    //Selector esquina
    private int selector;
    //colores
    private Color on,off;
    //Strings
    private String selectedPosition;
    private String castlePos;
    private boolean bolReady;

    public InitGame() {
        this.selectArea = new Image[4];
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
        selector = 0;
        selectedPosition = "";
        selectArea[0] = new Image("Assets/SelectFieldRed.png");
        selectArea[1] = new Image("Assets/SelectFieldBlue.png");
        selectArea[2] = new Image("Assets/SelectFieldYellow.png");
        selectArea[3] = new Image("Assets/SelectFieldGreen.png");
        
        select1 = new CastleSelector(20, 50,Castle.GREEN);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setAntiAlias(true);
        background.draw(0,0);
        chooseText.draw(100,0);
        startButton.draw(625,500);
        //for (int i = 0; i < 4; i++) {
          //  selectArea[i].draw(20,50 + (i*135));
        //}
        select1.draw(20, 50, g);
        g.setColor(Color.white);
        g.drawString(selectedPosition, 10, 50);
        
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(bolReady){
            game.enterState(MainApplication.Game);
            bolReady = false;
        }
        
    }
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if( x >= 50 && x <= (50+170) && y >= 500 && y <= 565 ){
        }
        if(this.isMouseHere(select1.getX() + 345, select1.getY()+50, 50, 50, x, y)){
            select1.leftArrowClick();
        }
        if(this.isMouseHere(select1.getX() + 445, select1.getY()+50, 50, 50, x, y)){
            select1.rightArrowClick();
        }
    }
    @Override
    public void mouseReleased(int button, int x, int y) {
        if( x >= 50 && x <= (50+170) && y >= 500 && y <= 565 ){
            startButton.setAlpha(0.9f);
            if(selectedPosition != ""){
                bolReady = true;
            }
        }
    }
    @Override
    public void mousePressed(int button, int x, int y) {
        if( x >= 50 && x <= (50+170) && y >= 500 && y <= 565 ){
            startButton.setAlpha(1f);
        }
    }    
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if( newx >= 50 && newx <= (50+170) && newy >= 500 && newy <= 565 ){
            startButton.setAlpha(0.9f);
        }else{
            startButton.setAlpha(0.8f);
        }
        if(!this.isMouseHere(select1.getX() + 345, select1.getY()+50, 50, 50, newx, newy)){
            select1.getLeftArrow().setAlpha(0.3f);
        }
        if(!this.isMouseHere(select1.getX() + 445, select1.getY()+50, 50, 50, newx, newy)){
            select1.getRightArrow().setAlpha(0.3f);
        }
        if(this.isMouseHere(select1.getX() + 345, select1.getY()+50, 50, 50, newx, newy)){
            select1.getLeftArrow().setAlpha(1f);
        }
        if(this.isMouseHere(select1.getX() + 445, select1.getY()+50, 50, 50, newx, newy)){
            select1.getRightArrow().setAlpha(1f);
        }        
    }
    private boolean isMouseHere(float x1,float y1,float width,float height,float mx,float my){
        return mx > x1 && mx < x1+width && my > y1 && my < y1+height;
    } 
}

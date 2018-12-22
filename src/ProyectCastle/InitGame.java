/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

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
    private Image background,selectBg,selectImg,chooseText,startButton;
    private final MouseOverArea[] selectArea = new MouseOverArea[4];
    
    //Selector esquina
    private int selector;
    //colores
    private Color on,off;
    //Strings
    private String selectedPosition;
    private String castlePos;
    private boolean bolReady;
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image("Assets/MenuBackground.png");
        selectBg = new Image("Assets/SelectField.png");
        selectImg = new Image("Assets/BlueCastle.png");
        chooseText = new Image("Assets/ChooseText.png");
        startButton = new Image("Assets/StartButton.png");
        startButton.setAlpha(0.8f);
        
        bolReady = false;
        selector = 0;
        selectedPosition = "";
        //Init colors
        on  = new Color(1,1,1,0.9f);
        off = new Color(1,1,1,0.7f);
        for (int i=0;i<2;i++) {
	selectArea[i] = new MouseOverArea(container, selectImg, 300 + (i * 225) , 50, (AbstractComponent source) -> {
            for (int j = 0; j < 4; j++) {
                if(source == selectArea[j]) selector = j;
            }
            switch(selector){
                case 0:
                    selectedPosition = "Top Left";
                    castlePos = "TL";
                    selectArea[0].setNormalColor(on);
                    selectArea[1].setNormalColor(off);
                    selectArea[2].setNormalColor(off);
                    selectArea[3].setNormalColor(off);
                    break;
                case 1:
                    selectedPosition = "Top Right";
                    castlePos = "TR";
                    selectArea[0].setNormalColor(off);
                    selectArea[1].setNormalColor(on);
                    selectArea[2].setNormalColor(off);
                    selectArea[3].setNormalColor(off);
                    break;
                case 2:
                    selectedPosition = "Bot Left";
                    castlePos = "BL";
                    selectArea[0].setNormalColor(off);
                    selectArea[1].setNormalColor(off);
                    selectArea[2].setNormalColor(on);
                    selectArea[3].setNormalColor(off);
                    break;
                case 3:
                    selectedPosition = "Bot Right";
                    castlePos = "BR";
                    selectArea[0].setNormalColor(off);
                    selectArea[1].setNormalColor(off);
                    selectArea[2].setNormalColor(off);
                    selectArea[3].setNormalColor(on);
                    break;
            }
        });
        selectArea[i+2] = new MouseOverArea(container, selectImg, 300 + (i * 225) , 50 + 155 , (AbstractComponent source) -> {
            for (int j = 0; j < 4; j++) {
                if(source == selectArea[j]) selector = j;
            }
            switch(selector){
                case 0:
                    selectedPosition = "Top Left";
                    castlePos = "TL";
                    selectArea[0].setNormalColor(on);
                    selectArea[1].setNormalColor(off);
                    selectArea[2].setNormalColor(off);
                    selectArea[3].setNormalColor(off);
                    break;
                case 1:
                    selectedPosition = "Top Right";
                    castlePos = "TR";
                    selectArea[0].setNormalColor(off);
                    selectArea[1].setNormalColor(on);
                    selectArea[2].setNormalColor(off);
                    selectArea[3].setNormalColor(off);
                    break;
                case 2:
                    selectedPosition = "Bot Left";
                    castlePos = "BL";
                    selectArea[0].setNormalColor(off);
                    selectArea[1].setNormalColor(off);
                    selectArea[2].setNormalColor(on);
                    selectArea[3].setNormalColor(off);
                    break;
                case 3:
                    selectedPosition = "Bot Right";
                    castlePos = "BR";
                    selectArea[0].setNormalColor(off);
                    selectArea[1].setNormalColor(off);
                    selectArea[2].setNormalColor(off);
                    selectArea[3].setNormalColor(on);
                    break;
            }
        });
        selectArea[i].setNormalColor(new Color(1,1,1,0.7f));
        selectArea[i+2].setNormalColor(new Color(1,1,1,0.7f));
	selectArea[i].setMouseOverColor(new Color(1,1,1,0.9f));
        selectArea[i+2].setMouseOverColor(new Color(1,1,1,0.9f));
	}
        
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setAntiAlias(true);
        background.draw(0,0);
        chooseText.draw(250,0);
        selectBg.draw(300,50);
        startButton.draw(50,500);
        for (int i = 0; i < 4; i++) {
            selectArea[i].render(container,g);
        }
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
    }
}

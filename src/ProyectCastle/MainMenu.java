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
public class MainMenu extends BasicGameState{
    public static final int ID = 0;
    //Imagenes del menu
    private Image background,gameTittle;
    private MouseOverArea[] areas = new MouseOverArea[3];
    private Image[] menuImg = new Image[3];

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        menuImg[0] = new Image("Assets/PlayText.png");
        menuImg[1] = new Image("Assets/OptionsText.png");
        menuImg[2] = new Image("Assets/ExitText.png");
        background = new Image("Assets/MenuBackground.png");
        gameTittle = new Image("Assets/GameTittle.png");
        
        for (int i=0;i<3;i++) {
	areas[i] = new MouseOverArea(container, menuImg[i], 300, 250 + (i*75), 300, 90, (AbstractComponent source) -> {
            for (int j = 0; j < 3; j++) {
                if (source == areas[j]) {
                    game.enterState(j + 1);
                }   
            }
        });
        areas[i].setNormalColor(new Color(1,1,1,0.8f));
	areas[i].setMouseOverColor(new Color(1,1,1,0.9f));
	}
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
   	background.draw(0,0);
        gameTittle.draw(100,50);
        for (int i=0;i<3;i++) {
            areas[i].render(container, g);
	}
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }
    
    public void componentActivated(AbstractComponent source) {
        System.out.println("ACTIVL : "+source);
        for (int i=0;i<3;i++) {
            if (source == areas[i]) {
                System.out.println("Presionado "+i);
            }
        }
    }    
}

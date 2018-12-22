/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectCastle;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Jesus
 */
public class MainApplication extends StateBasedGame{
    //ID
    public static final int MainMenu    = 0;
    public static final int InitGame    = 1;
    public static final int OptionsMenu = 2;
    public static final int ExitGame    = 3;
    public static final int Game        = 4;
    
    //Ventana
    public static final int SCREEN_X = 800;
    public static final int SCREEN_Y = 600;
    public static final int FPS = 60;

    public MainApplication(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new menu());
        this.addState(new InitGame());
        this.addState(new OptionMenu());
        this.addState(new Game());
    }
    
    // Main Method
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new MainApplication("Game"));
            app.setDisplayMode(SCREEN_X, SCREEN_Y, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch(SlickException e) {
            System.out.println("Ex: "+e);
        }
    }    
    
}

package ProyectCastle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionMenu extends BasicGameState {
	
	private final float width = 800, height = 600; // tamaño del juego
	private Image bg, optBg, bBtn0, bBtn1;
	private String optTitle = "OPTIONES";
	private String bgUrl = "Assets/menuBg.png", optBgUrl = "Assets/optBg.png";
	private String bBtn0Url = "Assets/volver0.png", bBtn1Url = "Assets/volver1.png";
	private int posBTn;
	private int posX, posY;

	public OptionMenu() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		bg = new Image(bgUrl);
		optBg = new Image(optBgUrl);
		bBtn0 = new Image(bBtn0Url);
		bBtn1 = new Image(bBtn1Url);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		bg.draw(0, 0, width, height);
		optBg.draw(30, 30, width-30*2, height-30*2);
		
		bBtn0.draw(600, 500);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		posX = Mouse.getX();
		posY = -(Mouse.getY()-(int)height);
		
	}

	@Override
	public int getID() {
		return 2;
	}

}

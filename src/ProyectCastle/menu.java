package ProyectCastle;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class menu extends BasicGameState {

	private final float width = 800, height = 600; // tamaño del juego
	private Image title,playBtn,playBtnSelect,optBtn,optBtnSelect,exitBtn,exitBtnSelect,bg; // declarar imagenes
	// ubicaciones
	private String titleUrl = "Assets/title.png", bgUrl = "Assets/menuBg.png";
	private String playUrl = "Assets/play0.png", playUrl1 = "Assets/play1.png";
	private String optUrl = "Assets/options0.png", optUrl1 = "Assets/options1.png";
	private String exitUrl = "Assets/exit0.png", exitUrl1 = "Assets/exit1.png";
	private int posX, posY;
	private int separacion1 = 80, separacion2 = separacion1*2;

	private int posBtnX = 70; // X para dibujar los botones
	private int posBtnY = 330; // Y para dibujar los botones
	private Input ent;
	
	public menu() {
	}


	@Override
	public int getID() {
		return 0;
	}


	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		bg = new Image(bgUrl);
		title = new Image(titleUrl);
		playBtn = new Image(playUrl);
		optBtn = new Image(optUrl);
		exitBtn = new Image(exitUrl);
		
		playBtnSelect = new Image(playUrl1);
		optBtnSelect = new Image(optUrl1);
		exitBtnSelect = new Image(exitUrl1);
		
		ent = container.getInput();
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		bg.draw(0, 0, width, height);
		generarBotones(container, game, g);
		generarCoordenadas(g);
		
	}


	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		posX = Mouse.getX();
		posY = -(Mouse.getY()-(int)height); // Una Maraña con las coordenadas
	}
	
	public void generarBotones(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		title.draw(400-(title.getWidth()/2), 150-(title.getHeight()/2)); // Dibuja el titulo en funcion al centro de la imagen
		
		if((posX > posBtnX && posX < playBtn.getWidth()+posBtnX) && (posY > posBtnY && posY < playBtn.getHeight()+posBtnY)) {
			g.drawString("Play", 500, 400);
			playBtnSelect.draw(posBtnX+10, posBtnY);
			optBtn.draw(posBtnX, posBtnY+separacion1);
			exitBtn.draw(posBtnX, posBtnY+separacion2);
			
			if(ent.isMousePressed(0)) {
				game.enterState(1);
			}
			
		}else if((posX > posBtnX && posX < optBtn.getWidth()+posBtnX) && (posY > posBtnY+separacion1 && posY < optBtn.getHeight()+posBtnY+separacion1)) {
			g.drawString("Options", 500, 400);
			optBtnSelect.draw(posBtnX+10, posBtnY+separacion1);
			playBtn.draw(posBtnX, posBtnY);
			exitBtn.draw(posBtnX, posBtnY+separacion2);
			
			if(ent.isMousePressed(0)) {
				game.enterState(2);
			}
			
		}else if((posX > posBtnX && posX < exitBtn.getWidth()+posBtnX) && (posY > posBtnY+separacion2 && posY < exitBtn.getHeight()+posBtnY+separacion2)) {
			g.drawString("Exit", 500, 400);
			exitBtnSelect.draw(posBtnX+10, posBtnY+separacion2);
			playBtn.draw(posBtnX, posBtnY);
			optBtn.draw(posBtnX, posBtnY+separacion1);
			
			if(ent.isMousePressed(0)) {
				container.exit();
			}
		} else {
			// Dibujar botones
			playBtn.draw(posBtnX, posBtnY);
			optBtn.draw(posBtnX, posBtnY+separacion1);
			exitBtn.draw(posBtnX, posBtnY+separacion2);
			
		}
	}
	
	public void generarCoordenadas(Graphics g) {
		g.drawString(" X= "+posX+" Y= "+posY, 90, 8);
	}

}

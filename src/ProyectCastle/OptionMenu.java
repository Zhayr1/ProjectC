package ProyectCastle;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionMenu extends BasicGameState {
	
	private float width, height; // tamaño del juego
	private Image bg, optBg, bBtn1;
	private String optTitle = "OPTIONES";
	private String bgUrl = "Assets/menuBg.png", optBgUrl = "Assets/optBg.png";
	private String bBtn1Url = "Assets/volver1.png";
	private int margen = 50;
	private int posX, posY;
	private float posBtnVolverX, posBtnVolverY;

	public OptionMenu() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		width = container.getWidth();
		height = container.getHeight();
		bg = new Image(bgUrl);
		optBg = new Image(optBgUrl);
		bBtn1 = new Image(bBtn1Url);
		
		posBtnVolverX = ((width-margen*2)+margen)-bBtn1.getWidth()-30;
		posBtnVolverY = ((height-margen*2)+margen)-bBtn1.getHeight()-30;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		bg.draw(0, 0, width, height);
		optBg.draw(margen, margen, width-margen*2, height-margen*2);
		
		bBtn1.draw(posBtnVolverX, posBtnVolverY);
		bBtn1.setAlpha(0.6f);
		
		g.setColor(Color.black);
		g.drawString(optTitle, margen+80, margen+20);
		videoSettings(container, g);
		audioSettings(container, g);
		otrosSettings(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		posX = Mouse.getX();
		posY = -(Mouse.getY()-(int)height);
		
		if((posX > posBtnVolverX && posX < posBtnVolverX+123) && (posY > posBtnVolverY && posY < posBtnVolverY+45)) {
			bBtn1.setAlpha(1);
			if(container.getInput().isMousePressed(0)) {
				game.enterState(0);
			}
		}
		
	}

	@Override
	public int getID() {
		return 2;
	}
	
	public void videoSettings(GameContainer container, Graphics g) throws SlickException {
		String opciones = "Video:  Resolucion:\n"
						+ "        Pantalla completa:\n"
						+ "        Mostrar FPS: \n";
		String configuracion = "";
		
		configuracion += "["+(int)width+"x"+(int)height+"]\n";
		
		if(container.isFullscreen()) {
			configuracion += "[Si]\n";
		} else {
			configuracion += "[No]\n";
		}
		
		if(container.isShowingFPS()) {
			configuracion += "[Si]";
		} else {
			configuracion += "[No]";
		}
		
		int distancia = 370, altura = 120, alto=16;
		
		dibujarSeccion(100, altura, 400, 100, distancia, opciones, configuracion, g);

		if((posX >= distancia && posX <= distancia+80) && (posY >= altura+15 && posY <= altura+15+alto)) {
			g.drawRect(distancia, altura+15, 80, alto);
			g.drawString("           <", 360, altura+14);
			
			if(container.getInput().isMousePressed(0)) {
				if(width == 800 && height == 600) {
					/**
					 * AUN POR TERMINAR
					 * FALTA ESTABLECER
					 * LA RESOLUCION DE PANTALLA
					 */
				}
			}
			
		} else if ((posX >= distancia && posX <= distancia+35) && (posY >= altura+36 && posY <=altura+36+alto)) {
			g.drawRect(distancia, altura+36, 35, alto);
			g.drawString("     <", 360, altura+35);
			
			if(container.getInput().isMousePressed(0)) {
				if(container.isFullscreen())
					container.setFullscreen(false);
				else
					container.setFullscreen(true);
			}
			
		} else if ((posX >= distancia && posX <= distancia+35) && (posY >= altura+57 && posY <=altura+57+alto)) {
			g.drawRect(distancia, altura+57, 35, alto);
			g.drawString("     <", 360, altura+56);
			
			if(container.getInput().isMousePressed(0)) {
				if(container.isShowingFPS())
					container.setShowFPS(false);
				else
					container.setShowFPS(true);
			}
		}
	}
	
	public void audioSettings(GameContainer container, Graphics g) {
		String opciones = "Audio:  Efectos:\n"
						+ "        Musica:";
		String configuracion = "";
		
		configuracion += "            ["+(int)(container.getMusicVolume()*100f)+"%]\n";
		configuracion += "            ["+(int)(container.getMusicVolume()*100f)+"%]";
		
		dibujarSeccion(100, 230, 400, 70, 290, opciones, configuracion, g);
		audioSlider(250, 290, container, g);
		audioSlider(275, 290, container, g);
	}
	
	public void audioSlider(int y, int distancia, GameContainer container, Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(distancia, y, 100, 5);
		g.setColor(Color.lightGray);
		g.fillRect(distancia+(container.getMusicVolume()*100), y-5, 5, 15);
		
		if((posX >= distancia && posX <= distancia+100) && (posY >= y-7 && posY <= y+15)) {
			g.setColor(Color.white);
			g.drawRect(distancia+(container.getMusicVolume()*100), y-5, 5, 15);
			if(container.getInput().isMouseButtonDown(0)) {
				container.setMusicVolume((posX-distancia)/100f);
			}
		}
	}
	
	public void otrosSettings(Graphics g) {
		String opciones = "Otros:  Idioma:";
		
		String configuracion = "[Español]";
		
		dibujarSeccion(100, 310, 400, 50, 290, opciones, configuracion, g);
		
		if((posX >= 290 && posX <= 290+80) && (posY >=325 && posY <= 325+18)) {
			g.drawRect(290, 325, 80, 18);
			g.drawString("          <", 280, 325);
			
		}
	}
	
	public void dibujarSeccion(int x, int y, int w, int h, int distancia, String opciones, String configuracion, Graphics g) {
		
		g.setColor(Color.darkGray);
		g.fillRect(x, y, w, h);
		
		g.setColor(Color.white);
		g.drawString(opciones, x+20, y+15);
		g.drawString(configuracion, distancia, y+15);
	}

}

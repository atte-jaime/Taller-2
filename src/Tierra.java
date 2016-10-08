import processing.core.PApplet;
import processing.core.PImage;

public class Tierra implements Recogible{

	private PApplet app;
	private int posX, posY;
	private PImage forma;
	private float escala = 1;

	
	public Tierra(int posX, int posY, PImage forma, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.forma=forma;
		this.app=app;
	}

	@Override
	public void pintar() {
		app.image(forma, posX, posY, forma.width/escala, forma.height/escala);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	@Override
	public void setSize(float size) {
		this.escala = size;
	}

}

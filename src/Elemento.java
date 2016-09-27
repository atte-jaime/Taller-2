import processing.core.PApplet;
import processing.core.PShape;

public abstract class Elemento {
	private PApplet app;
	private int posX, posY;
	private PShape forma;

	public Elemento(int posX, int posY, PShape forma, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.forma=forma;
		this.app=app;
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
	
	

}

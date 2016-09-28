import processing.core.PApplet;
import processing.core.PShape;

public class Fuego implements Recogible{
	private PApplet app;
	private int posX, posY;
	private PShape forma;
	private float escala = 1;

	
	public Fuego(int posX, int posY, PShape forma, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		this.forma=forma;
		this.app=app;
	}

	@Override
	public void pintar() {
		app.shape(forma, posX, posY, forma.width/escala, forma.height/escala);
	}

	@Override
	public void pros() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contras() {
		// TODO Auto-generated method stub
		
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

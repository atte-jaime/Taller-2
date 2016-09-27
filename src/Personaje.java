import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Personaje {

	private PApplet app;
	private PShape personaje;
	private PVector pos;
	private PVector vel;
	private PVector ace;
	private LinkedList<Elemento> elementos;
	
	public Personaje(PShape p, PApplet app) {
		this.app=app;
		personaje=p;
		elementos= new LinkedList<Elemento>();
	}

}

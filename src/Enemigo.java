import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public abstract class Enemigo {

	private PApplet app;
	private PImage forma;
	private PVector pos;
	private PVector vel;
	private PVector ace;
	private float velMax;
	private float aceMax;

	public Enemigo(PImage forma, PApplet app) {
		this.app = app;
		this.forma = forma;
		pos = new PVector((int) app.random(0, 1000), (int) app.random(0, 1000));
		vel = new PVector(0, 0);
		ace = new PVector(0, 0);
		velMax = app.random(1,4);
		aceMax = (float) 0.1;
	}
	
	public void actualizar() {
		vel.add(ace);
		vel.limit(velMax);
		pos.add(vel);
		ace.mult(0);
	}
	
	public void aplicarFuerza(PVector fuerza) {
		ace.add(fuerza);
	}

	public void seguirPersonaje(PVector personaje) {
		PVector dist = PVector.sub(personaje, pos);
		float d = dist.mag();
		dist.mag();
		if (d < 100) {
			float m = PApplet.map(d, 0, 100, 0, velMax);
			dist.mult(m);
		} else {
			dist.mult(velMax);
		}

		PVector direccion = PVector.sub(dist, vel);
		direccion.limit(aceMax);
		aplicarFuerza(direccion);

	}

	public void pintarE() {
		float angulo = vel.heading() + PApplet.PI / 2;
		app.pushMatrix();
		app.translate(pos.x, pos.y);
		app.rotate(angulo);
		app.tint(255,100);
		app.image(forma,0,0);
		app.noTint();
		app.popMatrix();
	}
	
	public PVector getPos() {
		return pos;
	}

}

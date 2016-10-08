import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;
import processing.core.PVector;

public class Personaje {

	private PApplet app;
	private PShape personaje;
	private PVector pos;
	private PVector vel;
	private PVector ace;
	private float fuerzaMax;
	private float velMax;
	private float poder;
	private float vida = 250;
	private float tamaño = 1;
	private float resis;
	private LinkedList<Recogible> elementos;

	public Personaje(PShape p, PApplet app) {
		this.app = app;
		vel = new PVector(0, 0);
		ace = new PVector(0, 0);
		pos = new PVector(app.width / 2, app.height / 2);
		fuerzaMax = (float) 0.1;
		velMax = 5;
		personaje = p;
		personaje.disableStyle();
		elementos = new LinkedList<Recogible>();
		app.shapeMode(PApplet.CENTER);
	}

	public void actualizar() {
		vel.add(ace);
		vel.limit(velMax);
		pos.add(vel);
		ace.mult(0);
	}

	private void aplicarFuerza(PVector fuerza) {
		ace.add(fuerza);
	}

	public void perseguir() {
		PVector objetivo = new PVector(app.mouseX, app.mouseY);
		PVector distancia = PVector.sub(objetivo, pos);
		float d = distancia.mag();
		distancia.normalize();
		if (d < 100) {
			float m = PApplet.map(d, 0, 100, 0, velMax);
			distancia.mult(m);
		} else {
			distancia.mult(velMax);
		}

		PVector direccion = PVector.sub(distancia, vel);
		direccion.limit(fuerzaMax);
		aplicarFuerza(direccion);
	}

	public void pintar() {
		float angulo = vel.heading() + PApplet.PI / 2;
		app.fill(255, 200, 200);
		app.noStroke();
		app.pushMatrix();
		app.translate(pos.x, pos.y);
		app.rotate(angulo);
		app.shape(personaje, 0, 0, personaje.width / tamaño, personaje.height / tamaño);
		app.popMatrix();
		app.fill(255);
		app.textSize(25);
		app.text("HEALTH", 20, 20);
		app.fill(255, 100);
		app.rect(140, 20, 250, 22);
		app.fill(0, 255, 200);
		app.rect(140, 20, vida, 22);
	}

	public PVector getPos() {
		return pos;
	}

	public void pintarElementos() {
		for (int i = 0; i < elementos.size(); i++) {
			Recogible e = elementos.get(i);
			float x = PApplet.cos(PApplet.radians((360 / elementos.size()) * i)) * 70 + pos.x;
			float y = PApplet.sin(PApplet.radians((360 / elementos.size()) * i)) * 70 + pos.y;
			e.setSize(2);
			e.setPosX((int) x);
			e.setPosY((int) y);
			e.pintar();
			System.out.println("Elemento # " + i + " ángulo: " + ((360 / elementos.size()) * i));

		}
	}

	public void agregarElemento(Recogible e) {
		elementos.add(e);
	}

	public void eliminarElemento() {
		if (elementos.size() > 0) {
			elementos.removeFirst();
		}
	}

	public boolean validarDist(int x, int y) {
		if (PApplet.dist(x, y, pos.x, pos.y) < 50) {
			return true;
		} else {
			return false;
		}
	}

	public LinkedList<Recogible> getElementos() {
		return elementos;
	}

	public float getVelMax() {
		return velMax;
	}

	public float getVida() {
		return vida;
	}

	public float getTamaño() {
		return tamaño;
	}

	public float getResis() {
		return resis;
	}

	public float getPoder() {
		return poder;
	}

	public void setPoder(float poder) {
		this.poder += poder;
	}

	public void setVelMax(float velMax) {
		if (this.velMax + (velMax) > 0 && this.velMax + (velMax) < 15) {
			this.velMax += velMax;
		}
	}

	public void setVida(float vida) {
		if (this.vida + (vida) < 250 && this.vida + (vida) > 0) {
			this.vida += vida;
		}
	}

	public void setTamaño(float tamaño) {
		if (this.tamaño + (tamaño) > 0.5 && this.tamaño + (tamaño) < 3) {
			this.tamaño += tamaño;
		}
	}

	public void setResis(float resis) {
		this.resis += resis;
	}

	public void setElementos(LinkedList<Recogible> elementos) {
		this.elementos = elementos;
	}
}

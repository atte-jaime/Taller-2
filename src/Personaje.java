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
	private float fuerzaMax;
	private float velMax;
	private float poder;
	private float vida;
	private float tamaño;
	private float resis;
	private LinkedList<Recogible> elementos;

	public Personaje(PShape p, PApplet app) {
		this.app = app;
		vel = new PVector(0, 0);
		ace = new PVector(0, 0);
		pos = new PVector(app.width / 2, app.height / 2);
		fuerzaMax = (float) 0.1;
		velMax = 4;
		personaje = p;
		personaje.disableStyle();
		elementos = new LinkedList<Recogible>();
	}

	public void actualizar() {
		vel.add(ace);
		vel.limit(velMax);
		pos.add(vel);
		ace.mult(0);
	}

	void aplicarFuerza(PVector fuerza) {
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
		app.shape(personaje);
		app.popMatrix();
	}

	public void pintarElementos() {
		float angulo = vel.heading() + PApplet.PI / 2;
		app.fill(255);
		app.noStroke();
		app.translate(pos.x, pos.y);
		app.pushMatrix();
		app.rotate(angulo);
		for (int i = 0; i < elementos.size(); i++) {
			Recogible e = elementos.get(i);
			e.pintar();
			e.setSize((float) 1.5);
			if (i < 2) {
				e.setPosX(-15 + (i % 2 * 30));
				e.setPosY(40);
			} else if (i >= 2 && i < 4) {
				e.setPosX(-15 + (i % 2 * 30));
				e.setPosY(80);
			} else if (i >= 4 && i < 6) {
				e.setPosX(-15 + (i % 2 * 30));
				e.setPosY(120);
			} else if (i >= 6 && i < 8) {
				e.setPosX(-15 + (i % 2 * 30));
				e.setPosY(160);
			} else if (i >= 8 && i < 10) {
				e.setPosX(-15 + (i % 2 * 30));
				e.setPosY(200);
			} else if (i >= 10 && i < 12) {
				e.setPosX(-15 + (i % 2 * 30));
				e.setPosY(240);
			}
		}
		app.popMatrix();

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
}

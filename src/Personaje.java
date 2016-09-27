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
	private float r;
	private LinkedList<Elemento> elementos;

	public Personaje(PShape p, PApplet app) {
		this.app = app;
		vel = new PVector(0, 0);
		ace = new PVector(0, 0);
		pos = new PVector(app.width / 2, app.height / 2);
		r = 3;
		fuerzaMax = (float) 0.1;
		velMax = 4;
		personaje = p;
		personaje.disableStyle();
		elementos = new LinkedList<Elemento>();
	}
	
	public void actualizar(){
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
		    PVector deseado = PVector.sub(objetivo,pos);
		    float d = deseado.mag();
		    deseado.normalize();
		    if (d < 100) {
		      float m = PApplet.map(d,0,100,0,velMax);
		      deseado.mult(m);
		    } else {
		      deseado.mult(velMax);
		    }

		    PVector direccion = PVector.sub(deseado,vel);
		    direccion.limit(fuerzaMax);
		    aplicarFuerza(direccion);
		  }
	
	public void pintar() {
		float angulo = vel.heading() + PApplet.PI/2;
	    app.fill(175);
	    app.noStroke();
	    app.pushMatrix();
	    app.translate(pos.x,pos.y);
	    app.rotate(angulo);
		app.shape(personaje);
	    app.popMatrix();
	}
	
	public void llegada(){
		
	}
}

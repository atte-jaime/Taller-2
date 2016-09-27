import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class Logica {
	private PApplet app;
	private int contadorP;
	private int posY, posY2;
	private float opacidad = 255;
	private boolean animacionInicio, cambioP, cambioPatras;
	private PImage[] inicio = new PImage[2];
	private PImage[] pag = new PImage[2];
	private PImage panelO= new PImage();
	private PShape[] elementos= new PShape[5];
	private PShape personaje = new PShape();
	private PShape enemigo = new PShape();
	
	Logica(PApplet app) {
		this.app = app;
		panelO= app.loadImage("../data/Interfaz/panelOrganizar.png");
		inicio[0] = app.loadImage("../data/Interfaz/Inicio1.png");
		inicio[1] = app.loadImage("../data/Interfaz/Inicio2.png");
		pag[0] = app.loadImage("../data/Interfaz/pag2.png");
		pag[1] = app.loadImage("../data/Interfaz/pag3.png");
		personaje= app.loadShape("../data/Personaje/Personaje.svg");
		enemigo= app.loadShape("../data/Personaje/Enemigo.svg");
		for (int i = 0; i < 5; i++) {
			elementos[i]= app.loadShape("../data/Elementos/Elemento"+i+"-09.svg");
		}
		
	}

	public void pintar() {
		pantallas();
	}

	public void pantallas() {
		switch (contadorP) {
		case 0:

			if (animacionInicio) {
				posY -= 10;
				posY2 += 10;
			}
			app.image(inicio[0], 0, posY);
			app.image(inicio[1], 0, posY2);

			if (posY2 > app.height && posY < 0 - app.height) {
				contadorP = 1;
			}
			break;

		case 1:
			if (opacidad > 0) {
				opacidad -= 5;
			}
			app.image(pag[0], 0, 0);
			app.fill(29, 29, 27, opacidad);
			app.rect(0, 0, app.width, app.height);
			if (cambioP) {
				contadorP += 1;
				cambioP = false;
			}

			break;

		case 2:
			app.image(pag[1], 0, 0);
			if (cambioP) {
				contadorP += 1;
				cambioP = false;
			}
			if (cambioPatras) {
				contadorP -= 1;
				cambioPatras = false;
			}
			break;

		case 3:
			fondo();
			ejecutar();
			app.image(panelO, 0, 0);
			break;

		default:
			break;
		}
	}

	public void fondo() {
		app.stroke(255, 0, 0, 100);
		for (int i = 0; i < 12; i++) {
			app.line(0, 0 + (91 * i), app.width, 0 + (91 * i));
			app.line(0 + (90 * i), 0, 0 + (90 * i), app.height);
		}
	}

	public void ejecutar() {

	}

	public void pressed() {
		cambioPantalla();
	}

	public void cambioPantalla() {
		if (app.mouseX < 950 && app.mouseX > 890 && app.mouseY > 458 && app.mouseY < 517
				&& (contadorP == 1 || contadorP == 2)) {
			cambioP = true;
		}
		if (app.mouseX < 103 && app.mouseX > 43 && app.mouseY > 458 && app.mouseY < 517 && contadorP == 2) {
			cambioPatras = true;
		}
	}

	public void key() {
		if (app.key == ' ' && contadorP==0) {
			animacionInicio = true;
		}
	}
}

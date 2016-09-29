import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PShape;

public class Logica {
	private PApplet app;
	private int contadorP = 3;
	private int posY, posY2;
	private float opacidad = 255;
	private boolean animacionInicio, cambioP, cambioPatras;
	private PFont fuente;
	private PImage[] inicio = new PImage[2];
	private PImage[] pag = new PImage[2];
	private PImage panelO = new PImage();
	private PShape[] elementos = new PShape[5];
	private PShape personaje = new PShape();
	private PShape enemigo = new PShape();
	private Personaje per;
	private ArrayList<Recogible> objetos;

	Logica(PApplet app) {
		this.app = app;
		fuente = app.createFont("../data/Fuente/MUSEOSANS_100-WEBFONT.TTF", 32);
		app.textFont(fuente);
		app.textAlign(PApplet.LEFT, PApplet.TOP);

		panelO = app.loadImage("../data/Interfaz/panelOrganizar.png");
		inicio[0] = app.loadImage("../data/Interfaz/Inicio1.png");
		inicio[1] = app.loadImage("../data/Interfaz/Inicio2.png");
		pag[0] = app.loadImage("../data/Interfaz/pag2.png");
		pag[1] = app.loadImage("../data/Interfaz/pag3.png");
		personaje = app.loadShape("../data/Personaje/Personaje.svg");
		enemigo = app.loadShape("../data/Personaje/Enemigo.svg");
		objetos = new ArrayList<Recogible>();
		for (int i = 0; i < 5; i++) {
			elementos[i] = app.loadShape("../data/Elementos/Elemento" + i + "-09.svg");
		}

		for (int i = 0; i < 10; i++) {
			int x = (int) app.random(5);

			switch (x) {
			case 0:
				Agua a = new Agua((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[2], app);
				objetos.add(a);
				break;

			case 1:
				Fuego f = new Fuego((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[0], app);
				objetos.add(f);
				break;

			case 2:
				Aire aire = new Aire((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[1],
						app);
				objetos.add(aire);
				break;

			case 3:
				Tierra t = new Tierra((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[3],
						app);
				objetos.add(t);
				break;

			case 4:
				Energia e = new Energia((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[4],
						app);
				objetos.add(e);
				break;

			default:
				break;
			}
		}

		per = new Personaje(personaje, app);

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
		for (int i = 0; i < objetos.size(); i++) {
			Recogible o = objetos.get(i);
			o.pintar();

			if (per.validarDist(o.getPosX(), o.getPosY()) == true && per.getElementos().size() < 10) {
				if (o instanceof Tierra) {
					per.agregarElemento(o);
					per.setVida(10);
					per.setVelMax((float) -0.5);
					objetos.remove(o);
				} else if (o instanceof Agua) {
					per.agregarElemento(o);
					per.setPoder(-1);
					per.setVelMax((float) 0.5);
					objetos.remove(o);
				} else if (o instanceof Fuego) {
					per.agregarElemento(o);
					per.setPoder(1);
					per.setResis((float) -0.5);
					objetos.remove(o);
				} else if (o instanceof Energia) {
					per.agregarElemento(o);
					per.setVida(-10);
					per.setTamaño((float) -0.05);
					objetos.remove(o);
				} else if (o instanceof Aire) {
					per.agregarElemento(o);
					per.setResis(10);
					per.setTamaño((float)0.05);
					objetos.remove(o);
				}

			}
		}
		crearNuevosE();
		per.actualizar();
		per.perseguir();
		per.pintarElementos();
		per.pintar();

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
		if (app.key == ' ') {
			if (contadorP == 0) {
				animacionInicio = true;
			} else if (contadorP == 3) {
				per.eliminarElemento();
			}
		}
		System.out.println(objetos.size());
	}

	public void crearNuevosE() {
		if (objetos.size() < 3) {
			int x = (int) app.random(5);

			switch (x) {
			case 0:
				Agua a = new Agua((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[2], app);
				objetos.add(a);
				break;

			case 1:
				Fuego f = new Fuego((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[0], app);
				objetos.add(f);
				break;

			case 2:
				Aire aire = new Aire((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[1],
						app);
				objetos.add(aire);
				break;

			case 3:
				Tierra t = new Tierra((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[3],
						app);
				objetos.add(t);
				break;

			case 4:
				Energia e = new Energia((int) app.random(50, app.width - 50), (int) app.random(50, 400), elementos[4],
						app);
				objetos.add(e);
				break;

			default:
				break;
			}
		}
	}
}

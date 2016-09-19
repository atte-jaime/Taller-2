import java.util.Locale;

import processing.core.PApplet;

public class Main extends PApplet {

	public static void main(String[] args) {
		Main.main("MAIN");
	}

	Logica app;

	public void settings() {
		size(1000, 600);
	}

	public void setup() {
		app = new Logica(this);
	}

	public void draw() {
		background(0);
		app.pintar();
	}

	public void mousePressed() {
		app.pressed();
	}

	public void keyPressed() {
		app.key();
	}

}

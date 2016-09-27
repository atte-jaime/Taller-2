import java.util.Locale;

import processing.core.PApplet;

public class Main extends PApplet {

	public static void main(String[] args) {
		Main.main("Main");
	}

	Logica app;

	public void settings() {
		size(1000, 600);
	}

	public void setup() {
		app = new Logica(this);
		shapeMode(CENTER);
	}

	public void draw() {
		background(29, 29, 27);
		app.pintar();
	}

	public void mousePressed() {
		app.pressed();
	}

	public void keyPressed() {
		app.key();
		if(key==ESC) exit();
	}

}

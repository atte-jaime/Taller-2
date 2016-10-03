import processing.core.PVector;

public interface Enemigos {

	public void seguirPersonaje(PVector personaje);

	public void pintarE();

	public void actualizar();

	public void aplicarFuerza(PVector fuerza);

	public PVector getPos();

}

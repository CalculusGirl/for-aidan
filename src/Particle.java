import java.awt.Color;
import java.awt.Graphics;

public class Particle {

	private Color color;
	private double[] pos;
	private double[] vel;
	private double[] acc;
	
	public Particle(double x, double y, Color color) {
		pos = new double[] {x, y};
		acc = new double[] {0, 0};
		this.color = color;
	}

	public double[] getPos() {
		return pos;
	}
	
	public void setVel(double[] vel) {
		this.vel = vel.clone();
	}
	
	public boolean hasExploded() {
		return vel[1] >= 0;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void apply(double[] force) {
		acc = force.clone();
	}
	
	public void update() {
		vel[1] += acc[1];
		pos[0] += vel[0];
		pos[1] += vel[1];
	}
	
	public void show(Graphics g) {
		g.setColor(color);
		g.fillRect((int) pos[0], (int) pos[1], 5, 5);
	}
}
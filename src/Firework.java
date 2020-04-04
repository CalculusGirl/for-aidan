import java.awt.*;
import java.util.*;

public class Firework {
	
	private Particle particle;
	private ArrayList<ExplodedParticle> particles;
	
	public Firework(Color[] colors) {
		int height = Constants.SCREEN_HEIGHT, width = Constants.SCREEN_WIDTH;
		particle = new Particle(random(width), height, colors[(int)(random(colors.length))]);
		particle.setVel(new double[] {0, random(-height/30, -height/60)});
		particles = new ArrayList<ExplodedParticle>();
	}
	
	public void simulate(Graphics g) {
		if (particle != null) {
			particle.apply(Constants.GRAVITY);
			particle.update();
			particle.show(g); 
			if (particle.hasExploded()) {
				explode(); 
				particle = null;
			}
			return;
		}
		for (int i = particles.size() - 1; i >= 0; --i) {
			particles.get(i).apply(Constants.GRAVITY);
			particles.get(i).update();
			particles.get(i).show(g);
			if (particles.get(i).isFinished()) particles.remove(i);
		}
	}
	
	public void explode() {
		double[] pos = particle.getPos().clone();
		int num = 100;
		Heart heart = new Heart(random(0.1f, 0.3f), num);
		for (int i = 0; i < num; ++i) {
			particles.add(new ExplodedParticle(pos, particle.getColor(), heart.getPoints()[i]));
		}
	}
	
	public double random(double min, double max) {
		return min + (Math.random() * (max - min));
	}
	
	public double random(double max) {
		return Math.random() * max;
	}
	
	public boolean isFinished() {
		return particle == null && particles.isEmpty();
	}
	
}
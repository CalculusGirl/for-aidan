import java.awt.*;

public class ExplodedParticle extends Particle {

	private int alpha;
	
	public ExplodedParticle(double[] pos, Color color, double[] vel) {
		super(pos[0], pos[1], color);
		setVel(vel);
		alpha = 255;
	}
	
	public void update() {
		alpha -= 10;
		super.update();
	}
	
	public void show(Graphics g) {
		try {
			g.setColor(new Color(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), alpha));
			g.fillRect((int) getPos()[0], (int) getPos()[1], 3, 3);
		} catch (Exception e) {} 
	}
	
	public boolean isFinished() {
		return alpha <= 0;
	}	
}
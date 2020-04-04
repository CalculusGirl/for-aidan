import java.awt.Color;
import java.awt.Graphics;

public class Heart {
	
	private double[][] points;
	private int[] center;
	private int n;
	
	public Heart(double scale, int[] center, int n) {
		this.n = n;
		this.points = new double[n][2];
		this.center = center;
		double t = 0;
		double dt = 2*Math.PI / n;
		for (int i = 0; i < n; ++i) {
			points[i] = new double[] {x(t) * scale + this.center[0], y(t) * scale + this.center[1]};
			t += dt;
		}
	}

	public Heart(double scale, int n) {
		this(scale, new int[] {0, 0}, n);
	}

	public double[][] getPoints() {
		return points;
	}
	
	public double x(double t) {
		return (16 * Math.pow(Math.sin(t), 3));
	}
	
	public double y(double t) {
		return -(13*Math.cos(t) - 5*Math.cos(2*t) - 2*Math.cos(3*t) - Math.cos(4*t)); 
	}
	
	public void paint(Graphics g, Color color) {
		g.setColor(color); // Color of the heart
		for (int i = 0; i < n; ++i) {
			g.drawLine(this.center[0], this.center[1], ((int) points[i][0]), ((int) points[i][1])); 
		}
	}
}
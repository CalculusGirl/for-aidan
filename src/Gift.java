import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Gift {
	abstract class Component extends JComponent {
		private static final long serialVersionUID = 1L;
		protected final Color[] colors = new Color[] {
				color(225, 215, 245), 
				color(205, 175, 255), 
				color(190, 150, 255), 
				color(155, 100, 255), 
				color(255, 75, 105),
				color(255, 150, 180), 
				color(175, 255, 255), 
				color(155, 255, 220), 
		};
		protected int width, height;
		Component() {
			this.width = Constants.SCREEN_WIDTH;
			this.height = Constants.SCREEN_HEIGHT;
			setup();
			Thread thread = new Thread(new Runnable() {
				public void run() {
					do {
						repaint();
						try {Thread.sleep(50);} catch (IllegalArgumentException iae) {break;} catch (Exception e) {} 
					} while (true);
				}
			});
			thread.start();
		}
		protected abstract void setup();
		protected abstract void draw(Graphics g);
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			draw(g);
		}
		protected Color color(int r, int g, int b) {
			return new Color(r, g, b);
		}
	}
	class Frame extends JFrame {
		private static final long serialVersionUID = 1L;
		Frame() {
			super("a gift for my favorite person"); // Sets title
			setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
			setResizable(false); // No resizing
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		}
		void set(Component comp) {
			super.setContentPane(comp);
			super.setVisible(true);
		}

	}
	public void create() {
		long start = System.currentTimeMillis();
		(new Frame()).set(new Component() {
			private static final long serialVersionUID = 1L;
			private ArrayList<Firework> fireworks; 
			protected void setup() {
				fireworks = new ArrayList<Firework>();
			}
			protected void draw(Graphics g) {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, width, height);
				fireworks(g);
				if (fireworks.size() == 0 && System.currentTimeMillis() - start > Constants.FIREWORKS_TIME) {
					printHappyBirthday(g);
				}
			}
			void fireworks(Graphics g) {
				if (Math.random() <= 0.75 && System.currentTimeMillis() - start <= Constants.FIREWORKS_TIME) {
					fireworks.add(new Firework(colors));
				}
				for (int i = fireworks.size() - 1; i >= 0; --i) {
					fireworks.get(i).simulate(g); // Displays fireworks
					if (fireworks.get(i).isFinished()) fireworks.remove(i);
				}
			}
			void printHappyBirthday(Graphics g) {	
				g.setColor(Constants.GREEN); // Font color
				int fontSize = 42;
				g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, fontSize));
				String str1 = "Happy birthday, my love!";
				int x1 = (Constants.SCREEN_WIDTH - g.getFontMetrics().stringWidth(str1)) / 2;
				int y1 = (Constants.SCREEN_HEIGHT) / 2 - fontSize;
				g.drawString(str1, x1, y1);
				String str2 = "-your favorite CS major";
				int x2 = (Constants.SCREEN_WIDTH - g.getFontMetrics().stringWidth(str2)) / 2;
				int y2 = (Constants.SCREEN_HEIGHT) / 2;
				g.drawString(str2, x2, y2);
				paintHearts(g);
			}
			void paintHearts(Graphics g) {
				double time = (System.currentTimeMillis() - start)/1000.0;
				double frequency = 1/0.6;
				double scale = (int) (time*frequency) % 2 == 0 ? 3 : 4.5;
				int n = 1000;
				int yOffset = 20;
				Heart[][] hearts = new Heart[2][2];
				for (int row = 0; row < hearts.length; ++row) {
					for (int col = 0; col < hearts[row].length; ++col) {
						int[] center = {(col+1)*(col+1)*Constants.SCREEN_WIDTH/5, (row+1)*(row+1)*Constants.SCREEN_HEIGHT/5-yOffset};
						hearts[row][col] = new Heart(scale, center, n);
						hearts[row][col].paint(g, Constants.RED);
					}
				}
			}
		});
	}
	public static void run() {
		(new Gift()).create();
	}
}
package rumple;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import rumple.graphics.Screen;
import rumple.input.Keyboard;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static int width = 300;
    public static int height = width * 16 / 9;
    public static int scale = 3;
    public boolean running = false;
    private Screen screen;
    private JFrame frame;
    private Thread thread;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Keyboard key;
    int xOff = 0, yOff = 0;
    
    
    public Game() {
	Dimension size = new Dimension(width * scale, height * scale);
	setPreferredSize(size);

	screen = new Screen(width, height);
	key = new Keyboard();
	frame = new JFrame();
	
	addKeyListener(key);
    }

    public synchronized void staart() {
	running = true;
	thread = new Thread(this, "Display");
	thread.start();
    }

    public synchronized void stop() {
	try {
	    thread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void run() {
	long lastTime = System.nanoTime();
	long timer = System.currentTimeMillis();
	final double ns = 1000000000.0 / 60.0;
	double delta = 0;
	int frames = 0;
	int updates = 0;
	requestFocus();
	while (running) {
	    long currentTime = System.nanoTime();
	    delta += (currentTime - lastTime) / ns;
	    lastTime = currentTime;
	    while (delta >= 1) {
		update();
		updates++;
		delta--;
	    }
	    render();
	    frames++;

	    if (System.currentTimeMillis() - timer > 1000) {
		timer += 1000;
		frame.setTitle("Rumple\t"+ updates + " ups, " + frames + " fps");
		updates=0;
		frames=0;
	    }
	}
	stop();
    }
    
    
    public void update() {
	key.update();
	if(key.up){
	    yOff--;
	}
	if (key.down){
	    yOff++;
	}
	if(key.left){
	   xOff--;
	}
	if(key.right){
	    xOff++;
	}
    }

    public void render() {
	BufferStrategy bs = getBufferStrategy();
	if (bs == null) {
	    createBufferStrategy(3);
	    return;
	}
	screen.clear();
	screen.render(xOff, yOff);

	for (int i = 0; i < pixels.length; i++) {
	    this.pixels[i] = screen.pixels[i];
	}

	Graphics g = bs.getDrawGraphics();
	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	g.dispose();
	bs.show();
    }

    public static void main(String args[]) {
	Game game = new Game();
	game.frame.setResizable(false);
	game.frame.setTitle("Rumple");
	game.frame.add(game);
	game.frame.pack();
	game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	game.frame.setLocationRelativeTo(null);
	game.frame.setVisible(true);

	game.staart();
    }
}
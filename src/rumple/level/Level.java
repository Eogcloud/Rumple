package rumple.level;

import rumple.graphics.Screen;
import rumple.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tiles;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
	}

	private void generateLevel() {

	}

	private void loadLevel(String path) {

	}

	private void time() {

	}

	public void update() {

	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 5;
		int x1 = (xScroll + screen.width) >> 5;
		int y0 = yScroll >> 5;
		int y1 = (yScroll + screen.width) >> 5;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x,  y, screen);;
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (tiles[x + y * width] == 0)
			return Tile.dungoenCobble;
		return Tile.voidTile;
	}

}

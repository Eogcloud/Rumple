package rumple.graphics;

import java.util.Random;

import rumple.level.tile.Tile;

public class Screen {

    private int width, height;
    public int pixels[];
    public final int MAP_SIZE = 64;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    private Random random = new Random();

    public Screen(int width, int height) {
	this.width = width;
	this.height = height;
	pixels = new int[width * height];

	for (int i = 0; i < tiles.length; i++) {
	    tiles[i] = random.nextInt(0xffffff);
	}

    }

    public void clear() {
	for (int i = 0; i < pixels.length; i++) {
	    pixels[i] = 0;
	}
    }

    /*
     * public void render(int xOffset, int yOffset) { for (int y = 0; y <
     * height; y++) { int yp = y + yOffset; if (yp < 0 || yp >= height)
     * continue; for (int x = 0; x < width; x++) { int xp = x + xOffset; if (xp
     * < 0 || xp >= width) continue; pixels[xp + yp * width] =
     * Sprite.DungeonCobble.pixels[(x & 31) + (y & 31) *
     * Sprite.DungeonCobble.SIZE]; } } }
     */

    public void renderTile(int xp, int yp, Tile tile) {
	for (int y = 0; y < tile.sprite.SIZE; y++) {
	    int ya = y + yp;
	    for (int x = 0; x < tile.sprite.SIZE; x++) {
		int xa = x + xp;
		if (xa < 0 || xa >= width || ya < 0 || ya >= width)
		    break;
		pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
	    }
	}
    }

}
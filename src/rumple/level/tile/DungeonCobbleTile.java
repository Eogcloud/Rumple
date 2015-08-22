package rumple.level.tile;

import rumple.graphics.Screen;
import rumple.graphics.Sprite;

public class DungeonCobbleTile extends Tile {

	public DungeonCobbleTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}

package rumple.graphics;

import java.awt.Color;

public class Sprite {
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite dungeonCobble = new Sprite(16, 0, 0, SpriteSheet.dungoen_tiles);
	public static Sprite voidSprite = new Sprite(16, 0);	
	public static Sprite player_down	=	new Sprite(32, 0, 7, SpriteSheet.dungoen_tiles);
	public static Sprite player_left	=	new Sprite(32, 0, 5, SpriteSheet.dungoen_tiles);
	public static Sprite player_right	=	new Sprite(32, 0, 6, SpriteSheet.dungoen_tiles);
	public static Sprite player_up		=	new Sprite(32, 0, 4, SpriteSheet.dungoen_tiles);
	public static Sprite player_down_2	=	new Sprite(32, 1, 7, SpriteSheet.dungoen_tiles);
	public static Sprite player_left_2	=	new Sprite(32, 1, 5, SpriteSheet.dungoen_tiles);
	public static Sprite player_right_2	=	new Sprite(32, 1, 6, SpriteSheet.dungoen_tiles);
	public static Sprite player_up_2	=	new Sprite(32, 1, 4, SpriteSheet.dungoen_tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int color){
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.sheet = sheet;
		SetColor(color);
	}
	
	private void SetColor(int color){
		for(int i=0; i<SIZE*SIZE; i++){
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}

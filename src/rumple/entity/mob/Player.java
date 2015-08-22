package rumple.entity.mob;

import rumple.graphics.Screen;
import rumple.input.Keyboard;

public class Player extends Mob {

	private Keyboard input;

	public Player(Keyboard input) {
		this.input = input;
	}

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void update() {
		int xa = 0, ya = 0;
		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;

		if (xa != 0 | ya != 0) {
			move(xa, ya);
		}
	}

	public void render(Screen screen) {
		if(dir==0)
			sprite = sprite.player_down;
		if(dir==1)
			sprite = sprite.player_right;
		if(dir==2)
			sprite = sprite.player_up;
		if(dir==3)
			sprite = sprite.player_left;
		
		screen.renderplayer(x-16, y-16, sprite);
		
	}
}

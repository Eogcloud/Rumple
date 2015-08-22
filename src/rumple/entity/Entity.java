package rumple.entity;

import java.util.Random;

import rumple.graphics.Screen;
import rumple.level.Level;

public class Entity {

	public int x, y;
	public boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update(){
		
	}
	
	public void render(Screen screen){
	
	}
	
	public void remove(){
		//remove from level
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
}

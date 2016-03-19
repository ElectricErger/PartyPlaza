package boardPlay;

import org.newdawn.slick.*;
public class Pieces {
	SpriteSheet sprites;
	int[] location;
	int currentTile;
	Image avatar;
	
	public int getCurrentTile(){return currentTile;}
	public void setCurrentTile(int id){currentTile=id;}

}

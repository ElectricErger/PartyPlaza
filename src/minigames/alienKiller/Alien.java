package alienKiller;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;

public class Alien {
	Image avatar;
	int position;
	boolean visible=false;
	boolean hit=false;
	int[] coordinates;
	Image blackhole;

	public Alien(int a, GameContainer app) throws SlickException{
		avatar=new Image("/Assets/Wisp.png");
		position=a;
		coordinates=new int[2];
		int b=1;
		while(a>3){a-=3;
		b++;}
		coordinates[0]=(a*2-1)*(app.getWidth()/6);
		coordinates[1]=(b*2-1)*(app.getHeight()/6);
		blackhole=new Image("/Assets/BlackHole.png");
	}
	
	public int getPosition(){return position;}
	public int getXCoordinate(){return coordinates[0];}
	public int getYCoordinate(){return coordinates[1];}	
	public Image getAvatar(){return avatar;}
	public boolean getVisible(){return visible;}
	public boolean getHit(){return hit;}
	
	public void setVisible(boolean x){visible=x;}
	public void setHit(boolean x){hit=x;}
}


package boardPlay;

import java.util.Hashtable;

import org.newdawn.slick.*;
public class Pieces {
	private SpriteSheet sprites;
	private int[] location=new int[2];
	private int currentTile;
	private Image avatar;
	
	public Pieces(Image a, int id,Hashtable<Integer,Nodes> map,int offset){
		sprites=new SpriteSheet(a,50,50);
		avatar=sprites.getSprite(0, 0);
		currentTile=id;
		findLocation(currentTile,map);
		location[1]=location[1]+offset;
	}
	
	public int getCurrentTile(){return currentTile;}
	public Image getAvatar(){return avatar;}
	public int getXLocation(){return location[0];}
	public int getYLocation(){return location[1];}
	
	
	public void setCurrentTile(int id){currentTile=id;}
	public void setAvatar(Image a){avatar=a;}
	public void setXLocation(int x){location[0]=x;}
	public void setYLocation(int y){location[1]=y;}
	
	public void findLocation(int id, Hashtable<Integer,Nodes> map){
		Nodes x=map.get(id);
		setXLocation(x.xCoordinate);
		setYLocation(x.yCoordinate);
	}

}

package boardPlay;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class CameraController {
	protected TiledMap map;
	protected int mapTileWidth=25;
	protected int mapTileHeight=25;
	protected int mapWidth=1600;
	protected int mapHeight=1600;
	protected int tileWidth=64;
	protected int tileHeight=64;
	GameContainer app;
	protected float cameraX;
	protected float cameraY;
	
	public CameraController(TiledMap map,GameContainer app){
		this.map=map;
		this.app=app;
	}
	
	public void centerOn(float x,float y){
		cameraX=x-app.getWidth()/2;
		cameraY=y-app.getWidth()/2;
		if(cameraX < 0) cameraX = 0;
	      if(cameraX + app.getWidth() > mapWidth) cameraX = mapWidth - app.getWidth();
	      
	      //if the camera is at the top or bottom edge lock it to prevent a black bar
	      if(cameraY < 0) cameraY = 0;
	      if(cameraY + app.getHeight() > mapHeight) cameraY = mapHeight - app.getHeight();
	}
	public void centerOn(float x, float y, float height, float width) {
	      this.centerOn(x + width / 2, y + height / 2);
	   }

	   /**
	    * "locks the camera on the center of the given Shape. The camera tries to keep the location in it's center.
	    * @param shape the Shape which should be centered on the screen
	    */
	   public void centerOn(Shape shape) {
	      this.centerOn(shape.getCenterX(), shape.getCenterY());
	   }
	   
	   /**
	    * draws the part of the map which is currently focused by the camera on the screen
	    */
	   public void drawMap() {
	      this.drawMap(0, 0);
	   }
	   
	public void drawMap(int offsetX,int offsetY){
		 int tileOffsetX = (int) - (cameraX % tileWidth);
	       int tileOffsetY = (int) - (cameraY % tileHeight);
	       
	       //calculate the index of the leftmost tile that is being displayed
	       int tileIndexX = (int) (cameraX / tileWidth);
	       int tileIndexY = (int) (cameraY / tileHeight);
	       
	       //finally draw the section of the map on the screen
	       map.render(   
	             tileOffsetX + offsetX, 
	             tileOffsetY + offsetY, 
	             tileIndexX,  
	             tileIndexY,
	                (app.getWidth()  - tileOffsetX) / tileWidth  + 1,
	                (app.getHeight() - tileOffsetY) / tileHeight + 1);
	}

}

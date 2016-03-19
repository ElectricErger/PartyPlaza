package boardPlay;
import org.newdawn.slick.tiled.*;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Board extends BasicGame implements Runnable{
	TiledMap board;
	Hashtable<Integer,Nodes> nodes;
	static final int ID=0;
	Pieces[] players=new Pieces[4];
	File map;
	CameraController camera;
	
	
	public Board(String name,File map){
		super(name);
		this.map=map;
	}

	public void init(GameContainer app) throws SlickException {
		try{
			board= new TiledMap("/Assets/board.tmx");
			nodes=Nodes.getConnections(map);
			players[0]=new Pieces(new Image("/Assets/P1.png"),1,nodes,5);
			players[1]=new Pieces(new Image("/Assets/P2.png"),1,nodes,15);
			players[2]=new Pieces(new Image("/Assets/P2.png"),1,nodes,-5);
			players[3]=new Pieces(new Image("/Assets/P1.png"),1,nodes,-15);
			camera=new CameraController(board, app);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	public void render(GameContainer app, Graphics g) throws SlickException {
		for(Pieces a:players){
			a.getAvatar().draw(a.getXLocation(),a.getYLocation());
			}
		camera.drawMap(0, 0);
		}

	@Override
	public void update(GameContainer app, int delta) throws SlickException {
		camera.centerOn(players[0].getXLocation(),players[0].getYLocation());
		//if you get input from the server...
		
	}

	@Override
	public void run() {
		try {
			DisplayMode[] possibilities=Display.getAvailableDisplayModes();
			for(DisplayMode d:possibilities){
				int width=d.getWidth();
				int height=d.getHeight();
				System.out.println(Integer.toString(width)+"x"+Integer.toString(height));
			}
			AppGameContainer app=new AppGameContainer(new Board("Party Plaza",new File("Assets/MapDesc.txt")),1280,720,true);
			app.start();
		} 
		catch (SlickException | LWJGLException e) {
			e.printStackTrace();
		}
	}

}

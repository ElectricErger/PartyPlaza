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
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.*;

public class Board extends BasicGame implements Runnable{
	TiledMap board;
	Hashtable<Integer,Nodes> nodes;
	static final int ID=0;
	Pieces[] players=new Pieces[4];
	File map;
	CameraController camera;
	boolean atJunction=false;
	boolean rollingDice=false;
	Animation diceRoll;
	Dice d6;
	
	
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
			//need images for dice - array for animation, sprite sheet of individual faces
			//d6=new Dice();
			
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
		if(rollingDice){
			diceRoll.draw();
		}
	}

	@Override
	public void update(GameContainer app, int delta) throws SlickException {
		Input input=app.getInput();
		if(input.isKeyPressed(Input.KEY_ESCAPE)) app.exit();
		camera.centerOn(players[0].getXLocation(),players[0].getYLocation());
		if(atJunction){
			selectNextTile(input,app,players[0]);
		}
		if(rollingDice){
			diceRoll=d6.rollDice();
		}
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
	
	public void selectNextTile(Input input, GameContainer game, Pieces piece) throws SlickException{
		Nodes point=nodes.get(players[0].getCurrentTile());
		Nodes first=nodes.get(point.connected[0]);
		MouseOverArea option1=new MouseOverArea(game,new Image("\\Assets\\Option.png"),new Rectangle(first.xCoordinate-32,first.yCoordinate-32,64,64));
		Nodes second=nodes.get(point.connected[1]);
		MouseOverArea option2=new MouseOverArea(game,new Image("\\Assets\\Option.png"),new Rectangle(second.xCoordinate-32,second.yCoordinate-32,64,64));
		if(option1.isMouseOver()){
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				piece.setCurrentTile(point.connected[0]);
			}
		}
		if(option2.isMouseOver()){
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				piece.setCurrentTile(point.connected[1]);
			}
		}
	}
}


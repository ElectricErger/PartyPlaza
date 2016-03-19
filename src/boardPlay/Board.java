package boardPlay;
import org.newdawn.slick.tiled.*;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Board extends BasicGameState{
	TiledMap board;
	Hashtable<Integer,Nodes> nodes;
	static final int ID=0;
	Pieces[] players=new Pieces[4];
	File map;
	
	public Board(File map){
		this.map=map;
	}

	@Override
	public int getID() {
		return ID;
	}

	public void init(GameContainer app, StateBasedGame game) throws SlickException {
		try{
			board= new TiledMap("Assets/board.tmx");
			nodes=Nodes.getConnections(map);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		players[0]=new Pieces(new Image("PartyPlaza/Assets/P1.png"),1,nodes,5);
		players[1]=new Pieces(new Image("PartyPlaza/Assets/P2.png"),1,nodes,15);
		players[2]=new Pieces(new Image("PartyPlaza/Assets/P2.png"),1,nodes,-5);
		players[3]=new Pieces(new Image("PartyPlaza/Assets/P1.png"),1,nodes,-15);

	}


	public void render(GameContainer app, StateBasedGame game, Graphics g) throws SlickException {
		board.render(0, 0);
		for(Pieces a:players){
			a.getAvatar().draw(a.getXLocation(),a.getYLocation());
		}

	}

	public void update(GameContainer app, StateBasedGame game,int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}

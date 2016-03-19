package boardPlay;
import org.newdawn.slick.tiled.*;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.LinkedList;

import org.newdawn.slick.*;

public class Board {
	TiledMap board;
	LinkedList<Integer[]> nodes;
	int boardID;
	
	public Board(int id){
		boardID=id;
		nodes=new LinkedList<Integer[]>();
		try{
			board= new TiledMap("Assets/board.tmx");
			
		}
		catch(SlickException e){
			e.printStackTrace();
		}
	}

}

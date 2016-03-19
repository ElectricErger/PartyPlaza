package boardPlay;

import java.io.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GamePlay extends StateBasedGame {
	
	public GamePlay(String name){
		super(name);
	}

	public static void main(String[] args) {
		try{
			GamePlay game=new GamePlay("Party Plaza");
			AppGameContainer app=new AppGameContainer(game,1024,768,false);
			game.initStatesList(app);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void initStatesList(GameContainer game) throws SlickException {
		addState(new Board(new File("\\Assets\\MapDesc.txt")));
		
	}

}

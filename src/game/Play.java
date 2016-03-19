package game;

import org.newdawn.slick.state.*;

import boardPlay.Board;

import java.io.File;

import org.newdawn.slick.*;

public class Play extends StateBasedGame{

	public Play(String name){
		super(name);
	}

	public static void main(String[] args) {
		try{
			Play game=new Play("Party Plaza");
			AppGameContainer app=new AppGameContainer(game,1024,768,false);
			game.initStatesList(app);
			app.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void initStatesList(GameContainer app) throws SlickException {
		addState(new Board(new File("./Assets/MapDesc.txt")));
		
	}

}

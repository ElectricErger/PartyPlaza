package minigames.alienKiller;

import java.io.IOException;

import server.Gameplay;
import server.Player;

public class GameServer implements Runnable{
	Gameplay board;
	
	public GameServer(Gameplay g){
		board = g;
	}
	
	@Override
	public void run() {
		//Get all 4 people's scores at the end
		
		//Broadcast the results
		board.broadcast("p1:");
		board.broadcast("p2:");
		board.broadcast("p3:");
		board.broadcast("p4:");
		//Award points
		cleanup(null);
		//Await everyone's okay to continue
		
		
	}
	
	private void cleanup(Player[] winners){
		board.winner(winners);
	}
	private void getScore(Player p){
		for(int i = 20; i>0; i--){
			String s = "";
			try {s = p.getNextMessage();}
			catch (IOException e) {
				e.printStackTrace();
				try {Thread.sleep(200);}
				catch (InterruptedException e1) {
					e1.printStackTrace();
					i++;
				}
			}
			if(s.equals("")){}
		}
	}
}

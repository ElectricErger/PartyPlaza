package server;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;

import boardPlay.Nodes;
import minigames.*;

public class Gameplay implements Runnable{

	private Hashtable<Integer,Nodes> nodes;
	private static Player[] players;

	public void run() {
		//Get map data
		File f = new File("Assets/MapDesc.txt");
		try {nodes = Nodes.getConnections(f);}
		catch (IOException e) {e.printStackTrace();}

		broadcast("Map loaded"); //Tell everyone the map is loaded
		initiativeRoll();
		for(Player p: players){ addPoints(100, p); }
		
		
		//Main loop
		int turns = PartyPlazaServer.getTurns();
		while(turns > 0){
			orderedRoll();
			chooseMinigame();
			//Call minigame
			//Give victor points	
		}
	}
	

	
	//Ordering (first roll)
	private void initiativeRoll(){
		int[] order = new int[players.length];
		//Hashtable<Integer, Player> order = new Hashtable<Integer, Player>();
		for(int i = players.length; i>0; i--){
			order[i] = roll(players[i]);
			//order.put(roll(players[i]), players[i]);
		}

		orderPlayers(order);
	}
	private void orderPlayers(int[] order){ //Bubble sort
		for(int i = 0; i< players.length-1; i++){
			for(int j = i+1; j<players.length; j++){
				if(order[i]>order[j]){
					Player a = players[i];
					players[i] = players[j];
					players[j] = a;
				}
			}
		}
	}
	
	//General play
	private void orderedRoll(){//WIP: Points
		for(Player p: players){
			int i = roll(p);
			move(p,i);
			//get points based off what tile you land on
		}
	}
	private void move(Player piece, int roll){ //ADAPT
		//Input input=game.getInput(); //SLICK
		for(int a=1;a<=roll;a++){
			Nodes point=nodes.get(piece.getCurrentTile());
			if(point.connected.length==1){ //One path --> move
				piece.setCurrentTile(point.connected[0]);
			}
			else if(point.connected.length==2){ //if there are many paths request
				//Send request
				//Set current position to new position
			}
			
		}
	}
	private void chooseMinigame(){
		Random gen = new Random();
		//New minigame from LUT according to random number
		minigames.alienKiller.Game g = new minigames.alienKiller.Game();
		g.run();
	}
	
	public static void broadcast(String msg){
		for(Player p: players){ p.sentMessage(msg); }
	}
	//Player API
	private void addPoints(int i, Player p){
		p.addPoints(i);
		p.sentMessage("points:"+p.getPoints());
	}
	private void addVictoryPoints(int i, Player p){
		p.addVictoryPoints(i);
		p.sentMessage("victory points:"+p.getPoints());
	}
	private int roll(Player p){//WIP:broadcast
		p.sentMessage("roll");
		
		for(int i=20; i>0; i--){ //Wait for the next message, if we fail then we sleep and try again max 2 seconds
			try { p.getNextMessage();}
			catch (IOException e) {
				e.printStackTrace();
				try {Thread.sleep(100);}
				catch (InterruptedException e1) {e1.printStackTrace(); i++;}
			}
		}
		Random gen = new Random();
		int i = gen.nextInt(10)+1;
		//Broadcast what the roll was
		return i;
	}
}

package server;

import java.net.Socket;

public class Player {
	private Socket connection;
	private int player;
	private int points = 0;
	private int victoryPoints = 0;
	private int armor = 0;
	
	Player(Socket sock, int p){
		connection = sock;
		player = p;
	}

	public void addPoints(int p){points+=p;}
	public void addVictoryPoints(int p){victoryPoints+=p;}
	public void addArmor(int p){armor+=p;}

	public Socket getConnection(){return connection;}
	public int getPlayerID(){return player;}
	public int getPoints(){return points;}
	public int getVictoryPoints(){return victoryPoints;}
	public int getArmor(){return armor;}
	
}

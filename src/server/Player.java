package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
	private Socket connection;
	private int player;
	private int points = 0;
	private int victoryPoints = 0;
	private int armor = 0;
	private int order;
	private int currentTile;
	
	private PrintWriter out;
	private BufferedReader in;
	
	Player(Socket sock, int p) throws IOException{
		connection = sock;
		player = p;
		
        out = new PrintWriter(sock.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	}
	//Socket
	public Socket getConnection(){return connection;}
	public String getNextMessage() throws IOException{ return in.readLine(); }
	public void sentMessage(String s){out.println(s);}
	//Technical data
	public int getPlayerID(){return player;}
	public void setOrder(int o){order = o;}
	public int getOrder() throws NullPointerException {return order;}
	public void setCurrentTile(int id){currentTile=id;}
	public int getCurrentTile(){return currentTile;}

	//Game getter and setters
	public void addPoints(int p){points+=p;}
	public void addVictoryPoints(int p){victoryPoints+=p;}
	public void addArmor(int p){armor+=p;}

	public int getPoints(){return points;}
	public int getVictoryPoints(){return victoryPoints;}
	public int getArmor(){return armor;}
	
}

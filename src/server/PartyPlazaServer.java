package server;

import java.io.IOException;
import java.net.*;

public class PartyPlazaServer {
	public static final int CLIENTPORT = 9000;
	public static final int SERVERPORT = 9001;
	private static DatagramSocket udpSocket;
	private static ServerSocket tcpSocket;
	
	private static int players = 0; //Currently this doesn't handle people leaving
	private static Player[] player;

	private static int map = 0;
	private static int turns = 0;
	
	
	public static void main(String[] args) throws IOException {
		udpSocket = new DatagramSocket(SERVERPORT);
		tcpSocket = new ServerSocket(SERVERPORT);
		player = new Player[4];
		System.out.println("Server set, waiting for clients...");
		
		//Wait for people to join till we start
		boolean waitForConnections = true;
		while (waitForConnections){
			DatagramPacket in = null;
			String msg = getPacket(in);
			
			if(msg.equals("join")){ connect();}
			else{waitForConnections = false;} //Start
		}	
		
		//Send maps to connected people
		for(int i=0; i<players; i++){
			Socket sock = player[i].getConnection();
			sock.getOutputStream().write("Space Race".getBytes());
		}
		
		//Select map
		{
			byte[] b = new byte[1];
			player[0].getConnection().getInputStream().read(b);
			map = b[0];
		}
		
		//Select number of turns
		{
			byte[] b = new byte[1];
			player[0].getConnection().getInputStream().read(b);
			turns = b[0];
		}
		
		
		System.out.println("Starting map");
	}
	
	private static String getPacket(DatagramPacket in){
		byte[] inBuffer = new byte[20];
		in = new DatagramPacket(inBuffer, inBuffer.length);
		try { udpSocket.receive(in);}
		catch (IOException e) {	e.printStackTrace(); }
		return new String(in.getData());
	}
	private static void connect(){
		//If joining establish TCP connection and add that to list.
		try {
			player[players] = new Player(tcpSocket.accept(), players+1);
			players++;
			player[players].getConnection().getOutputStream().write((players+"").getBytes());
		} catch (Exception e) {e.printStackTrace();}
	}

	public static int getTurns(){return turns;}
	public static int getMap(){return map;}
	
	public DatagramSocket getUDPSocket(){ return udpSocket; }
	public ServerSocket getTCPSocket(){ return tcpSocket; }
}

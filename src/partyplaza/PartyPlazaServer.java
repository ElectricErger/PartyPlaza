package partyplaza;

import java.io.IOException;
import java.net.*;

public class PartyPlazaServer {
	public static final int CLIENTPORT = 9000;
	public static final int SERVERPORT = 9001;
	private static DatagramSocket udpSocket;
	private static ServerSocket tcpSocket;
	private static Socket[] connections;
	private static int players = 0; //Currently this doesn't handle people leaving
	
	public static void main(String[] args) throws IOException {
		udpSocket = new DatagramSocket(SERVERPORT);
		tcpSocket = new ServerSocket(SERVERPORT);
		connections = new Socket[4];
		System.out.println("Server set, waiting for clients...");
		//Wait for people to join
		
		boolean waitForConnections = true;
		while (waitForConnections){ //Get a message, wait for join/start
			DatagramPacket in = null;
			String msg = getPacket(in);
			
			if(msg.equals("join")){ connect();}
			else{waitForConnections = false;} //Start
		}	
		
		//Send maps to connected people
		for(int i=0; i<players; i++){
			Socket sock = connections[i];
			sock.getOutputStream().write("Space Race".getBytes());
		}
		
		//Select map
		int map = 0;
		{
			byte[] b = new byte[1];
			connections[0].getInputStream().read(b);
			map = b[0];
		}
		
		
		
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
			connections[players] = tcpSocket.accept();
			players++;
			connections[players].getOutputStream().write((players+"").getBytes());
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public DatagramSocket getUDPSocket(){ return udpSocket; }
	public ServerSocket getTCPSocket(){ return tcpSocket; }
}
